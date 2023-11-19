package BData.src;

/**
 * This interface represents a collection of values
 *
 * @author Luca Mazza
 * @version 1.0
 * @since 1.0
 */
public interface IData extends Iterable<Object> {

    /**
     * Retrieves the length of the object.
     * @return the length of the object.
     */
    int length();

    /**
     * Checks if the object is empty.
     * @return true if the object is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Determines whether the function contains the specified value.
     * @param value the value to be checked if it is contained in the function
     * @return true if the value is contained in the function, otherwise false
     */
    <Any>boolean contains(Any value);

    /**
     * Sets the values of the collection.
     * @param  values  an array of values to be set
     */
    <Any> void setValues(Any... values);

    /**
     * Pushes a value onto the collection.
     * @param  value  the value to be pushed onto the stack
     */
    <Any> void push(Any value);

    /**
     * Inserts a new value at the specified index
     * @param index index to insert the value into
     * @param value value to insert
     */
    <Any> void insert(int index, Any value);

    /**
     * Replaces the element at the specified index with the given value.
     * @param index the index of the element to be replaced
     * @param value the value to replace the element with
     */
    <Any> void replace(int index, Any value);

    /**
     * Swaps the elements at the specified indices in the array.
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    void swap(int index1, int index2);

    /**
     * Retrieves the value at the specified index.
     * @param index the index of the value
     * @return the value at the specified index
     */
    <Any> Any getValue(int index);

    /**
     * Returns the index of a specified value
     * @param value the value to search
     * @return the index of the value
     */
    <Any> int indexOf(Any value);

    /**
     * Removes the value at the last index
     */
    void pop();

    /**
     * Removes the element at the specified index
     * @param index the index of the element to be removed
     */
    void remove(int index);

    /**
     * Clears the array
     */
    void clear();

    /**
     * Converts the tuple to a string
     * @return the string representation of the tuple
     */
    String toString();

    /**
     * Calculates the hash code
     * @return the hash code
     */
    int hashCode();

    /**
     * Sorts the collection
     */
    void sort();

    /**
     * Reverse the order of the collection
     */
    void reverse();

    /**
     * Slice the collection from a defined start to a defined end
     * @param start the index to start
     * @param end the index to end
     */
    void slice(int start, int end);
}
