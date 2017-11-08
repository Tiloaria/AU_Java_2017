package ru.spbau.savon.functional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for static methods map(), filter(), takeWhile(), takeUnless(), foldr(), foldl()
 */
public class Collections {
    static <T, S> List<S> map (Function1<T, S> f, Iterable<T> a) {
        List<S> ans = new LinkedList<>();
        for (T t: a) {
            ans.add(f.apply(t));
        }
        return ans;
    }

    static <T> List<T> filter (Predicate<T> p, Iterable<T> a) {
        List<T> ans = new LinkedList<>();
        for (T t: a) {
            if (p.apply(t)) {
                ans.add(t);
            }
        }
        return ans;
    }
    static <T> List<T> takeWhile (Predicate<T> p, Iterable<T> a) {
        List<T> ans = new LinkedList<>();
        Boolean allTrue = true;
        for (T t: a) {
            if(allTrue) {
                if(p.apply(t)) {
                    ans.add(t);
                }
                allTrue = p.apply(t);
            }
        }
        return ans;
    }

    static <T>  List<T> takeUnless (Predicate<T> p, Iterable<T> a) {
        return Collections.takeWhile(p.not(), a);
    }

    static <T, S> S foldl (Function2<S, T, S> f, S start, Iterable<T> a) {
        S ans = start;
        for (T t: a) {
            ans = f.apply(ans, t);
        }
        return ans;
    }

    static <T, S> S foldr (Function2<T, S, S> f, S start, Collection<T> a) {
        ArrayList<T> list = new ArrayList<T>(a);
        java.util.Collections.reverse(list);
        S ans = start;
        for (T t: a) {
            ans = f.apply(t, ans);
        }
        return ans;
    }
}
