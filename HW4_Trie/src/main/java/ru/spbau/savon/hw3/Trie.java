package ru.spbau.savon.hw3;

import java.util.HashMap;

public class Trie {
    private class newChar {
        private char x;
    }
    private class Point {
        private HashMap <newChar, Point> next;
        private boolean endOfString = false;

        public getEndOfString() {

        }

        public Point getNext(char letter) {
            return next.get(letter);
        }

        public Point goTo(char letter) {
            if (getNext(letter) == null) {
                Point newBranch = new Point();
                next.put(letter, newBranch);
            }
            return getNext(letter);
        }

        public void setEndOfString() {
            endOfString = true;
        }
    }
    private int size;
    private Point start;

    public boolean add(String element) {
        Point current = start;
        for (int i = 0; i < element.length(); i++) {
            current = current.goTo(element.charAt(i));
        }
        current.setEndOfString();
    }

    boolean contains(String element) {
        Point current = start;
        for (int i = 0; i < element.length(); i++) {
            current = current.getNext(element.charAt(i));
            if (current.getNext(element.charAt(i)) == null)
                return false;
        }
        current
    }

}
