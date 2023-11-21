package ch.mazluc;

import java.util.function.Predicate;

/**
 * This interface represents a collection of values
 *
 * @author Luca Mazza
 * @version 1.0
 * @since 1.0
 */
public interface Data extends Iterable<Object> {

    /**
     * Retrieves the length of the object.
     *
     * @return the length of the object.
     */
    int length();

    /**
     * Checks if the object is empty.
     *
     * @return true if the object is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Determines whether the function contains the specified value.
     *
     * @param value the value to be checked if it is contained in the function
     * @return true if the value is contained in the function, otherwise false
     * @param <Any> the type
     */
    <Any>boolean contains(Any value);

    /**
     * Pushes a value onto the collection.
     *
     * @param value the value to be pushed onto the stack
     * @param <Any> the type
     */
    <Any> void push(Any value);

    /**
     * Swaps the elements at the specified indices in the array.
     *
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    void swap(int index1, int index2) throws IndexOutOfBoundsException;

    /**
     * Returns the index of a specified value
     *
     * @param value the value to search
     * @return the index of the value
     * @param <Any> the type
     */
    <Any> int indexOf(Any value);

    /**
     * Removes the value at the last index
     */
    void pop();

    /**
     * Clears the array
     */
    void clear();

    /**
     * Slice the collection from a defined start to a defined end
     *
     * @param start the index to start
     * @param end   the index to end
     */
    void slice(int start, int end);

    /**
     * Joins two or more tuples
     *
     * @param datas the tuples to join
     */
    void join(Object... datas);

    /**
     * Returns true if this tuple is a subset of the given tuple
     *
     * @param data the tuple
     * @return true if this tuple is a subset of the given tuple
     */
    boolean isSubsetOf(Object data);

    /**
     * Returns true if this tuple is a superset of the given tuple
     *
     * @param data the tuple
     * @return true if this tuple is a superset of the given tuple
     */
    boolean isSupersetOf(Object data);

    /**
     * Returns true if this tuple is a strict subset of the given tuple
     * A strict subset is a subset in which all values are the same, in the same order
     *
     * @param data the tuple
     * @return true if this tuple is a strict subset of the given tuple
     */
    boolean isStrictSubsetOf(Object data);

    /**
     * Returns true if this tuple is a strict superset of the given tuple
     * A strict superset is a superset in which all values are the same, in the same order
     *
     * @param data the tuple
     * @return true if this tuple is a strict superset of the given tuple
     */
    boolean isStrictSupersetOf(Object data);

    /**
     * Returns the symmetric difference between this tuple and the given tuple
     * The symmetric difference is the set of values that are in either tuple
     * but not in both
     *
     * @param data the tuple
     * @return the symmetric difference
     */
    Object symmetricDifference(Object data);

    /**
     * Subtracts the given tuple from this tuple
     *
     * @param data the tuple
     * @return the difference
     */
    Object subtract(Object data);

    /**
     * Filters the tuple using `Predicates`
     *
     * @param predicate the predicate
     * @return the filtered tuple
     */
    Object filter(Predicate<Object> predicate);

    /**
     * Returns true if this tuple is disjoint from the given tuple
     * Disjunction is the set of values that are in either tuple
     * but not in both
     *
     * @param data the tuple
     * @return true if this tuple is disjoint from the given tuple
     */
    boolean isDisjoint(Object data);
}
