package ru.spbau.savon.functional;

/**
 * Class with is specification of Function1, it takes any 1 argument and return <b>Bolean</b>
 * @param <T> incoming argument
 */
public abstract class Predicate<T> extends Function1<T, Boolean> {
    /**
     * Method, which return disjunction of two predicates, lazy evaluation
     * @param p second predicate
     * @return disjunction of this predicate and predicate from arguments
     */
    public Predicate<T> or (Predicate<T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return Predicate.this.apply(arg) || p.apply(arg);
            }
        };
    }

    /**
     * Method, which return conjunction of two predicates, lazy evaluation
     * @param p second predicate
     * @return conjunction of this predicate and predicate from arguments
     */
    public Predicate<T> and (Predicate<T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return Predicate.this.apply(arg) || p.apply(arg);
            }
        };
    }

    /**
     * Method, which return predicate with reverse values
     * @return predicate with reverse value
     */
    public Predicate<T> not () {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return !Predicate.this.apply(arg);
            }
        };
    }

    /**
     * Method which return predicate with always true return value
     * @return predicate with always true return value
     */
    public Predicate<T> ALWAYS_TRUE () {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return true;
            }
        };
    }

    /**
     * Method which return predicate with always false return value
     * @return predicate with always false return value
     */
    public Predicate<T> ALWAYS_FALSE () {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return false;
            }
        };
    }
}
