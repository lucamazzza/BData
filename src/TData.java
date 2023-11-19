package BData.src;

import java.util.function.Predicate;

public interface TData extends IData{

    boolean equals(Tuple tuple);

    boolean containsType(Class<?> type);

    Tuple getValuesOfType(Class<?> type);

    void trim();

    void join(Tuple... tuples);

    <Any>void fill(Any value, int amount);

    <Any>void fillRandom(int amount);

    <Any>void fillRandom(int min, int max, int amount);

    boolean isSubsetOf(Tuple tuple);

    boolean isSupersetOf(Tuple tuple);

    boolean isStrictSubsetOf(Tuple tuple);

    boolean isStrictSupersetOf(Tuple tuple);

    Tuple symmetricDifference(Tuple tuple);

    Tuple subtract(Tuple tuple);

    Tuple filter(Predicate<Object> predicate);

    boolean isDisjoint(Tuple tuple);
}
