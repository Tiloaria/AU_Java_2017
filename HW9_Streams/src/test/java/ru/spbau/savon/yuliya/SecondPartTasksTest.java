package ru.spbau.savon.yuliya;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static ru.spbau.savon.yuliya.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        assertEquals(Arrays.asList("!@#$%^&*((())))_+aba1234567890-=", "aba", "This file have aba!"),
                findQuotes(Arrays.asList(
                        "folderWithTestFile/a.txt",
                        "folderWithTestFile/b.txt",
                        "folderWithTestFile/file.txt"), "aba"));
    }

    @Test
    public void testPiDividedBy4() {
        assertTrue(Math.abs(piDividedBy4() - Math.PI / 4.0) < 0.01);
    }

    @Test
    public void testFindPrinter() {
        HashMap<String, List<String>> m = new HashMap<>();

        m.put("Alex", Arrays.asList("abc", "abc", "abc"));
        m.put("Bob", Arrays.asList("aa", "asdf", "ab"));
        m.put("Tom", Arrays.asList("aaaaaaa", "abbbbc", "bbbb"));

        assertEquals("Tom", findPrinter(m));
    }

    @Test
    public void testCalculateGlobalOrder() {
        HashMap<String, Integer> m1 = new HashMap<>();
        m1.put("Lemon", 1);
        m1.put("Banana", 2);
        m1.put("Note", 3);

        HashMap<String, Integer> m2 = new HashMap<>();
        m2.put("Pillow", 4);
        m2.put("Lemon", 9);

        HashMap<String, Integer> m3 = new HashMap<>();
        m3.put("Banana", 3);
        m3.put("Pillow", 16);

        HashMap<String, Integer> correct = new HashMap<>();
        correct.put("Lemon", 10);
        correct.put("Banana", 5);
        correct.put("Note", 3);
        correct.put("Pillow", 20);

        assertEquals(correct, calculateGlobalOrder(Arrays.asList(m1, m2, m3)));
    }
}