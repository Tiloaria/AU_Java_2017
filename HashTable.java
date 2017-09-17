package ru.spbau.savon.hw1.HashTable;

import static java.lang.Math.pow;

/**
 * @author Savon Yuliya
 */

public class HashTable {
    private class Element {
        private String key;
        private String value;          //field for value of string
        private Element next = null;   //this is link to next Element in backet

        private Element(String newKey, String newValue, Element nextElement) {
            key = newKey;
            value = newValue;
            next = nextElement;
        }

        private String getValue() {
            return value;
        }

        /**
         * return Element with necessary key or null
         * @param findKey
         * @return
         */
        private Element contains(String findKey) {
            if (key == findKey) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.contains(findKey);
        }

        /**
         * return previous Element with key of current Element
         * @param findKey
         * @return
         */
        private Element previous(String findKey) {
            if(next == null) {
                return null;
            }
            if(next.key == findKey)
                return this;
            return  next.previous(findKey);
        }

        /**
         * put new value in current Element
         * @param newValue
         * @return
         */
        private String put(String newValue) {
            String oldValue = value;
            value = newValue;
            return oldValue;
        }

        /**
         * remove current Element
         * @param previousElement
         * @return
         */
        private String remove(Element previousElement) {
            if (previousElement != null) {
                previousElement.next = next;
            }
            return value;
        }
    }

    private int places = 1_000_003; //size of HashTable
    private int module = 281;
    private int size = 0;

    /**
     * return size of HashTable
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * return hash with count 0..places-1
     * @param str
     * @return
     */
    private int hash(String str){
        int hash = 0;
        for (int i = 0; i < str.length(); i++){
            hash = hash * module + (str.charAt(i) + 1);
            hash %= places;
        }
        return hash;
    }

    private Element [ ] table = new Element[places]; //table with links to first elements

    /**
     * return true if key exist in HashTable otherwise return false
     * @param key
     * @return
     */
    public boolean contains(String key) {
        int place = hash(key);
        Element curentElement = table[place];
        if (curentElement == null || curentElement.contains(key) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * return value if key was in HashTable otherwise return null
     * @param key
     * @return
     */
    public String get(String key) {
        int place = hash(key);
        Element findElement = table[place].contains(key);
        if (findElement == null) {
            return null;
        }
        else {
            return findElement.getValue();
        }
    }

    /**
     * put Element in HashTable if it wasn't there and replace the value and return previous value if key was in HashTable
     * @param key
     * @param value
     * @return
     */
    public String put(String key, String value) {
        int place = hash(key);
        Element findElement = table[place];
        if(findElement != null)
            findElement = findElement.contains(key);
        if(findElement == null) {
            Element a = new Element(key, value, table[place]);   //when key is new
            table[place] = a;
            size++;
            return null;
        }
        return findElement.put(value);
    }

    /**
     * remove element from HashTable and return value or return null if element wasn't in HashTable
     * @param key
     * @return
     */
    public String remove(String key) {
        if (contains(key) == false) {
            return null;
        }
        size--;
        int place = hash(key);
        Element findElement = table[place].contains(key);
        if (table[place] == findElement) {
            table[place] = findElement.next;
            return findElement.getValue();
        }
        return findElement.remove(table[place].previous(key));
    }

    /**
     * clear all HashTable
     */
    public void clear() {
        for (int i = 0; i < size; i++){
            table[i] = null;
        }
        size = 0;
    }

    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.size());
        System.out.println(h.put("Savon", "Yuliya"));
        System.out.println(h.contains("Savon"));
        System.out.println(h.contains("False"));
        System.out.println(h.size());
        System.out.println(h.remove("Savon"));
        System.out.println(h.size());
    }
}
