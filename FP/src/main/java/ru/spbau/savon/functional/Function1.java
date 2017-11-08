package ru.spbau.savon.functional;

/**
 * Class of function with 1 argument
 * @param <T> type of incoming argument
 * @param <R> return type
 */
public abstract class Function1<T , R> {
    /**
     * Describe of Function1
     * @param arg is an incoming argument of function
     * @return one value with any type
     */
    abstract public R apply(T arg);

    /**
     * Public method which compose two functions
     * @param g external function
     * @param <S> type of return value of function g and new function
     * @return function which is compose of two functions, g(f(x))
     */
    public <S> Function1<T, S> compose (Function1< ? super R, S> g) {
        return new Function1<T, S>() {
            @Override
            public S apply(T arg) {
                return g.apply(Function1.this.apply(arg));
            }
        };
    }
}
