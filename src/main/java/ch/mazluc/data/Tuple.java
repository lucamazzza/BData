package ch.mazluc.data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.lang.System.arraycopy;
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
/**
 * <p>
 * This class represents a tuple of values.
 * A tuple is a sequence of values that can be
 * accessed by index, it is weakly typed and
 * is also iterable.
 * Implements the {@link Data} interface
 *
 * <p>
 * Usage:
 *
 * <pre>
 * {@code
 * Tuple tuple = new Tuple(); // create an empty tuple
 * tuple.push(1); // add a value
 * tuple.push(2); // add another value
 * tuple.push(3); // add another value
 * for (int i = 0; i < tuple.length(); i++) { // iterate on the tuple
 *     System.out.println(tuple.getValue(i));
 * }
 * }
 * </pre>
 *
 * <p>
 * See Repo for more: <a href="https://github.com/lucamazzza/BData">GitHub</a>
 *
 * @author Luca Mazza
 * @version 1.0
 */
public class Tuple implements Data {

    /**
     * Message when an object is not a tuple
     */
    private static final String NOT_A_TUPLE = "Object is not a tuple";

    /**
     * Random generator.
     * Used to generate random values throughout the class.
     */
    private static final Random random = new Random();

    /**
     * The values of the tuple.
     * Values are stored in an array of Objects, as they can be of any type.
     *
     * <p>
     * The values of the tuple can be accessed by index.
     */
    private Object[] values;

    /**
     * Creates a new instance of a Tuple.
     *
     * <p>
     * Instantiates an empty array of values, with a length of 0.
     */
    public Tuple() {
        this.values = new Object[0];
    }

    /**
     * Creates a new instance of tuple.Tuple
     * Defines the values of the tuple
     *
     * @param values the values of the tuple
     * 
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
    static boolean isTuple(Object obj) {
        return obj.getClass() == Tuple.class;
    }

    /**
     * Returns the length of the tuple
     *
     * @return the length of the tuple
     * 
     */
    @Override
    public int length() {
        return this.values.length;
    }

    /**
     * Returns if the tuple is empty
     *
     * @return if the tuple is empty
     * 
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
     * 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && !isTuple(obj)) {
            return false;
        }
        Tuple tuple = (Tuple) obj;
        assert tuple != null;
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
     * 
     */
    @Override
    public <T> boolean contains(T value) {
        return this.indexOf(value) != -1;
    }

    /**
     * Returns if the tuple contains a value of the specified type
     *
     * @param type the type of the value
     * @return if the tuple contains a value of the specified type
     * 
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
     * 
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
     * Appends a value of T type to the tuple
     *
     * @param <T> value the value to append
     * 
     */
    @Override
    public <T> void push(T value) {
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
     * 
     */
    public <T> void insert(int index, T value) throws IndexOutOfBoundsException {
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
     * 
     */
    public <T> void replace(int index, T value) throws IndexOutOfBoundsException {
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
     * 
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
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.values.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + this.values.length);
        }
        return (T) this.values[index];
    }

    /**
     * Returns a new tuple with only the values of the specified type
     *
     * @param type the type of the values (to pass as `Class.class`, for example `Integer.class`)
     * @return a new tuple with only the values of the specified type
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
     */
    public <T> int indexOf(T value) {
        for (int i = 0; i < this.values.length; i++) {
            if (value instanceof String && this.values[i].equals(value.toString())) {
                return i;
            }
            if (value instanceof Tuple && this.values[i].equals(value)) {
                return i;
            }
            if (this.values[i] != null && this.values[i].equals(value)) {
                return i;
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
     * 
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
     * 
     */
    @Override
    public void clear() {
        this.values = new Object[0];
    }

    /**
     * Clears T null values from the array.
     */
    private void clearNulls() {
        if (this.values.length == 0) {
            return;
        }
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == null) {
                this.remove(i);
            }
        }
    }

    /**
     * Returns the string representation of the tuple
     *
     * @return the string representation of the tuple
     * 
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
     * 
     */
    @Override
    public int hashCode() {
        if (this.values.length == 0) {
            return 0;
        }
        return Arrays.hashCode(this.values);
    }

    /**
     * Reverses the tuple
     *
     * 
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
     * 
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
     * 
     */
    public void split(int index, Object data) throws IllegalArgumentException {
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
     * 
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
    public <T> void fill(T value, int amount) {
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
            this.values[i] = random.nextInt(100);
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

            this.values[i] = random.nextInt(max - min + 1) + min;
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
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
    public Object symmetricDifference(Object data) throws IllegalArgumentException {
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
        if (!isTuple(data)){ throw new IllegalArgumentException(NOT_A_TUPLE); }
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
                if (!hasNext()) { throw new NoSuchElementException(); }
                return values[currentIndex++];
            }
        };
    }

}