package ru.spbau.savon.functional;

/**
 * Class of function with 2 arguments
 * @param <T1> type of 1st argument
 * @param <T2> type of 2nd argument
 * @param <R> return type of argument
 */
public abstract class Function2 <T1, T2, R>{
    /**
     * Describe of class Function2
     * @param arg1 is 1st incoming argument
     * @param arg2 is 2nd incoming argument
     * @return one value with any type
     */
    abstract public R apply (T1 arg1, T2 arg2);

    /**
     * Public method which compose two functions
     * @param g external function which takes 1 argument and return 1 argument
     * @param <S> type of return value of function g and new function
     * @return function which is compose of two functions, g(f(x, y))
     */
    public <S> Function2<T1, T2, S> compose (Function1< ? super R, S> g) {
        return new Function2<T1, T2, S>() {
            @Override
            public S apply(T1 arg1, T2 arg2) {
                return g.apply(Function2.this.apply(arg1, arg2));
            }
        };
    }

    /**
     * Public method which income 1 argument and return function with substituted 1st argument
     * @param arg1 substituted on 1st place
     * @return function with 1 income argument, f(x, y) -> f(y)
     */
    public Function1<T2, R> bind1 (T1 arg1) {
        return new Function1<T2, R>() {
            @Override
            public R apply(T2 arg2) {
                return Function2.this.apply(arg1, arg2);
            }
        };
    }

    /**
     * Public method which income 1 argument and return function with substituted 2st argument
     * @param arg2 substituted on 2nd place
     * @return function with 1 income argument, f(x, y) -> f(x)
     */
    public Function1<T1, R> bind2 (T2 arg2) {
        return new Function1<T1, R>() {
            @Override
            public R apply(T1 arg1) {
                return Function2.this.apply(arg1, arg2);
            }
        };
    }

    /**
     * Method converts this function to 1 argument function
     * f(x, y) -> x + y, f.curry(5) -> (f(y) -> 5 + y)
     * @param arg2 substituted on 2nd place
     * @return function with 1 income argument
     */
    public Function1<T1, R> curry (T2 arg2) {
        return Function2.this.bind2(arg2);
    }
}
