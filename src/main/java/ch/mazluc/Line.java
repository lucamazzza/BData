package ch.mazluc;

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
        return """
                {
                    key: %s,
                    value: %s
                }
                """.formatted(key, value);
    }

    public Class<?> keyType() {
        return key.getClass();
    }
}
