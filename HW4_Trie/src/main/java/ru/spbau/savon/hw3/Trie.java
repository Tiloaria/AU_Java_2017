package ru.spbau.savon.hw3;

import java.util.HashMap;

/**
 * Class can save string, add and remove strings in structure.
 * Also with fast time return size of Trie, is the string contains this array,
 * number of strings with prefix.
 *
 */
public class Trie {

    private int size; //number of strings
    private Point start; //the root of Trie

    /**
     * add string to Trie and make new Points if it's necessary
     * @param element string which we want to add
     * @return true if we add new string, false otherwise
     */
    public boolean add(String element) {
        Point current = start;
        for (int i = 0; i < element.length(); i++) {
            current = current.goTo(element.charAt(i));
        }
        if (current.setEndOfString()) {         //check if it was the same sting
            current = start;
            for (int i = 0; i < element.length(); i++) {  //update startsWithPrefix
                current.increaseStartsWithPrefix();
                current.getNext(element.charAt(i));
            }
            size++;
            return true;
        }
        return false;
    }

    /**
     * check contains sting in Trie
     * @param element is checked string
     * @return true if string contains, false otherwise
     */
    public boolean contains(String element) {
        Point current = start;
        for (int i = 0; i < element.length(); i++) {
            current = current.getNext(element.charAt(i));
            if (current.getNext(element.charAt(i)) == null) {
                return false;
            }
        }
        return current.getEndOfString();
    }

    /**
     * remove sting from Trie
     * @param element removing string
     * @return true if string contained in Trie, false otherwise
     */

    public boolean remove(String element) {
        Point current = start;
        for (int i = 0; i < element.length(); i++) {
            current = current.getNext(element.charAt(i));
            if (current.getNext(element.charAt(i)) == null) {
                return false;
            }
        }
        if (current.removeEndOfString()) {
            for (int i = 0; i < element.length(); i++) {
                current.reduceStartsWithPrefix();
                current.getNext(element.charAt(i));
            }
            size--;
            return true;
        }
        return false;
    }

    /**
     * @return number of different string in Trie
     */
    public  int size() {
        return size;
    }

    /**
     * count during O(|prefix|) number of strings in Trie with this prefix
     * @param prefix
     * @return number of sting, if prefix isn't contained in Trie return 0
     */
    int howManyStartWithPrefix(String prefix) {
        Point current = start;
        for (int i = 0; i < prefix.length(); i++) {
            current.getNext(prefix.charAt(i));
            if (current == null) {
                return 0;
            }
        }
        return current.getStartsWithPrefix();
    }

    /**
     * helper class have private fields next, endOfString, startsWithPrefix
     */
    private class Point {
        private int countOfLetters = 65536; //max Char
        private Point [ ] next = new Point[countOfLetters]; //link to all potential branches
        private boolean endOfString = false;
        private int startsWithPrefix = 0;

        public boolean getEndOfString() {
            return endOfString;
        }

        public boolean setEndOfString() {
            if (endOfString) {
                return false;
            }
            endOfString = true;
            return true;
        }

        public boolean removeEndOfString() {
            if (!endOfString) {
                return false;
            }
            endOfString = false;
            return true;
        }

        public Point getNext(char letter) {
            return next[letter];
        }

        /**
         * try to go to the next Point, if it's null create new Point
         * @param letter current char
         * @return next Point
         */
        public Point goTo(char letter) {
            if (getNext(letter) == null) {
                Point newBranch = new Point();
                next[letter] = newBranch;
            }
            return getNext(letter);
        }

        public int getStartsWithPrefix() {
            return startsWithPrefix;
        }

        public void increaseStartsWithPrefix() {
            startsWithPrefix++;
        }

        public void reduceStartsWithPrefix() {
            startsWithPrefix--;
        }
    }
}
