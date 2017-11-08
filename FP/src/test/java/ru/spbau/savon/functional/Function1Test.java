package ru.spbau.savon.functional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Function1Test {
    Function1<Integer, Integer> f;
    Function1<Integer, Integer> g;
    @Before
    public void SetUp(){
        f = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return 5 * arg;
            }
        };

        g = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg + 3;
            }
        };
    }

    @Test
    public void compose() throws Exception {
        Function1<Integer, Integer> k = f.compose(g);
        Function1<Integer, Integer> t = g.compose(f);
    }

}