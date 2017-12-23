package ru.spbau.savon.hw1.HashTable;

/**
 * Class can keep keys and it values, one key -> one value, it has public methods size, contains, get, put, remove, clear
 * @author Savon Yuliya
 */

public class HashTable {

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
    private int hash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * module + (str.charAt(i) + 1);
            hash %= places;
        }
        return hash;
    }

    private Element[] table = new Element[places]; //table with links to first elements

    /**
     * return true if key exist in HashTable otherwise return false
     * @param key
     * @return
     */
    public boolean contains(String key) {
        int place = hash(key);
        Element curentElement = table[place];
        return (curentElement != null && curentElement.contains(key) != null);
    }

    /**
     * return value if key was in HashTable otherwise return null
     * @param key
     * @return
     */
    public String get(String key) {
        int place = hash(key);
        if (table[place] == null)
            return null;
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
        Element findElement = null;
        if (table[place] != null) {
            findElement = table[place].contains(key);
        }
        if (findElement != null) { //key is already exists
            return findElement.putValue(value);
        }
        Element a = new Element(key, value, table[place]);   //when key is new
        table[place] = a;
        size++;
        return null;
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
        Element findElement = table[place].contains(key);//Element must exists
        /*if (findElement == null)
            System.out.println("We have some problems ser, it must exists!");*/
        if (table[place] == findElement) {
            table[place] = findElement.getNext();
            return findElement.getValue();
        }
        return table[place].previous(key).removeNext();
    }

    /**
     * clear all HashTable
     */
    public void clear() {
        for (int i = 0; i < places; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /*
    Help class for element in HashTable
     */
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
            if (key.equals(findKey)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.contains(findKey);
        }

        /**
         * return previous Element of Element with finding key
         * @param findKey
         * @return return Element or null if there is't element with this key
         */
        private Element previous(String findKey) {
            if (next == null) {
                return null;
            }

            if (key.equals(findKey)) { //it's first Element
                return null;
            }

            if (next.key.equals(findKey))
                return this;
            return  next.previous(findKey);
        }


        /**
         * remove element after this if it exists
         * @return value of removing element
         */
        private String removeNext() {
            String removingValue = null;
            removingValue = next.value;
            next = next.next;
            return removingValue;
        }


        /**
         * put new value in current element
         * @param newValue new value
         * @return old value
         */
        private String putValue(String newValue) {
            String lastValue = value;
            value = newValue;
            return lastValue;
        }

        /**
         * return value of Element
         * @return value of Element
         */
        private Element getNext() {
            return next;
        }
    }
}
