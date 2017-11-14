package ru.spbau.savon.hw1.HashTable;

import org.junit.Before;

import static org.junit.Assert.*;

public class HashTableTest {
    HashTable hashTable;
    @Before
    public void SetUp(){
        hashTable = new HashTable();
        hashTable.put("Savon", "Yuliya");
        hashTable.put("Blue", "lake");
        hashTable.put("math", "camp");
    }
    @org.junit.Test
    public void size() throws Exception {
        assertEquals(3, hashTable.size());
        hashTable.put("math", "club");
        assertEquals(3, hashTable.size());
        hashTable.put("input", "string");
        assertEquals(4, hashTable.size());
    }

    @org.junit.Test
    public void contains() throws Exception {
        assertEquals(true, hashTable.contains("Blue"));
        assertEquals(false, hashTable.contains("blue"));
    }

    @org.junit.Test
    public void get() throws Exception {
        assertEquals(null, hashTable.get("savon"));
        assertEquals("camp", hashTable.get("math"));
        hashTable.put("math", "club");
        assertEquals("club", hashTable.get("math"));
    }

    @org.junit.Test
    public void put() throws Exception {
        for(int i = 0; i < 2_000_000; i++) {
            hashTable.put(Integer.toString(i), "one");
        }
    }

    @org.junit.Test
    public void remove() throws Exception {
        assertEquals(null, hashTable.remove("savon"));
        assertEquals("Yuliya", hashTable.remove("Savon"));
        assertEquals(null, hashTable.remove("Savon"));
    }

    @org.junit.Test
    public void clear() throws Exception {
        hashTable.clear();
        assertEquals(null, hashTable.get("Savon"));
        assertEquals(null, hashTable.get("Blue"));
        assertEquals(null, hashTable.get("math"));
    }

}