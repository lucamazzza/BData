package BData.src;

import java.util.function.Predicate;

/**
 * BlackBox to access and operate on Tuples
 * @author Luca Mazza
 * @version 1.0
 * @since 1.0
 */
public interface TData extends Data {

    /**
     * Returns if the tuple contains a value of the specified type
     * @param type the type of the value
     * @return if the tuple contains a value of the specified type
     */
    boolean containsType(Class<?> type);

    /**
     * Returns a new tuple with only the values of the specified type
     * @param type the type of the values (to pass as `<Class>.class`, for example `Integer.class`)
     * @return a new tuple with only the values of the specified type
     */
    TData getValuesOfType(Class<?> type);

    /**
     * Joins two or more tuples
     * @param tuples the tuples to join
     */
    void join(TData... tuples);

    /**
     * Splits the tuple at the specified index
     * The new tuples combined have the same length as the old one
     * @param index the index to split
     * @param data the new tuple
     */
    void split(int index, TData data);

    /**
     * Populates the tuple with the specified value for the specified amount
     * @param value the value
     * @param amount the amount
     */
    <Any>void fill(Any value, int amount);

    /**
     * Fills the values array with random integers between 0 and 100 (exclusive).
     */
    void fillRandom(int amount);

    /**
     * Fills the values array with random integers in specified range.
     * @param min start of the range
     * @param max end of the range (exclusive)
     * @param amount the amount
     */
    void fillRandom(int min, int max, int amount);

    /**
     * Returns true if this tuple is a subset of the given tuple
     * @param tuple the tuple
     * @return true if this tuple is a subset of the given tuple
     */
    boolean isSubsetOf(TData tuple);

    /**
     * Returns true if this tuple is a superset of the given tuple
     * @param tuple the tuple
     * @return true if this tuple is a superset of the given tuple
     */
    boolean isSupersetOf(TData tuple);

    /**
     * Returns true if this tuple is a strict subset of the given tuple
     * A strict subset is a subset in which all values are the same, in the same order
     * @param tuple the tuple
     * @return true if this tuple is a strict subset of the given tuple
     */
    boolean isStrictSubsetOf(TData tuple);

    /**
     * Returns true if this tuple is a strict superset of the given tuple
     * A strict superset is a superset in which all values are the same, in the same order
     * @param tuple the tuple
     * @return true if this tuple is a strict superset of the given tuple
     */
    boolean isStrictSupersetOf(TData tuple);

    /**
     * Returns the symmetric difference between this tuple and the given tuple
     * The symmetric difference is the set of values that are in either tuple
     * but not in both
     * @param tuple the tuple
     * @return the symmetric difference
     */
    TData symmetricDifference(TData tuple);

    /**
     * Subtracts the given tuple from this tuple
     * @param tuple the tuple
     * @return the difference
     */
    TData subtract(TData tuple);

    /**
     * Filters the tuple using `Predicates`
     * @param predicate the predicate
     * @return the filtered tuple
     */
    TData filter(Predicate<Object> predicate);

    /**
     * Returns true if this tuple is disjoint from the given tuple
     * Disjunction is the set of values that are in either tuple
     * but not in both
     * @param tuple the tuple
     * @return true if this tuple is disjoint from the given tuple
     */
    boolean isDisjoint(TData tuple);
}
