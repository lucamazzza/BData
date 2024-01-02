package ch.mazluc.data;

import java.util.function.Predicate;
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
 * Represents a data structure.
 * A data structure is a collection of values.
 * It is an extension of the {@link Iterable} interface.
 *
 * <p>
 * See Repo for more: <a href="https://github.com/lucamazzza/BData">GitHub</a>
 *
 * @author Luca Mazza
 * @version 1.0
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
     * @param <T> the type
     */
    <T>boolean contains(T value);

    /**
     * Pushes a value onto the collection.
     *
     * @param value the value to be pushed onto the stack
     * @param <T> the type
     */
    <T> void push(T value);

    /**
     * Swaps the elements at the specified indices in the array.
     *
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    void swap(int index1, int index2) throws IndexOutOfBoundsException;

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
