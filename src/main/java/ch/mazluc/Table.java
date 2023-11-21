package ch.mazluc;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Predicate;

import static java.lang.System.arraycopy;

public class Table implements Data{

    private Tuple[] values;


    public Table(){
        this.values = new Tuple[0];
    }

    public Table(int size){
        this.values = new Tuple[size];
    }

    public Table(Tuple... values){
        this.values = values;
    }

    /**
     * Retrieves the length of the object.
     *
     * @return the length of the object.
     */
    @Override
    public int length() {
        return this.values.length;
    }

    /**
     * Checks if the object is empty.
     *
     * @return true if the object is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.values.length == 0;
    }

    /**
     * Determines whether the function contains the specified value.
     *
     * @param value the value to be checked if it is contained in the function
     * @return true if the value is contained in the function, otherwise false
     */
    @Override
    public <Any> boolean contains(Any value) {
        for (Tuple tuple : this.values) {
            if (tuple.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the values of the collection.
     *
     * @param values an array of values to be set
     */
    public <Any> void setValues(Any... values) {

    }

    /**
     * Pushes a value onto the collection.
     *
     * @param value the value to be pushed onto the stack
     */
    @Override
    public <Any> void push(Any value) throws IllegalArgumentException {
        if (this.values.length == 0) {
            this.values = new Tuple[1];
            this.values[0] = new Tuple();
        }
        if (value.getClass() != Tuple.class) { throw new IllegalArgumentException("Object is not a tuple"); }
        this.values[this.values.length - 1].push(value);
    }

    /**
     * Inserts a new value at the specified index
     *
     * @param row index to insert the value into
     * @param col index to insert the value into
     * @param value value to insert
     */
    public <Any> void insert(int row, int col, Any value) throws IndexOutOfBoundsException {
        this.values[row].insert(col, value);
    }

    /**
     * Replaces the element at the specified index with the given value.
     *
     * @param row the index of the element to be replaced
     * @param col the index of the element to be replaced
     * @param value the value to replace the element with
     */
    public <Any> void replace(int row, int col, Any value) throws IndexOutOfBoundsException {
        this.values[row].replace(col, value);
    }

    /**
     * Swaps the rows at the specified indices in the array.
     *
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    @Override
    public void swap(int index1, int index2) throws IndexOutOfBoundsException {
        if (index1 < 0 || index2 < 0 || index1 >= this.values.length || index2 >= this.values.length) {
            throw new IndexOutOfBoundsException("Index out of bounds for length " + this.values.length);
        }
        Tuple temp = this.values[index1];
        this.values[index1] = this.values[index2];
        this.values[index2] = temp;
    }

    /**
     * Retrieves the value at the specified index.
     *
     * @param row the index of the value
     * @param col the index of the value
     * @return the value at the specified index
     */
    public <Any> Any getValue(int row, int col) throws IndexOutOfBoundsException {
        return this.values[row].getValue(col);
    }

    /**
     * Returns the index of a specified value
     *
     * @param value the value to search
     * @return the index of the value
     */
    @Override
    public <Any> int indexOf(Any value) {
        return 0;
    }

    /**
     * Removes the value at the last index
     */
    @Override
    public void pop() {
        if (this.values.length == 0) {
            return;
        }
        Tuple[] newValues = new Tuple[this.values.length - 1];
        arraycopy(this.values, 0, newValues, 0, this.values.length - 1);
        this.values = newValues;
    }

    /**
     * Removes the element at the specified index
     *
     * @param row the index of the element to be removed
     * @param col the index of the element to be removed
     */
    public void remove(int row, int col) throws IndexOutOfBoundsException {
        this.values[row].remove(col);
    }

    /**
     * Clears the array
     */
    @Override
    public void clear() {
        this.values = new Tuple[0];
    }

    /**
     * Slice the table from a defined row to a defined row
     *
     * @param start the index to start
     * @param end   the index to end
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
     * Joins two or more tuples
     *
     * @param datas the tuples to join
     */
    @Override
    public void join(Object... datas) {
        for (Object data : datas) {
            if (data.getClass() != Table.class) { throw new IllegalArgumentException("Object is not a table"); }
            this.push(data);
        }
    }

    /**
     * Returns true if this tuple is a subset of the given tuple
     *
     * @param data the tuple
     * @return true if this tuple is a subset of the given tuple
     */
    @Override
    public boolean isSubsetOf(Object data) {
        if (data.getClass() != Table.class) { throw new IllegalArgumentException("Object is not a table"); }
        Table table = (Table) data;
        if (this.values.length < table.values.length) {
            return false;
        }
        int counter = 0;
        int length = 0;
        for (Tuple value : this.values) {
            for (int j = 0; j < value.length(); j++) {
                if (value.contains(table.values[j])) {
                    counter++;
                    length += value.length();
                }
            }
        }
        return counter == length;
    }

    /**
     * Returns true if this tuple is a superset of the given tuple
     *
     * @param data the tuple
     * @return true if this tuple is a superset of the given tuple
     */
    @Override
    public boolean isSupersetOf(Object data) {
        if (data.getClass() != Table.class) { throw new IllegalArgumentException("Object is not a table"); }
        Table table = (Table) data;
        return table.isSubsetOf(this);
    }

    /**
     * Returns true if this tuple is a strict subset of the given tuple
     * A strict subset is a subset in which all values are the same, in the same order
     *
     * @param data the tuple
     * @return true if this tuple is a strict subset of the given tuple
     */
    @Override
    public boolean isStrictSubsetOf(Object data) {
        return false;
    }

    /**
     * Returns true if this tuple is a strict superset of the given tuple
     * A strict superset is a superset in which all values are the same, in the same order
     *
     * @param data the tuple
     * @return true if this tuple is a strict superset of the given tuple
     */
    @Override
    public boolean isStrictSupersetOf(Object data) {
        return false;
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
    public Object symmetricDifference(Object data) {
        return null;
    }

    /**
     * Subtracts the given tuple from this tuple
     *
     * @param data the tuple
     * @return the difference
     */
    @Override
    public Object subtract(Object data) {
        return null;
    }

    /**
     * Filters the tuple using `Predicates`
     *
     * @param predicate the predicate
     * @return the filtered tuple
     */
    @Override
    public Object filter(Predicate<Object> predicate) {
        return null;
    }

    /**
     * Returns true if this tuple is disjoint from the given tuple
     * Disjunction is the set of values that are in either tuple
     * but not in both
     *
     * @param data the tuple
     * @return true if this tuple is disjoint from the given tuple
     */
    @Override
    public boolean isDisjoint(Object data) {
        return false;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Object> iterator() {
        return null;
    }
}
