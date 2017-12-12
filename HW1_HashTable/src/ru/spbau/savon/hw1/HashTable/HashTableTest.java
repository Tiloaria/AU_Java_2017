package ru.spbau.savon.hw1.HashTable;

import org.junit.Before;

import static org.junit.Assert.*;

public class HashTableTest {
    HashTable hashTable;

    /**
     * Every hash Table will have 3 points at start
     */
    @Before
    public void setUp() {
        hashTable = new HashTable();
        hashTable.put("Savon", "Yuliya");
        hashTable.put("Blue", "lake");
        hashTable.put("math", "camp");
    }

    /**
     * put fist element with exist key, second with new key and check size of HashTable
     * @throws Exception
     */
    @org.junit.Test
    public void size() throws Exception {
        assertEquals(3, hashTable.size());
        hashTable.put("math", "club");
        assertEquals(3, hashTable.size());
        hashTable.put("input", "string");
        assertEquals(4, hashTable.size());
    }

    /**
     * check exist pair and absent pair
     * @throws Exception
     */

    @org.junit.Test
    public void contains() throws Exception {
        assertEquals(true, hashTable.contains("Blue"));
        assertEquals(false, hashTable.contains("blue"));
    }

    /**
     * check absent value, exist key, than check recently changed key
     * @throws Exception
     */
    @org.junit.Test
    public void get() throws Exception {
        assertEquals(null, hashTable.get("savon"));
        assertEquals("camp", hashTable.get("math"));
        hashTable.put("math", "club");
        assertEquals("club", hashTable.get("math"));
    }

    /**
     * add more elements, than there are baskets in HashTable to check collision.
     * @throws Exception
     */
    @org.junit.Test
    public void put() throws Exception {
        for (int i = 0; i < 2_000_000; i++) {
            hashTable.put(Integer.toString(i), Integer.toString(i));
        }

        for (int i = 0; i < 2_000_000; i++) {
            assertEquals(true, hashTable.contains(Integer.toString(i)));
        }
}

    /**
     * Check absent key, than delete exist key and check this key.
     * Also test that after removing it return correct values, if there is collisions.
     * @throws Exception
     */
    @org.junit.Test
    public void remove() throws Exception {
        assertEquals(null, hashTable.remove("savon"));
        assertEquals("Yuliya", hashTable.remove("Savon"));
        assertEquals(null, hashTable.remove("Savon"));

        for (int i = 0; i < 2_000_000; i++) {
            hashTable.put(Integer.toString(i), Integer.toString(i));
        }

        for (int i = 0; i < 2_000_000; i++) {
            String curValue = hashTable.remove(Integer.toString(i));
            assertEquals(Integer.toString(i), curValue);
        }
    }

    /**
     * clear HashTale and check, that all existed keys was deleted
     * @throws Exception
     */
    @org.junit.Test
    public void clear() throws Exception {
        hashTable.clear();
        assertEquals(null, hashTable.get("Savon"));
        assertEquals(null, hashTable.get("Blue"));
        assertEquals(null, hashTable.get("math"));
    }
}