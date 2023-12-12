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

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Interface for Document.
 * A Document is a collection of lines.
 *
 * @param <K>
 * @author Luca Mazza
 * @version 1.0
 * @since 1.0
 */
public interface Document<K> extends Iterable<Line<K>>, Serializable {

    /**
     * Retrieves the count of lines of the Document.
     *
     * @return the count of lines
     */
    int lineCount();

    /**
     * Checks if the Document is empty or not.
     *
     * @return true if the Document is empty, false otherwise
     */
    boolean isEmpty();


    /**
     * Retrieves the line at the specified index.
     *
     * @param index the index
     * @return the line
     */
    Line<K> getLine(int index);

    /**
     * Appends a line at the end of the document.
     *
     * @param line the line
     */
    void append(Line<K> line);

    /**
     * Empties the Document
     */
    void clear();
}
