package ru.spbau.savon;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Class which can work with different Java classes.
 * Every element contains value or null
 * @value contains value if it's exist
 */
public class Maybe<T> {
    //value, if it exist
    private T value = null;

    private Maybe(T t) {
        value = t;
    }

    private Maybe() {
        value = null;
    }

    /**
     * Create new object with type Maybe
     * @param t   new value of Maybe
     * @param <T> type of t
     * @return object Maybe with value t
     */
    public static <T> Maybe<T> just(T t) {
        return new Maybe<T>(t);
    }

    /**
     * Create new object with out value
     * @param <T> type of value which may be in Maybe
     * @return object of Maybe
     */
    public static <T> Maybe<T> nothing() {
        return new Maybe<T>();
    }

    public T get() throws NotPresentException {
        if (!isPresent()) {
            throw new NotPresentException("Not present");
        }
        return value;
    }

    /**
     * @return is there value in Maybe or not
     */
    public boolean isPresent() {
        return !(value == null);
    }

    /**
     * @param mapper function on the value of Maybe
     * @param <U>    type of new Maybe object
     * @return a new Maybe object with a type corresponding to the return value of the mapper function
     */
    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        if (isPresent()) {
            return just(mapper.apply(value));
        }

        return Maybe.nothing();
    }

    /**
     * read numbers from string, in string may be only integer value.
     * @param s parsing string
     * @return Maybe with integer if string is correct, otherwise with null
     */
    public static Maybe<Integer> readNumber(String s) {
        Integer res = 0;
        Boolean minus = false;
        s = s.trim();

        for (int i = 0; i < s.length(); i++) {
            if (!minus && s.charAt(i) == '-') {
                if (res != 0) {
                    return Maybe.nothing();
                }
                minus = true;
                continue;
            }

            if (Character.isDigit(s.charAt(i))) {
                res = res * 10 + (int) s.charAt(i) - '0';
            } else {
                return Maybe.nothing();
            }
        }

        if (minus) {
            return Maybe.just(-res);
        }

        return Maybe.just(res);
    }

    /**
     * read numbers from file
     * @param in input file stream
     * @return ArrayList of Maybe with numbers
     */
    public static ArrayList<Maybe<Integer>> readFile(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ArrayList<Maybe<Integer>> list = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                Maybe<Integer> res = Maybe.readNumber(line);
                list.add(res);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * write numbers to file from ArrayList with Maybe
     * @param out  output stream
     * @param list ArrayList with numbers
     */
    public static void writeToFile(OutputStream out, ArrayList<Maybe<Integer>> list) {
        PrintWriter outFile = new PrintWriter(out);
        for (int i = 0; i < list.size(); i++) {
            Maybe<Integer> current = list.get(i);
            if (current.isPresent()) {
                outFile.println(current.get() * current.get());
            } else {
                outFile.println("null");
            }
        }
        outFile.close();
    }
}