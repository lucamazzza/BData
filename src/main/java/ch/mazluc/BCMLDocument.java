package ch.mazluc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Represents a document of type BCML.
 * A document is made of lines.
 * A line is made of a key and a value.
 *
 * @param <K> key type
 * @author Luca Mazza
 * @version 1.0
 * @since 1.0
 */
public class BCMLDocument<K> implements Document<K>{

    /**
     * The array of lines.
     */
    private final transient List<Line<K>> lines;

    private static final Logger logger = Logger.getLogger(BCMLDocument.class.getName());

    public BCMLDocument() {
        this.lines = new ArrayList<>();
    }

    public BCMLDocument(File file){
        this();
        this.deserialize(file);
    }

    /**
     * Retrieves the count of lines of the Document.
     *
     * @return the count of lines
     */
    @Override
    public int lineCount() {
        return this.lines.size();
    }

    /**
     * Checks if the Document is empty or not.
     *
     * @return true if the Document is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return lines.isEmpty();
    }

    /**
     * Returns the key type of the Document.
     *
     * @return the key type
     */
    @Override
    public Class<?> keyType() {
        return (this.lineCount() != 0 ? lines.get(0).keyType() : null);
    }

    /**
     * Retrieves the line at the specified index.
     *
     * @param index the index
     * @return the line
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public Line<K> getLine(int index) throws IndexOutOfBoundsException {
        if (this.isEmpty()) { throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length 0"); }
        return this.lines.get(index);
    }

    /**
     * Appends a line at the end of the document.
     *
     * @param line the line
     */
    @Override
    public void append(Line<K> line) {
        if (line.keyType() != this.keyType()) {
            throw new IllegalArgumentException("Key type must be " + this.keyType());
        }
        this.lines.add(line);
    }

    /**
     * Empties the Document
     */
    @Override
    public void clear() {
        this.lines.clear();
    }

    private void toBCML(String doc) {
        for (String line : doc.split("\n")) {
            this.append(Line.parseLine(line));
        }
    }

    /**
     * Serializes the document to the given file.
     *
     * @param  file  the file to be serialized in
     */
    public void serialize(File file) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file))) {
            for (Line<K> line : this) {
                writer.append(line.toString());
            }
            writer.flush();
            logger.log(new LogRecord(Level.INFO, "Document serialized to " + file.getAbsolutePath()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    /**
     * Deserializes the document in the given file.
     *
     * @param  file  the file in which the document is contained
     */
    private void deserialize(File file) {
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            if (reader.lines().findAny().isEmpty()) {
                logger.log(
                        new LogRecord(
                                Level.WARNING,
                                "Document is empty, can't deserialize from " + file.getAbsolutePath()
                        )
                );
                return;
            }
            StringBuilder doc = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.charAt(0) == '#') { continue; }
                doc.append(line).append("\n");
            }
            doc = new StringBuilder(doc.toString().replace("(?<=\\{)\\n|\\t|(?=\\})\\n\\t", ""));
            doc = new StringBuilder(doc.toString().trim());
            this.lines.clear();
            this.toBCML(doc.toString());
            logger.log(new LogRecord(Level.INFO, "Document deserialized from " + file.getAbsolutePath()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Line<K>> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < lineCount() && lines.get(currentIndex) != null;
            }

            @Override
            public Line<K> next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return lines.get(currentIndex++);
            }
        };
    }
}