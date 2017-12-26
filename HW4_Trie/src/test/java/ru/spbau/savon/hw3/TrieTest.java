package ru.spbau.savon.hw3;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class TrieTest {
    private Trie t;

    @Before
    public void setUp() throws Exception {
        t = new Trie();
    }

    @Test
    public void add() throws Exception {
        t.add("cab");
        t.add("abc");

        assertTrue(t.contains("abc"));
        assertTrue(t.contains("cab"));
    }

    @Test
    public void contains() throws Exception {
        t.add("abc");
        t.add("abcde");

        assertTrue(t.contains("abc"));
        assertFalse(t.contains("abcd"));
        assertTrue(t.contains("abcde"));
        assertFalse(t.contains("abb"));
    }

    @Test
    public void remove() throws Exception {
        t.add("abc");
        assertTrue(t.contains("abc"));
        assertTrue(t.remove("abc"));
        assertFalse(t.contains("abc"));
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, t.size());
        t.add("abc");
        assertEquals(1, t.size());
        t.add("abc");
        assertEquals(1, t.size());
        t.remove("abc");
        assertEquals(0, t.size());
    }

    @Test
    public void howManyStartWithPrefix() throws Exception {
        assertEquals(0, t.size());
        t.add("abc");
        t.add("abc");
        t.add("abcd");
        t.add("a");
        t.add("abnon");
        t.remove("abnon");
        assertEquals(2, t.howManyStartWithPrefix("ab"));
    }

    @org.junit.Test
    public void serialize() throws Exception {
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("aa");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        trie.serialize(out);

        assertEquals(2, out.toString().charAt(0));
        assertEquals('a', out.toString().charAt(1));
        assertEquals('a', out.toString().charAt(2));
        assertEquals(0, out.toString().charAt(3));
        assertEquals('a', out.toString().charAt(4));
        assertEquals('b', out.toString().charAt(5));
        assertEquals('c', out.toString().charAt(6));
        assertEquals(0, out.toString().charAt(7));//?
    }

    @org.junit.Test
    public void deserialize() throws Exception {
        byte[] arr = {2, 'a', 'b', 'c', 0, 'a', 'b', 'c', 'd', 0, 0};

        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        Trie trie = new Trie();
        trie.deserialize(in);

        assertEquals(2, trie.size());
        assertTrue(trie.contains("abc"));
        assertTrue(trie.contains("abcd"));
    }

    @Test
    public void serializeTest() throws Exception {
        t.add("abcd");
        t.add("abcdeq");
        t.add("corrupt");
        t.add("corporation");
        t.add("correct");
        t.add("correspondent");
        t.add("corra");

        FileOutputStream out = new FileOutputStream("file.txt");
        t.serialize(out);

        FileInputStream in = new FileInputStream("file.txt");

        Trie deserializedTrie = new Trie();
        deserializedTrie.deserialize(in);

        assertEquals(7, deserializedTrie.size());
        assertTrue(deserializedTrie.contains("abcd"));
        assertTrue(deserializedTrie.contains("abcdeq"));
        assertTrue(deserializedTrie.contains("correct"));
        assertTrue(deserializedTrie.contains("correspondent"));
        assertTrue(deserializedTrie.contains("corrupt"));
        assertTrue(deserializedTrie.contains("corra"));
        assertTrue(deserializedTrie.contains("corporation"));

        File f = new File("file.txt");
        f.delete();
    }
}