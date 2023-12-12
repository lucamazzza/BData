/*
 * MIT License
 *
 * Copyright (c) 2023 Luca Mazza
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ch.mazluc;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a line.
 * A line is made of a key and a value.
 *
 * @param key key
 * @param value value
 * @param <K> key type
 */
public record Line<K>(K key, Object value) {
    /**
     * Constructor for the line
     *
     * @param key the key
     * @param value the value
     */
    public Line {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
    }

    /**
     * Constructor for the empty line
     *
     * @param key the key
     */
    public Line(K key) {
        this(key, null);
    }

    /**
     * Returns the string representation of the line
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        String result = (
                key.getClass() == String.class ?
                        "\"" + this.key + "\"" :
                        this.key.toString());

        if (this.value != null && this.value.getClass().isArray()) {
            if (this.value instanceof Object[] objects) {
                return result + ": " + Arrays.deepToString(objects);
            }
            int length = Array.getLength(this.value);
            Object[] array = new Object[length];
            for (int i = 0; i < length; i++) {
                array[i] = Array.get(this.value, i);
            }
            return result + ": " + Arrays.deepToString(array);
        }

        if (this.value != null && this.value instanceof String string) {
            return result + ": " + "\""+ string + "\"";
        }

        if (this.value != null && this.value instanceof Line<?> line) {
            String nestedLine = line.toString().replace("\n", "\n\t");
            return result + ": {\n\t" + nestedLine + "\n}";
        }

        return result + ": " + this.value;
    }

    /**
     * Returns the key type of the line.
     *
     * @return the key type
     */
    public Class<?> keyType() {
        return key.getClass();
    }

    /**
     * Parses a line from a string.
     *
     * @param s the string
     * @return the line
     * @throws ClassCastException if the line is invalid
     */
    @SuppressWarnings("unchecked")
    public static <K> Line<K> parseLine(String s) throws ClassCastException {
        s = StringTool.trimAll(s);
        String[] splitted = s.split(":");
        K key = null;
        if (splitted.length == 2){
            try {
                key = (K) StringTool.parseString(splitted[0]);
            } catch (ClassCastException ignored) {
                throw new ClassCastException("Invalid key type: " + splitted[0]);
            }
            if (splitted[1].startsWith("[") && splitted[1].endsWith("]")) {
                Object[] value = StringTool.toArray(splitted[1]);
                return new Line<>(key, value);
            }
            Object value = StringTool.parseString(splitted[1]);
            return new Line<>(key, value);
        }
        try {
            return new Line<>(
                    (K) StringTool.parseString(splitted[0]),
                    parseLine(s.substring(splitted[0].length() + 1))
            );
        } catch (ClassCastException ignored) {
            throw new ClassCastException("Invalid key type: " + splitted[0]);
        }
    }

    /**
     * Returns the subline at the specified depth
     *
     * @param depth the depth
     * @return the subline
     */
    @SuppressWarnings("unchecked")
    public Line<K> subLine(int depth) {
        if (depth == 0) {
            return this;
        }
        try {
            return ((Line<K>) this.value()).subLine(depth - 1);
        } catch (ClassCastException ignored) {
            return this;
        }
    }
}
