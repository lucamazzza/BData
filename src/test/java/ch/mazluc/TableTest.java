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
        assertEquals(2, table.length());
    }

    @Test
    void isEmpty() {
        Table table = new Table();
        assertTrue(table.isEmpty());
        table.push(new Tuple(0, 1, 2));
        table.push(new Tuple(3, 4, 5));
        assertFalse(table.isEmpty());
    }

    @Test
    void contains() {
        Table table = new Table();
        table.push(new Tuple(0, 1, 2));
        table.push(new Tuple(3, 4, 5));
        assertTrue(table.contains(0));
        assertFalse(table.contains(6));
        assertTrue(table.contains(2));
        table.clear();
        assertFalse(table.contains(0));
    }

    @Test
    void setValues() {
        Table table = new Table();
        table.setValues(new Tuple(0, 1), 1, 2, new Tuple(3, 4, 5, 6), 4);
        assertEquals(5, table.length());
        assertEquals(0, (int)table.getValue(0, 0));
        assertEquals(1, (int)table.getValue(1,0));
        assertEquals(2, (int)table.getValue(2,0));
        assertEquals(6, (int)table.getValue(3,3));
        assertEquals(4, (int)table.getValue(4,0));
    }

    @Test
    void push() {
        Table table = new Table();
        table.push(1);
        table.push(new Tuple(2, 3));
        table.push(3);
        assertFalse(table.isEmpty());
        assertTrue(table.contains(1));
        assertTrue(table.contains(2));
        assertTrue(table.contains(3));
        assertEquals(3, table.length());
        assertEquals(1, (int)table.getValue(0,0));
        assertEquals(2, (int)table.getValue(1,0));
        assertEquals(3, (int)table.getValue(2,0));
    }

    @Test
    void insert() {
        Table table = new Table();
        table.setValues(
                new Tuple(6.5e13, 1, false),
                new Tuple(1, 'e', 3),
                new Tuple(3, 4.4, 5)
        );
        table.insert(0, 0, 6);
        table.insert(1, 1, 7);
        table.insert(2, 2, 8);
        assertTrue(table.contains(6));
        assertTrue(table.contains(7));
        assertTrue(table.contains(8));
        assertEquals(3, table.length());
        assertEquals(6, (int)table.getValue(0,0));
        assertEquals(7, (int)table.getValue(1,1));
        assertEquals(8, (int)table.getValue(2,2));
    }

    @Test
    void replace() {
        Table table = new Table();
        table.setValues(
                new Tuple(0, "1", false),
                new Tuple(1, 3.3, 3),
                new Tuple(3, 4f, 5)
        );
        table.replace(0, 0, 6);
        table.replace(1, 1, 7);
        table.replace(2, 2, 8);
        assertTrue(table.contains(6));
        assertTrue(table.contains(7));
        assertTrue(table.contains(8));
        assertEquals(3, table.length());
        assertEquals(6, (int)table.getValue(0,0));
        assertEquals(7, (int)table.getValue(1,1));
        assertEquals(8, (int)table.getValue(2,2));
    }

    @Test
    void swap() {
        Table table = new Table();
        table.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        table.swap(0, 1);
        table.swap(1, 2);
        assertEquals(3, (int)table.getValue(0,0));
        assertEquals(0, (int)table.getValue(2,0));
        assertFalse((boolean) table.getValue(1, 4));
        assertNotEquals(2, (int)table.getValue(0,0));
        assertNotEquals(1, (int)table.getValue(1,0));
    }

    @Test
    void getValue() {
        Table table = new Table();
        table.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        assertEquals(0, (int)table.getValue(0,0));
        assertEquals(3, (int)table.getValue(1,0));
        assertEquals(7, (int)table.getValue(2,0));
        assertEquals(2, (int)table.getValue(0,2));
        assertEquals("2", table.getValue(2,3));
    }

    @Test
    void indexOf() {
        Table table = new Table();
        table.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        assertArrayEquals(new int[]{0, 0}, table.indexOf(0));
        assertArrayEquals(new int[]{0, 1}, table.indexOf(1));
        assertArrayEquals(new int[]{0, 2}, table.indexOf(2));
        assertArrayEquals(new int[]{1, 0}, table.indexOf(3));
        assertArrayEquals(new int[]{1, 1}, table.indexOf(4));
        assertArrayEquals(new int[]{1, 2}, table.indexOf(5));
        assertArrayEquals(new int[]{1, 3}, table.indexOf(6));
        assertArrayEquals(new int[]{2, 0}, table.indexOf(7));
        assertArrayEquals(new int[]{2, 1}, table.indexOf(8));
        assertArrayEquals(new int[]{2, 2}, table.indexOf(9));
        assertArrayEquals(new int[]{2, 3}, table.indexOf("2"));
        assertArrayEquals(new int[]{2, 4}, table.indexOf(false));
    }

    @Test
    void pop() {
        Table table = new Table(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        table.pop();
        assertEquals(2, table.length());
        table.pop();
        assertEquals(1, table.length());
        table.pop();
        assertEquals(0, table.length());
        table.pop();
        assertEquals(0, table.length());
    }

    @Test
    void remove() {
        Table table = new Table(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        table.remove(0, 0);
        assertEquals(3, table.length());
        assertFalse(table.contains(0));
        table.remove(1, 1);
        assertEquals(3, table.length());
        assertFalse(table.contains(4));
        table.remove(2, 2);
        assertEquals(3, table.length());
        assertFalse(table.contains(9));
        table.remove(0, 0);
        assertEquals(3, table.length());
        assertFalse(table.contains(0));
    }

    @Test
    void clear() {
        Table table = new Table();
        table.setValues(new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        table.clear();
        assertEquals(0, table.length());
        assertFalse(table.contains(5));
        assertFalse(table.contains(6));
        assertFalse(table.contains(7));
        assertFalse(table.contains(8));
        assertFalse(table.contains(9));
    }

    @Test
    void slice() {
        Table table = new Table();
        table.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        table.slice(0, 2);
        assertEquals(2, table.length());
        assertEquals(0, (int)table.getValue(0,0));
        assertEquals(6, (int)table.getValue(1,3));
        table.slice(1, 2);
        assertEquals(1 , table.length());
        assertEquals(4, (int)table.getValue(0, 1));

    }

    @Test
    void join() {
        Table table = new Table();
        Table table1 = new Table();
        table.setValues(new Tuple(0, 1, 2), new Tuple(3, 4, 5, 6));
        table1.setValues(new Tuple(7, 8, 9, "2", false), new Tuple(true, 'a'));
        table.join(table1);
        assertEquals(4, table.length());
        assertEquals(0, (int)table.getValue(0,0));
        assertEquals(1, (int)table.getValue(0,1));
        assertEquals(2, (int)table.getValue(0,2));
        assertEquals(3, (int)table.getValue(1,0));
        assertEquals(4, (int)table.getValue(1,1));
        assertEquals(5, (int)table.getValue(1,2));
        assertEquals(6, (int)table.getValue(1,3));
        assertEquals(7, (int)table.getValue(2,0));
        assertEquals(8, (int)table.getValue(2,1));
        assertEquals(9, (int)table.getValue(2,2));
        assertEquals("2", table.getValue(2,3));
        assertEquals(false, table.getValue(2,4));
        assertEquals(true, table.getValue(3,0));
        assertEquals('a', (char)table.getValue(3,1));
    }

    @Test
    void isSubsetOf() {
        Table table = new Table();
        table.setValues(new Tuple(0, 1, 2), new Tuple(3, 4, 5, 6));
        Table table1 = new Table();
        table1.setValues(new Tuple(0, 1, 2), new Tuple(3, 4, 5, 6), new Tuple(7, 8, 9, "2"));
        assertTrue(table.isSubsetOf(table1));
        table1.setValues(new Tuple(0, 1, 2), new Tuple(3, 4, 5, 6), new Tuple(7, 8, 9, "2"));
        assertTrue(table.isSubsetOf(table1));
        table1.setValues(new Tuple(0, 1, 2));
        assertFalse(table.isSubsetOf(table1));
    }

    @Test
    void isSupersetOf() {
        Table table = new Table();
        table.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        Table table1 = new Table();
        table1.setValues(new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        assertTrue(table.isSupersetOf(table1));
        table1.setValues(new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false),
                new Tuple(10, 11, 12, "3", true)
        );
        assertFalse(table.isSupersetOf(table1));
        table1.setValues(new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false),
                new Tuple(10, 11, 12, "3", true),
                new Tuple(13, 14, 15, "4", false)
        );
        assertFalse(table.isSupersetOf(table1));
    }

    @Test
    void subtract() {
        Table table = new Table();
        Table table1 = new Table();
        table.setValues(new Tuple(0, 1, 2), new Tuple(3, 4, 5, 6), new Tuple(7, 8, 9));
        table1.setValues(new Tuple(0, 1, 2));
        Table comp = new Table(new Tuple(3, 4, 5, 6), new Tuple(7, 8, 9));
        Table subt = (Table) table.subtract(table1);
        assertEquals(comp, subt);
    }

    @Test
    void filter() {
        Table table = new Table();
        table.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3.0, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        assertEquals("[2]", table.filter(t -> t instanceof String).toString());
        assertEquals("[0, 1, 2, 4, 5, 6, 7, 8, 9]", table.filter(t -> t instanceof Integer).toString());
        assertEquals("[3.0]", table.filter(t -> t instanceof Double).toString());
        assertEquals("[0, 1, 2, 3.0, 4, 5, 6, 7, 8, 9]", table.filter(t -> t instanceof Number).toString());
    }

    @Test
    void isDisjoint() {
        Table table = new Table();
        table.setValues(new Tuple("ciao", true), new Tuple(0.2, 4.4f));
        Table table1 = new Table();
        table1.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false)
        );
        assertTrue(table.isDisjoint(table1));
        table1.setValues(
                new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false),
                new Tuple(10, 11, 12, "3", true)
        );
        assertFalse(table.isDisjoint(table1));
        table1.setValues(new Tuple(0, 1, 2),
                new Tuple(3, 4, 5, 6),
                new Tuple(7, 8, 9, "2", false),
                new Tuple(10, 11, 12, "3", false)
        );
        assertTrue(table.isDisjoint(table1));
    }
}