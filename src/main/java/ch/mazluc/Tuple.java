package ch.mazluc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.lang.System.arraycopy;

/**
 * This class represents a tuple of values
 *
 * @author Luca Mazza
 * @version 1.0
 * @since 1.0
 */
public class Tuple implements Data {

    /**
     * Constant containing the types of values that the tuple can contain
     * @since 1.0
     */
    private static final Class<?>[] TYPES = {
            Boolean.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            BigInteger.class,
            BigDecimal.class,
            Character.class,
            String.class,
            Object[].class,
            Tuple.class,
    };

    /**
     * The values of the tuple
     *
     * @since 1.0
     */
    private Object[] values;

    /**
     * Creates a new instance of tuple.Tuple
     *
     * @since 1.0
     */
    public Tuple() {
        this.values = new Object[0];
    }

    /**
     * Creates a new instance of tuple.Tuple
     * Defines the values of the tuple
     *
     * @param values the values of the tuple
     * @since 1.0
     */
    public Tuple(Object... values) {
        if (values.length == 0) {
            this.values = new Object[0];
            return;
        }
        this.values = new Object[values.length];
        arraycopy(values, 0, this.values, 0, values.length);
        this.clearNulls();
    }

    /**
     * Determines if the given object is an instance of Tuple.
     *
     * @param obj the object to be checked
     * @return  true if the object is an instance of Tuple, false otherwise
     */
    private static boolean isTuple(Object obj) {
        return obj.getClass() == Tuple.class;
    }

    /**
     * Sorts the values of a determined type and returns a tuple containing them
     * Type `Array`, `tuple.Tuple` and `Object` are not sorted
     *
     * @param values the tuple
     * @param type   the type
     * @return a tuple with the sorted Typed values
     * @since 1.0
     */
    private static Tuple sortType(Object values, Class<?> type) {
        if (!isTuple(values)) {
            return new Tuple();
        }
        Tuple vals = (Tuple) values;
        Tuple tuple = vals.getValuesOfType(type);
        for (int i = 1; i < tuple.length(); i++) {
            if (type == TYPES[0]) { // boolean
                if ((boolean) tuple.getValue(i) && !(boolean) tuple.getValue(i - 1)) {
                    tuple.swap(i, i - 1);
                    i = 0;
                }
            } else if (
                    type == TYPES[1]
                            || type == TYPES[2]
                            || type == TYPES[3]
                            || type == TYPES[4]
                            || type == TYPES[5]
                            || type == TYPES[6]
            ) { // Number
                if (
                        (type == TYPES[1] && (byte) tuple.getValue(i) < (byte) tuple.getValue(i - 1))
                                || (type == TYPES[2] && (short) tuple.getValue(i) < (short) tuple.getValue(i - 1))
                                || (type == TYPES[3] && (int) tuple.getValue(i) < (int) tuple.getValue(i - 1))
                                || (type == TYPES[4] && (long) tuple.getValue(i) < (long) tuple.getValue(i - 1))
                                || (type == TYPES[5] && (float) tuple.getValue(i) < (float) tuple.getValue(i - 1))
                                || (type == TYPES[6] && (double) tuple.getValue(i) < (double) tuple.getValue(i - 1))
                ) {
                    tuple.swap(i, i - 1);
                    i = 0;
                }
            } else if (type == TYPES[7] || type == TYPES[8]) { // BigNumber
                if (
                        (type == TYPES[7] &&
                                ((BigInteger) tuple.getValue(i)).compareTo(tuple.getValue(i - 1)) < 0) ||
                                (type == TYPES[8] &&
                                        ((BigDecimal) tuple.getValue(i)).compareTo(tuple.getValue(i - 1)) < 0)
                ) {
                    tuple.swap(i, i - 1);
                    i = 0;
                }
            } else if (type == TYPES[9]) { // Character
                if ((char) tuple.getValue(i) < (char) tuple.getValue(i - 1)) {
                    tuple.swap(i, i - 1);
                    i = 0;
                }
            } else if (type == TYPES[10]) { // String
                if (((String) tuple.getValue(i)).compareTo(tuple.getValue(i - 1)) < 0) {
                    tuple.swap(i, i - 1);
                    i = 0;
                }
            } else {
                return tuple;
            }
        }
        return tuple;
    }

    /**
     * Returns the length of the tuple
     *
     * @return the length of the tuple
     * @since 1.0
     */
    @Override
    public int length() {
        return this.values.length;
    }

    /**
     * Returns if the tuple is empty
     *
     * @return if the tuple is empty
     * @since 1.0
     */
    @Override
    public boolean isEmpty(){
        return this.values.length == 0;
    }

    /**
     * Returns if the tuple is equal to another
     *
     * @param obj the tuple to compare
     * @return if the tuple is equal to another
     * @since 1.0
     */
    @Override
    public boolean equals(Object obj) {
        if (!isTuple(obj)) {
            return false;
        }
        Tuple tuple = (Tuple) obj;
        if(this.values.length != tuple.values.length){
            return false;
        }
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] != tuple.values[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the given value is contained within the collection.
     *
     * @param value the value to check for containment
     * @return true if the value is contained, false otherwise
     * @since 1.0
     */
    @Override
    public <Any> boolean contains(Any value) {
        return this.indexOf(value) != -1;
    }

    /**
     * Returns if the tuple contains a value of the specified type
     *
     * @param type the type of the value
     * @return if the tuple contains a value of the specified type
     * @since 1.0
     */
    public boolean containsType(Class<?> type) {
        for (Object value : this.values) {
            if (value == null) {
                return false;
            }
            if (value.getClass() == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces the values of the tuple
     *
     * @param values the new values of the tuple
     * @since 1.0
     */
    public void setValues(Object... values){
        if (values.length == 0) {
            this.values = new Object[0];
            return;
        }
        this.values = values;
        this.clearNulls();
    }

    /**
     * Appends a value of any type to the tuple
     *
     * @param <Any> value the value to append
     * @since 1.0
     */
    @Override
    public <Any> void push(Any value) {
        if (value == null) {
            return;
        }
        Object[] newValues = new Object[this.values.length + 1];
        arraycopy(this.values, 0, newValues, 0, this.values.length);
        newValues[this.values.length] = value;
        this.values = newValues;
        this.clearNulls();
    }

    /**
     * Inserts a new value at the specified index
     *
     * @param index index to insert the value into
     * @param value value to insert
     * @since 1.0
     */
    public <Any> void insert(int index, Any value) throws IndexOutOfBoundsException {
        if (value == null) {
            return;
        }
        Object[] newValues = new Object[this.values.length + 1];
        arraycopy(this.values, 0, newValues, 0, index);
        newValues[index] = value;
        arraycopy(this.values, index, newValues, index + 1, this.values.length - index);
        this.values = newValues;
        this.clearNulls();
    }

    /**
     * Replaces the value at the specified index
     *
     * @param index the index of the value
     * @param value the new value
     * @since 1.0
     */
    public <Any> void replace(int index, Any value) throws IndexOutOfBoundsException {
        if (value == null) {
            return;
        }
        if (index < 0 || index >= this.values.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + this.values.length);
        }
        this.values[index] = value;
        this.clearNulls();
    }

    /**
     * Swaps the elements at the specified indices in the array.
     *
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     * @since 1.0
     */
    @Override
    public void swap(int index1, int index2) throws IndexOutOfBoundsException {
        if (index1 < 0 || index2 < 0 || index1 >= this.values.length || index2 >= this.values.length) {
            throw new IndexOutOfBoundsException("Index out of bounds for length " + this.values.length);
        }
        Object temp = this.values[index1];
        this.values[index1] = this.values[index2];
        this.values[index2] = temp;
    }

    /**
     * Returns the value at the specified index of the tuple
     * Value is already typed, but be careful, put your values
     * in a rightful type variable
     *
     * @param index the index of the value
     * @return the value at the specified index
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public <Any> Any getValue(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.values.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + this.values.length);
        }
        return (Any) this.values[index];
    }

    /**
     * Returns the values of the tuple
     * Do not use externally, Arrays of
     * Objects are not as good as you think
     *
     * @return the values of the tuple
     * @since 1.0
     */
    private Object[] getValues() {
        return this.values;
    }

    /**
     * Returns a new tuple with only the values of the specified type
     *
     * @param type the type of the values (to pass as `Class.class`, for example `Integer.class`)
     * @return a new tuple with only the values of the specified type
     * @since 1.0
     */
    public Tuple getValuesOfType(Class<?> type) {
        Tuple tuple = new Tuple();
        if (!containsType(type)) {
            return tuple;
        }
        Tuple copy = new Tuple(this.values); // Create a copy of the original tuple
        for (int i = 0; i < copy.length(); i++) {
            if (copy.getValue(i) == null) {
                copy.remove(i);
            }
        }
        for (Object value : copy) {
            if (value.getClass() == type) {
                tuple.push(value);
            }
        }
        return tuple;
    }

    /**
     * Returns the index of a specified value
     *
     * @param value the value to search
     * @return the index of the value
     * @since 1.0
     */
    @Override
    public <Any> int indexOf(Any value) {
        for (int i = 0; i < this.values.length; i++) {
            if (value instanceof String) {
                if (this.values[i].equals(value.toString())) {
                    return i;
                }
            }
            if (value instanceof Tuple) {
                if (this.values[i].equals(value)) {
                    return i;
                }
            }
            if (value != null && this.values[i] != null) {
                if (this.values[i].equals(value)) {
                    return i;
                }
            }
            if (this.values[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes the last value of the tuple
     */
    @Override
    public void pop() {
        if (this.values.length == 0) {
            return;
        }
        Object[] newValues = new Object[this.values.length - 1];
        arraycopy(this.values, 0, newValues, 0, this.values.length - 1);
        this.values = newValues;
        this.clearNulls();
    }

    /**
     * Removes the element at the specified index from the array of values.
     *
     * @param index the index of the element to be removed
     * @since 1.0
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        if (this.values.length == 0) {
            return;
        }
        if (index < 0 || index >= this.values.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newValues = new Object[this.values.length - 1];
        arraycopy(this.values, 0, newValues, 0, index);
        if (this.values.length - 1 - index >= 0)
            arraycopy(this.values, index + 1, newValues, index, this.values.length - 1 - index);
        this.values = newValues;
        this.clearNulls();
    }

    /**
     * Clears the tuple, making it of length 0
     *
     * @since 1.0
     */
    @Override
    public void clear() {
        this.values = new Object[0];
    }

    /**
     * Clears any null values from the array.
     */
    private void clearNulls() {
        if (this.values.length == 0) {
            return;
        }
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == null) {
                this.remove(i);
                i--;
            }
        }
    }

    /**
     * Returns the string representation of the tuple
     *
     * @return the string representation of the tuple
     * @since 1.0
     */
    @Override
    public String toString() {
        if (this.values.length == 0) {
            return "[]";
        }
        return Arrays.toString(this.values);
    }

    /**
     * Returns the hash code of the tuple
     *
     * @return the hash code of the tuple
     * @since 1.0
     */
    @Override
    public int hashCode() {
        if (this.values.length == 0) {
            return 0;
        }
        return Arrays.hashCode(this.values);
    }

    /**
     * Sorts the tuple, grouping by Type, in this order
     *
     * <p>Types:
     * - boolean
     * - byte
     * - short
     * - integer
     * - long
     * - float
     * - double
     * - BigInteger
     * - BigDecimal
     * - Character
     * - String
     * - tuple.Tuple
     * - Object and Arrays
     * - Null
     * </p>
     *
     * @since 1.0
     */
    public void sort() {
        if (this.values.length == 0) {
            return;
        }
        Tuple tuple = new Tuple();
        Tuple booleans = Tuple.sortType(this, TYPES[0]);
        Tuple bytes = Tuple.sortType(this, TYPES[1]);
        Tuple shorts = Tuple.sortType(this, TYPES[2]);
        Tuple integers = Tuple.sortType(this, TYPES[3]);
        Tuple longs = Tuple.sortType(this, TYPES[4]);
        Tuple floats = Tuple.sortType(this, TYPES[5]);
        Tuple doubles = Tuple.sortType(this, TYPES[6]);
        Tuple bigIntegers = Tuple.sortType(this, TYPES[7]);
        Tuple bigDecimals = Tuple.sortType(this, TYPES[8]);
        Tuple characters = Tuple.sortType(this, TYPES[9]);
        Tuple strings = Tuple.sortType(this, TYPES[10]);
        Tuple tuples = Tuple.sortType(this, TYPES[11]);
        Tuple objects = Tuple.sortType(this, TYPES[12]);
        Tuple nulls = Tuple.sortType(this, null);
        tuple.join(
                booleans,
                bytes,
                shorts,
                integers,
                longs,
                floats,
                doubles,
                bigIntegers,
                bigDecimals,
                characters,
                strings,
                tuples,
                objects,
                nulls);
        this.clear();
        for (Object value : tuple) {
            this.push(value);
        }
    }

    /**
     * Reverses the tuple
     *
     * @since 1.0
     */
    public void reverse() {
        if (this.values.length == 0 || this.values.length == 1) {
            return;
        }
        Object[] newValues = new Object[this.values.length];
        for (int i = 0; i < this.values.length; i++) {
            newValues[i] = this.values[this.values.length - i - 1];
        }
        this.setValues(newValues);
    }

    /**
     * Slices the tuple from a defined start to a defined end
     *
     * @param start the first index
     * @param end   the last index
     * @since 1.0
     */
    @Override
    public void slice(int start, int end) {
        if (end > this.values.length) {
            end = this.values.length;
        }
        if (start < 0) {
            start = 0;
        }
        Object[] newValues = new Object[end - start];
        arraycopy(this.values, start, newValues, 0, newValues.length);
        this.setValues(newValues);
    }

    /**
     * Splits the tuple at the specified index
     * The new tuple has the same length as the old one
     *
     * @param index the index to split
     * @param data the new tuple
     * @since 1.0
     */
    public void split(int index, Object data) throws IllegalArgumentException {
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        if (index > this.values.length) {
            index = this.values.length;
        }
        if (index < 0) {
            index = 0;
        }
        Tuple tmp = (Tuple) data;
        Object[] newValues = new Object[index];
        Object[] newValues2 = new Object[this.values.length - index];
        arraycopy(this.values, index, newValues2, 0, this.values.length - index);
        tmp.setValues(newValues2);
        arraycopy(this.values, 0, newValues, 0, index);
        this.setValues(newValues);
    }

    /**
     * Joins two tuples into one
     *
     * @param datas the tuples to join
     * @since 1.0
     */
    @Override
    public void join(Object... datas) {
        for (Object data : datas) {
            if (!isTuple(data)) {
                continue;
            }
            Tuple tmp = (Tuple) data;
            for (int i = 0; i < tmp.length(); i++) {
                this.push(tmp.getValue(i));
            }
        }
    }

    /**
     * Fills the array with the specified value.
     *
     * @param value the value to fill the array with
     */
    public <Any> void fill(Any value, int amount) {
        if (amount < 0) { amount = 0; }
        if (amount == 0 || value == null) { return; }
        for (int i = 0; i < amount; i++) {
            this.push(value);
        }
    }

    /**
     * Fills the array with random values.
     *
     * @param amount the amount of random values to generate
     */
    public void fillRandom(int amount) {
        if (amount < 0 || amount > this.values.length) {
            amount = 0;
        }
        if (amount == 0) {
            return;
        }
        for (int i = 0; i < amount; i++) {
            this.values[i] = (int) (Math.random() * 100);
        }
    }

    /**
     * Fills the values array with random integers between 0 and 99 (inclusive).
     */
    public void fillRandom(int min, int max, int amount) {
        if (amount < 0 || amount > this.values.length) {
            amount = 0;
        }
        if (amount == 0) {
            return;
        }
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        for (int i = 0; i < amount; i++) {
            this.values[i] = (int) (Math.random() * (max - min + 1) + min);
        }
    }

    /**
     * Determines if this tuple is a subset of the given tuple.
     *
     * @param data the tuple to check against
     * @return true if this tuple is a subset of the given tuple, false otherwise
     */
    @Override
    public boolean isSubsetOf(Object data) throws IllegalArgumentException{
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        if (this.length() > ((Tuple) data).length()) {
            return false;
        }
        Tuple tmp = (Tuple) data;
        for (int i = 0; i < this.length(); i++) {
            if (!tmp.contains(this.getValue(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if this tuple is a superset of the given tuple.
     *
     * @param data the tuple to check against
     * @return true if this tuple is a superset of the given tuple, false otherwise
     */
    @Override
    public boolean isSupersetOf(Object data) throws IllegalArgumentException {
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        if (this.length() < ((Tuple) data).length()) {
            return false;
        }
        Tuple tmp = (Tuple) data;
        return tmp.isSubsetOf(this);
    }

    /**
     * Determines if this set is a strict superset of the given tuple.
     * A strict superset means that this set contains all the values
     * of the given tuple in the same order
     *
     * @param data the tuple to compare against
     * @return true if this set is a strict superset of the given tuple, false otherwise
     */
    public boolean isStrictSupersetOf(Object data) throws IllegalArgumentException {
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        if (this.length() < ((Tuple) data).length()) {
            return false;
        }
        int consecutive = 0;
        Tuple tmp = (Tuple) data;
        for (int i = 0; i < tmp.length(); i++) {
            if (this.contains(tmp.getValue(i))) {
                consecutive++;
            } else {
                consecutive = 0;
            }
        }
        return consecutive == tmp.length();
    }

    /**
     * Determines if this set is a strict subset of the given tuple.
     * A strict subset means that this set contains all the values
     * of the given tuple in the same order
     *
     * @param data the tuple to compare against
     * @return true if this set is a strict subset of the given tuple, false otherwise
     */
    public boolean isStrictSubsetOf(Object data) throws IllegalArgumentException{
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        if (this.length() > ((Tuple) data).length()) {
            return false;
        }
        Tuple tmp = (Tuple) data;
        int consecutive = 0;
        for (int i = 0; i < this.length(); i++) {
            if (tmp.contains(this.getValue(i))){
                consecutive++;
            } else {
                consecutive = 0;
            }
        }
        return consecutive == this.length();
    }

    /**
     * Checks if the current tuple.Tuple is disjoint with the given tuple.Tuple.
     *
     * @param data the tuple.Tuple to check for disjointness
     * @return true if the Tuples are disjoint, false otherwise
     */
    @Override
    public boolean isDisjoint(Object data) throws IllegalArgumentException{
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        if (data.equals(this)) {
            return false;
        }
        Tuple tmp = (Tuple) data;
        for (int i = 0; i < tmp.length(); i++) {
            if (this.contains(tmp.getValue(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the symmetric difference between this tuple and the given tuple
     * The symmetric difference is the set of values that are in either tuple
     * but not in both
     *
     * @param data the tuple
     * @return the symmetric difference
     */
    @Override
    public Object symmetricDifference(Object data) throws IllegalArgumentException {
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        Tuple tuple = (Tuple) data;
        Tuple result = new Tuple();
        for (int i = 0; i < this.length(); i++) {
            if (!tuple.contains(this.getValue(i))) {
                result.push(this.getValue(i));
            }
        }
        for (int i = 0; i < tuple.length(); i++) {
            if (!this.contains(tuple.getValue(i))) {
                result.push(tuple.getValue(i));
            }
        }
        return result;
    }

    /**
     * Subtracts the given tuple from this tuple
     *
     * @param data the tuple
     * @return the result
     */
    @Override
    public Object subtract(Object data) throws IllegalArgumentException {
        if (!isTuple(data)){ throw new IllegalArgumentException("Object is not a tuple"); }
        Tuple tmp = (Tuple) data;
        Tuple result = new Tuple();
        for (int i = 0; i < this.length(); i++) {
            if (!tmp.contains(this.getValue(i))){
                result.push(this.getValue(i));
            }
        }
        return result;
    }

    /**
     * Filters the elements of the tuple.Tuple based on the given predicate.
     *
     * @param predicate the predicate used to filter the elements of the tuple.Tuple
     * @return the filtered tuple.Tuple
     */
    @Override
    public Object filter(Predicate<Object> predicate){
        Tuple result = new Tuple();
        IntStream.range(
                0,
                this.length()
        )
        .filter(
            i -> predicate.test(this.getValue(i))
        ).mapToObj(
                this::getValue
        ).forEachOrdered(result::push);

        return result;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     * @since 1.0
     */
    @Override
    public Iterator<Object> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length() && values[currentIndex] != null;
            }

            @Override
            public Object next() {
                return values[currentIndex++];
            }
        };
    }

}