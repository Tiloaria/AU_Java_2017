/*
  Class which can work with different Java classes.
  Every element contains value or null
  @value contains value if it's exist
 */
public class Maybe<T> {
    private T value = null;

    private Maybe(T t){
        value = t;
    }

    private Maybe(){
        value = null;
    }

    public static <T> Maybe<T> just(T t) {
        return new Maybe<T>(t);
    }

    public static <T> Maybe<T> nothing() {
        return new Maybe<T>();
    }

    public T get() {
    }

    public boolean isPresent() {
        return !(value == null);
    }

    public <U> Maybe<U> map(Function<T, U> mapper) {
    }

}