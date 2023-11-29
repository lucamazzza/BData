package ch.mazluc;

import java.io.Serializable;

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
     * Returns the key type of the Document.
     *
     * @return the key type
     */
    Class<?> keyType();

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
