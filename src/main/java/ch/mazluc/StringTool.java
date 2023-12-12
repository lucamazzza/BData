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

public class StringTool {

    private StringTool(){
        // utility
    }

    public static String join(String separator, String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i < strings.length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String trimAll(String string) {
        return string.replaceAll("\\s|\\{|\\}|\"", "");
    }

    public static Object[] toArray(String s) {
        s = s.replaceAll("\\[|\\]", "");
        String[] splitted = s.split(",");
        Object[] result = new Object[splitted.length];
        for (int i = 0; i < splitted.length; i++) {
            result[i] = parseString(splitted[i]);
        }
        return result;
    }

    public static Object parseString(String s) {
        s = trimAll(s);
        if (s.isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e2) {
                if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(s);
                } else if (s.length() == 1) {
                    return s.charAt(0);
                } else {
                    return s;
                }
            }
        }
    }
}
