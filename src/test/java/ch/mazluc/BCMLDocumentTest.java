package ch.mazluc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCMLDocumentTest {

    @Test
    void lineCount() {
        BCMLDocument<Integer> document = new BCMLDocument<>();
        assertEquals(0, document.lineCount());
        document.append(new Line<>(3,"First line"));
        assertEquals(1, document.lineCount());
    }

    @Test
    void isEmpty() {
        BCMLDocument<Integer> document = new BCMLDocument<>();
        assertTrue(document.isEmpty());
        document.append(new Line<>(3,"First line"));
        assertFalse(document.isEmpty());
    }

    @Test
    void getLine() {
    }

    @Test
    void append() {
    }

    @Test
    void clear() {
    }

    @Test
    void serialize() {
    }

    @Test
    void iterator() {
    }
}