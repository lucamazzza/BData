package ch.mazluc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static java.lang.System.arraycopy;

/**
 * Represents a table
 *
 * @author Luca Mazza
 * @version 1.0
 * @since 1.0
 */
public class Table implements Data{

    /**
     * The values of the table
     */
    private Tuple[] values;

    /**
     * Creates a new, empty table
     */
    public Table(){
        this.values = new Tuple[0];
    }

    /**
     * Creates a new table with the specified size
     * @param size the size of the table
     */
    public Table(int rows, int cols){
        for (int i = 0; i < rows; i++) {
            this.push(new Tuple(cols));
        }
    }

    /**
     * Creates a new table with the specified values
     * @param values the values
     */
    public Table(Tuple... values){
        this.values = values;
    }

    private static boolean isTable(Object data) {
        return data.getClass() == Table.class;
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
    public <T> boolean contains(T value) {
        if (this.isEmpty()) { return false; }
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
    @SafeVarargs
    public final <T> void setValues(T... values) {
        for (T value : values) {
            if (value == null) {
                continue;
            }
            this.push(value);
        }
    }

    /**
     * Pushes a value onto the collection.
     *
     * @param value the value to be pushed onto the stack
     */
    @Override
    public <T> void push(T value) throws IllegalArgumentException {
        if (this.values.length == 0) {
            this.values = new Tuple[1];
            this.values[0] = (value instanceof Tuple) ? (Tuple) value : new Tuple(value);
            return;
        }
        Tuple[] tmp = new Tuple[this.length() + 1];
        arraycopy(this.values, 0, tmp, 0, this.length());
        tmp[tmp.length - 1] = (value instanceof Tuple) ? (Tuple) value : new Tuple(value);
        this.values = tmp;
    }

    /**
     * Inserts a new value at the specified index
     *
     * @param row index to insert the value into
     * @param col index to insert the value into
     * @param value value to insert
     */
    public <T> void insert(int row, int col, T value) throws IndexOutOfBoundsException {
        if (row < 0 || row >= this.values.length || col < 0 || col >= this.values[row].length()) {
            throw new IndexOutOfBoundsException("Index out of bounds for length " + this.values.length);
        }
        this.values[row].insert(col, value);
    }

    /**
     * Replaces the element at the specified index with the given value.
     *
     * @param row the index of the element to be replaced
     * @param col the index of the element to be replaced
     * @param value the value to replace the element with
     */
    public <T> void replace(int row, int col, T value) throws IndexOutOfBoundsException {
        if (row < 0 || row >= this.values.length || col < 0 || col >= this.values[row].length()) {
            throw new IndexOutOfBoundsException("Index out of bounds for length " + this.values.length);
        }
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
    public <T> T getValue(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || row >= this.values.length || col < 0 || col >= this.values[row].length()) {
            throw new IndexOutOfBoundsException("Index out of bounds for length " + this.values.length);
        }
        return this.values[row].getValue(col);
    }

    /**
     * Returns the index of a specified value
     *
     * @param value the value to search
     * @return the index of the value
     */
    public <T> int[] indexOf(T value) {
        int[] coordinates = new int[2];
        for (int i = 0; i < this.values.length; i++) {
            for (int j = 0; j < this.values[i].length(); j++) {
                if (this.values[i].getValue(j).equals(value)) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }
        return coordinates;
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
        if (row < 0 || row >= this.values.length || col < 0 || col >= this.values[row].length()) {
            throw new IndexOutOfBoundsException("Index out of bounds for length " + this.values.length);
        }
        this.values[row].remove(col);
    }

    /**
     * Clears the table
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
        Tuple[] newValues = new Tuple[end - start];
        arraycopy(this.values, start, newValues, 0, newValues.length);
        this.values = newValues;
    }

    /**
     * Joins two or more tables
     *
     * @param datas the tables to join
     */
    @Override
    public void join(Object... datas) {
        for (Object data : datas) {
            if (!isTable(data)) { continue; }
            for (Tuple value : ((Table) data).values) {
                this.push(value);
            }
        }
    }

    /**
     * Returns true if this table is a subset of the given table
     *
     * @param data the table
     * @return true if this table is a subset of the given table
     */
    @Override
    public boolean isSubsetOf(Object data) throws IllegalArgumentException {
        if (!isTable(data)) { throw new IllegalArgumentException("Object is not a table"); }
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
     * Returns true if this table is a superset of the given table
     *
     * @param data the table
     * @return true if this table is a superset of the given table
     */
    @Override
    public boolean isSupersetOf(Object data) throws IllegalArgumentException {
        if (!isTable(data)) { throw new IllegalArgumentException("Object is not a table"); }
        Table table = (Table) data;
        return table.isSubsetOf(this);
    }


    /**
     * Subtracts the given table from this table
     *
     * @param data the table
     * @return the difference
     */
    @Override
    public Object subtract(Object data) {
        if (!isTable(data)) { throw new IllegalArgumentException("Object is not a table"); }
        Table tmp = new Table();
        Table table = (Table) data;
        for (Tuple value : table.values) {
            for (int i = 0; i < value.length(); i++) {
                if (!this.contains(value.getValue(i))) {
                    tmp.push(value);
                }
            }
        }
        return tmp;
    }

    /**
     * Filters the table using `Predicates`
     * Returns the Tuples containing the values that match the predicate
     *
     * @param predicate the predicate
     * @return the filtered table
     */
    @Override
    public Object filter(Predicate<Object> predicate) {
        Table result = new Table();
        for (Tuple value : this.values) {
            if (predicate.test(value)) {
                result.push(value);
            }
        }
        return result;
    }

    /**
     * Returns true if this table is disjoint from the given table
     * Disjunction is the set of values that are in either table
     * but not in both
     *
     * @param data the table
     * @return true if this table is disjoint from the given table
     */
    @Override
    public boolean isDisjoint(Object data) {
        if (!isTable(data)) { throw new IllegalArgumentException("Object is not a table"); }
        Table table = (Table) data;
        for (Tuple value : table.values) {
            for (int i = 0; i < value.length(); i++) {
                if (this.contains(value.getValue(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String out = "";
        for (Object value : this.values) {
            out += value + "\n";
        }
        return out;
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
                return values[currentIndex++];
            }
        };
    }
}
