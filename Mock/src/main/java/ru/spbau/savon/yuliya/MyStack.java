package ru.spbau.savon.yuliya;

public class MyStack<E> {
    int capacity = 1000;
    int size = 0;

    @SuppressWarnings("unchecked")
    E[] a = (E[])new Object[capacity];

    public void add (E newElement) {
        a[size] = newElement;
        size++;
        if (size * 2 > capacity) {
            resize();
        }
    }

    public void pop () {
        size--;
    }

    public E top () {
        return a[size - 1];
    }

    public int size () {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize () {
        capacity *= 2;
        E[] newA = (E[])new Object[capacity];
        for (int i = 0; i < size; i++) {
            newA[i] = a[i];
        }
        a = newA;
    }
}
