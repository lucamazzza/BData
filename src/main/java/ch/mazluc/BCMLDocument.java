package ch.mazluc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.*;

public class BCMLDocument<K> implements Document<K>{

    /**
     * The array of lines.
     */
    private final transient List<Line<K>> lines;

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
        this.lines.add(line);
    }

    /**
     * Empties the Document
     */
    @Override
    public void clear() {
        this.lines.clear();
    }

    public void serialize(File file) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file))) {
            for (Line<K> line : this) {
                writer.append(line.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deserialize(File file) {
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.append(Line.parseLine(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
