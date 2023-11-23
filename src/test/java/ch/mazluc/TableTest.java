package ch.mazluc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void length() {
        Table table = new Table();
        assertEquals(0, table.length());
        table.push(new Tuple(0, 1, 2));
        assertEquals(1, table.length());
        table.push(new Tuple(3, 4, 5));
    }

    @Test
    void isEmpty() {
        Table table = new Table();
        assertTrue(table.isEmpty());
        table.push(new Tuple(0, 1, 2));
        table.push(new Tuple(3, 4, 5));
        assertFalse(table.isEmpty());
        System.out.println(table);
    }

    @Test
    void contains() {
    }

    @Test
    void setValues() {
    }

    @Test
    void push() {
    }

    @Test
    void insert() {
    }

    @Test
    void replace() {
    }

    @Test
    void swap() {
    }

    @Test
    void getValue() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void pop() {
    }

    @Test
    void remove() {
    }

    @Test
    void clear() {
    }

    @Test
    void slice() {
    }

    @Test
    void join() {
    }

    @Test
    void isSubsetOf() {
    }

    @Test
    void isSupersetOf() {
    }

    @Test
    void isStrictSubsetOf() {
    }

    @Test
    void isStrictSupersetOf() {
    }

    @Test
    void symmetricDifference() {
    }

    @Test
    void subtract() {
    }

    @Test
    void filter() {
    }

    @Test
    void isDisjoint() {
    }

    @Test
    void iterator() {
    }
}