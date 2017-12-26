package ru.spbau.savon.hw3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Class can save string, add and remove strings in structure.
 * Also with fast time return size of Trie, is the string contains this array,
 * number of strings with prefix.
 */
public class Trie implements Serializable {

    private int size = 0; //number of strings
    private Point start = new Point(); //the root of Trie

    /**
     * add string to Trie and make new Points if it's necessary, works for O(|length|)
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
            current.increaseStartsWithPrefix();
            for (int i = 0; i < element.length(); i++) {  //update startsWithPrefix
                current = current.getNext(element.charAt(i));
                current.increaseStartsWithPrefix();
            }
            size++;
            return true;
        }
        return false;
    }

    /**
     * check contains sting in Trie
     * @param element checked string
     * @return true if string contains, false otherwise
     */
    public boolean contains(String element) {
        Point current = start;
        for (int i = 0; i < element.length(); i++) {
            current = current.getNext(element.charAt(i));
            if (current == null) {
                return false;
            }
        }
        return current.isEndOfString();
    }

    /**
     * remove string from Trie, works for O(|length|)
     * @param element removing string
     * @return true if string contained in Trie, false otherwise
     */

    public boolean remove(String element) {
        Point current = start;
        for (int i = 0; i < element.length(); i++) {
            current = current.getNext(element.charAt(i));
            if (current == null) {
                return false;
            }
        }
        if (current.removeEndOfString()) {
            current = start;
            current.reduceStartsWithPrefix();
            for (int i = 0; i < element.length(); i++) {
                current = current.getNext(element.charAt(i));
                current.reduceStartsWithPrefix();
            }
            size--;
            return true;
        }
        return false;
    }

    /**
     * @return number of different string in Trie
     */
    public int size() {
        return size;
    }

    /**
     * count during O(|prefix|) number of strings in Trie with this prefix, works for O(|prefix|)
     * @param prefix
     * @return number of sting, if prefix isn't contained in Trie return 0
     */
    public int howManyStartWithPrefix(String prefix) {
        Point current = start;
        for (int i = 0; i < prefix.length(); i++) {
            current = current.getNext(prefix.charAt(i));
            if (current == null) {
                return 0;
            }
        }
        return current.getStartsWithPrefix();
    }

    /**
     * helper class have private fields next, endOfString, startsWithPrefix
     */
    private class Point implements Serializable {
        private int countOfLetters = 255; //max Char
        private Point[] next = new Point[countOfLetters]; //link to all potential branches
        private boolean endOfString = false;
        private int startsWithPrefix = 0;

        public int getCountOfLetters() {
            return countOfLetters;
        }

        public boolean isEndOfString() {
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
         * try to go to the next Point, if it's null create new Point, O(1)
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

    /**
     * bypasses Trie, and write exist strings in OutputStream, after every string write 0
     * @param buffer save the current string
     * @param v current Point in Trie
     * @param out output stream to write strings
     * @throws IOException uncorrected write
     */
    private void dfs(StringBuffer buffer, Point v, OutputStream out) throws IOException {
        if (v == null)
            return;
        if (v.isEndOfString()) {
            out.write(buffer.toString().getBytes());
            out.write(0);
        }
        for (int i = 0; i < v.getCountOfLetters(); i++) {
            dfs(buffer.append((char) i), v.next[i], out);
            buffer.delete(buffer.length() - 1, buffer.length());
        }
    }

    /**
     * serialize Trie to Stream. Decide that it's 255 or less strings in Trie.
     * @param out OutputStream where trie be written
     * @throws IOException - uncorrected OutputStream
     */
    public void serialize(OutputStream out) throws IOException {
        out.write((char) size);
        dfs(new StringBuffer(), start, out);
        out.write(0);
    }

    /**
     * serialize Trie from Stream
     * @param in input stream from trie will be read
     * @throws IOException uncorrected InputStream
     */
    public void deserialize(InputStream in) throws IOException {
        size = 0;
        int mustSize = in.read();
        start = new Point();
        StringBuffer buf = new StringBuffer();
        while (in.available() > 1) {
            int first = in.read();
            if (first == 0) {
                add(buf.toString());
                buf.delete(0, buf.length());
            } else {
                buf.append((char) first);
            }
        }
        if (size != mustSize) {
            throw new IOException();
        }
    }
}