package BData.tests;
import BData.src.*;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @org.junit.jupiter.api.Test
    void length() {
        Tuple tuple = new Tuple();
        assertEquals(0, tuple.length());
        tuple.push(1);
        assertEquals(1, tuple.length());
        tuple.push(2);
        assertEquals(2, tuple.length());
        tuple.push(3);
        assertEquals(3, tuple.length());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        Tuple tuple = new Tuple();
        assertTrue(tuple.isEmpty());
        tuple.push(1);
        assertFalse(tuple.isEmpty());
        tuple.pop();
        assertTrue(tuple.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        TData tuple = new Tuple();
        TData tuple2 = new Tuple();
        tuple.push(1);
        assertNotEquals(tuple, tuple2);
        tuple2.push(1);
        assertEquals(tuple, tuple2);
        tuple.push(2);
        assertNotEquals(tuple, tuple2);
        tuple2.push(2);
        assertEquals(tuple, tuple2);
        tuple.push(3);
        assertNotEquals(tuple, tuple2);
        tuple2.push(3);
        assertEquals(tuple, tuple2);
        tuple.push(4);
        assertNotEquals(tuple, tuple2);
        tuple2.push(4);
        assertEquals(tuple, tuple2);
    }

    @org.junit.jupiter.api.Test
    void contains() {
        TData tuple = new Tuple(1);
        tuple.push(1);
        assertTrue(tuple.contains(1));
        assertFalse(tuple.contains(2));
    }

    @org.junit.jupiter.api.Test
    void containsType() {
        TData tuple = new Tuple(1);
        tuple.push(1);
        assertTrue(tuple.containsType(Integer.class));
        assertFalse(tuple.containsType(String.class));
        assertFalse(tuple.containsType(Boolean.class));
        tuple.push(null);
        assertFalse(tuple.containsType(Object.class));
        assertFalse(tuple.containsType(Boolean.class));
        tuple.push("test");
        assertTrue(tuple.containsType(String.class));
        assertFalse(tuple.containsType(Boolean.class));
        tuple.push(true);
        assertTrue(tuple.containsType(Boolean.class));
    }

    @org.junit.jupiter.api.Test
    void setValues() {
        TData tuple = new Tuple();
        tuple.setValues(1, 2, 3, 4, 5);
        assertEquals(5, tuple.length());
        assertEquals(1, (int)tuple.getValue(0));
        assertEquals(2, (int)tuple.getValue(1));
        assertEquals(3, (int)tuple.getValue(2));
        assertEquals(4, (int)tuple.getValue(3));
        assertEquals(5, (int)tuple.getValue(4));
        assertNotEquals(2, (int)tuple.getValue(0));
        assertNotEquals(1, (int)tuple.getValue(1));
        assertNotEquals(5, (int)tuple.getValue(2));
        assertNotEquals(8, (int)tuple.getValue(3));
        assertNotEquals(1, (int)tuple.getValue(4));
    }

    @org.junit.jupiter.api.Test
    void push() {
        TData tuple = new Tuple();
        tuple.push(1);
        tuple.push(2);
        tuple.push(3);
        assertEquals(3, tuple.length());
        assertEquals(1, (int)tuple.getValue(0));
        assertEquals(2, (int)tuple.getValue(1));
        assertEquals(3, (int)tuple.getValue(2));
        assertNotEquals(2, (int)tuple.getValue(0));
        assertNotEquals(1, (int)tuple.getValue(1));
        assertNotEquals(5, (int)tuple.getValue(2));
    }

    @org.junit.jupiter.api.Test
    void insert() {
        TData tuple = new Tuple();
        tuple.insert(0, 1);
        tuple.insert(1, 2);
        tuple.insert(2, 3);
        assertEquals(3, tuple.length());
        assertEquals(1, (int)tuple.getValue(0));
        assertEquals(2, (int)tuple.getValue(1));
        assertEquals(3, (int)tuple.getValue(2));
        assertNotEquals(2, (int)tuple.getValue(0));
        assertNotEquals(1, (int)tuple.getValue(1));
        assertNotEquals(5, (int)tuple.getValue(2));
    }

    @org.junit.jupiter.api.Test
    void replace() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", false);
        tuple.replace(0, 1);
        tuple.replace(1, 2);
        tuple.replace(2, 3);
        assertEquals(1, (int)tuple.getValue(0));
        assertEquals(2, (int)tuple.getValue(1));
        assertEquals(3, (int)tuple.getValue(2));
        assertNotEquals(2, (int)tuple.getValue(0));
        assertNotEquals(1, (int)tuple.getValue(1));
        assertNotEquals(5, (int)tuple.getValue(2));
    }

    @org.junit.jupiter.api.Test
    void swap() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", false);
        tuple.swap(0, 1);
        tuple.swap(1, 2);
        tuple.swap(2, 3);
        assertEquals(1, (int)tuple.getValue(0));
        assertEquals(2, (int)tuple.getValue(1));
        assertEquals(3, (int)tuple.getValue(2));
        assertNotEquals(2, (int)tuple.getValue(0));
        assertNotEquals(1, (int)tuple.getValue(1));
        assertNotEquals(5, (int)tuple.getValue(2));
    }

    @org.junit.jupiter.api.Test
    void getValue() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", false);
        assertEquals(0, (int)tuple.getValue(0));
        assertEquals(1, (int)tuple.getValue(1));
        assertEquals(2, (int)tuple.getValue(2));
        assertEquals(3, (int)tuple.getValue(3));
        assertEquals(4, (int)tuple.getValue(4));
        assertEquals(5, (int)tuple.getValue(5));
        assertEquals(6, (int)tuple.getValue(6));
        assertEquals(7, (int)tuple.getValue(7));
        assertEquals(8, (int)tuple.getValue(8));
        assertEquals(9, (int)tuple.getValue(9));
        assertEquals("2", tuple.getValue(10));
        assertEquals(false, tuple.getValue(11));
    }

    @org.junit.jupiter.api.Test
    void getValuesOfType() {
        BigInteger s = new BigInteger("12345678901234567890123456789012345678901234567890");
        TData tuple = new Tuple(1, 2, 3, 4.0f, 5.5, 5.65, "Ciao", false, null, s);
        assertEquals(tuple.getValuesOfType(Integer.class), new Tuple(1, 2, 3));
        assertTrue(tuple.getValuesOfType(Float.class).contains(4.0f));
        assertTrue(tuple.getValuesOfType(Double.class).contains(5.5));
        assertEquals(tuple.getValuesOfType(String.class), new Tuple("Ciao"));
        assertTrue(tuple.getValuesOfType(BigInteger.class).contains(s));
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false);
        assertEquals(0, tuple.indexOf(0));
        assertEquals(1, tuple.indexOf(1));
        assertEquals(2, tuple.indexOf(2));
        assertEquals(3, tuple.indexOf(3));
        assertEquals(4, tuple.indexOf(4));
        assertEquals(5, tuple.indexOf(5));
        assertEquals(6, tuple.indexOf(6));
        assertEquals(7, tuple.indexOf(7));
        assertEquals(8, tuple.indexOf(8));
        assertEquals(9, tuple.indexOf(9));
        assertEquals(10, tuple.indexOf("2"));
        assertEquals(11, tuple.indexOf(false));
    }

    @org.junit.jupiter.api.Test
    void pop() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false);
        tuple.pop();
        assertEquals(tuple.length(), 11);
        tuple.pop();
        assertEquals(tuple.length(), 10);
        tuple.pop();
        assertEquals(tuple.length(), 9);
        tuple.pop();
        assertEquals(tuple.length(), 8);
        tuple.pop();
        assertEquals(tuple.length(), 7);
        tuple.pop();
        assertEquals(tuple.length(), 6);
    }

    @org.junit.jupiter.api.Test
    void remove() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false);
        tuple.remove(0);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 2, false]", tuple.toString());
        tuple.remove(tuple.length() - 1);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 2]", tuple.toString());
        assertFalse(tuple.contains(false));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false, null, null);
        tuple.clear();
        assertEquals(tuple.length(), 0);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false, null, null);
        assertEquals(tuple.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 2, false]");
    }

    @org.junit.jupiter.api.Test
    void sort() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false, null, null);
        tuple.sort();
        assertEquals( "[false, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 2]",tuple.toString());
    }

    @org.junit.jupiter.api.Test
    void reverse() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false, null, null);
        tuple.reverse();
        assertEquals( "[false, 2, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]",tuple.toString());
    }

    @org.junit.jupiter.api.Test
    void slice() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false, null, null);
        tuple.slice(1, 4);
        assertEquals( "[1, 2, 3]",tuple.toString());
        tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false, null, null);
        tuple.slice(7, tuple.length());
        assertEquals( "[7, 8, 9, 2, false]", tuple.toString());
    }

    @org.junit.jupiter.api.Test
    void split() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2", null, false, null, null);
        TData tuple2 = new Tuple();
        tuple.split(7, tuple2);
        assertEquals( "[0, 1, 2, 3, 4, 5, 6]", tuple.toString());
        assertEquals( "[7, 8, 9, 2, false]", tuple2.toString());
    }

    @org.junit.jupiter.api.Test
    void join() {
        TData tuple = new Tuple(0, 1, 2, 3, 4, 5, 6);
        TData tuple2 = new Tuple(7, 8, 9, 2, null, false);
        TData tuple3 = new Tuple(null, null, "ciao");
        tuple.join(tuple2, tuple3);
        assertEquals( "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 2, false, ciao]", tuple.toString());
    }

    @org.junit.jupiter.api.Test
    void fill() {
        TData tuple = new Tuple();
        tuple.fill(1, 10);
        assertEquals( "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]", tuple.toString());
        tuple.fill(1, 10);
        assertEquals( "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]", tuple.toString());
    }

    @org.junit.jupiter.api.Test
    void isSubsetOf() {
        Tuple tuple = new Tuple(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "2");
        Tuple tuple2 = new Tuple(0, 9, 2, 3, 7, 5, 6, 4, 8, 1, "2", null, null, null, false);
        assertTrue(tuple.isSubsetOf(tuple2));
        tuple.push("1");
        assertFalse(tuple.isSubsetOf(tuple2));
    }

    @org.junit.jupiter.api.Test
    void isSupersetOf() {
        Tuple tuple = new Tuple(0, 1, 2, 3, null, false, null, null, "cuai");
        Tuple tuple2 = new Tuple(0, 1, 3, false, null, null, null, 2);
        assertTrue(tuple.isSupersetOf(tuple2));
        tuple2.pop();
        tuple2.push("1");
        assertFalse(tuple.isSupersetOf(tuple2));
    }

    @org.junit.jupiter.api.Test
    void isStrictSupersetOf() {
        Tuple tuple = new Tuple(0, 1, 2, 3, false, null, null, "cuai");
        Tuple tuple2 = new Tuple(0, 1, 2, 3, false);
        assertTrue(tuple.isStrictSupersetOf(tuple2));
        tuple2.pop();
        tuple2.push("1");
        assertFalse(tuple.isStrictSupersetOf(tuple2));
    }

    @org.junit.jupiter.api.Test
    void isStrictSubsetOf() {
        Tuple tuple = new Tuple(0, 1, 2, 3, false);
        Tuple tuple2 = new Tuple(0, 1, 2, 3, false, "cuai");
        assertTrue(tuple.isStrictSubsetOf(tuple2));
        tuple2.pop();
        tuple.push("1");
        assertFalse(tuple.isStrictSubsetOf(tuple2));
    }

    @org.junit.jupiter.api.Test
    void isDisjoint() {
        Tuple tuple = new Tuple(0, 1, 2, 3, null, false, null, null, "cuai");
        Tuple tuple2 = new Tuple(0, 1, 2, 3, null, false, null, null);
        assertFalse(tuple.isDisjoint(tuple2));
        tuple2.clear();
        tuple2.push("-1");
        assertTrue(tuple.isDisjoint(tuple2));
    }

    @org.junit.jupiter.api.Test
    void symmetricDifference() {
        Tuple tuple = new Tuple(0, 1, 2, 3, 4, 5, false, null, null, "cuai");
        Tuple tuple2 = new Tuple(0, 1, 2, 3, false, null, null);
        assertEquals("[4, 5, cuai]", tuple.symmetricDifference(tuple2).toString());
        tuple2.push("-1");
        assertEquals("[4, 5, cuai, -1]", tuple.symmetricDifference(tuple2).toString());
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        Tuple tuple = new Tuple(0, 1, 2, 3, 4, 5, false, null, null, "cuai");
        Tuple tuple2 = new Tuple(0, 1, 2, 3, false, null, null);
        assertEquals("[4, 5, cuai]", tuple.subtract(tuple2).toString());
        tuple2.push("-1");
        assertEquals("[4, 5, cuai]", tuple.subtract(tuple2).toString());
    }

    @org.junit.jupiter.api.Test
    void filter() {
        Tuple tuple = new Tuple(0, 1, 2, 3, 4, 5, false, null, null, "cuai");
        Tuple tuple2 = new Tuple(0, 1, 2, 3, false, null, null);
        assertEquals("[0, 1, 2, 3, 4, 5]", tuple.filter(o -> o instanceof Integer).toString());
        tuple2.push(-1);
        assertEquals("[-1]", tuple2.filter(o -> o instanceof Integer && (int) o < 0).toString());
    }
}