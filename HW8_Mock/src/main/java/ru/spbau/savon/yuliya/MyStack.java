package ru.spbau.savon.yuliya;

/**
 * own Stack with public methods add, pop, top, size
 *
 * @param <E>
 */
public class MyStack<E> {
    int capacity = 1000; //start size of capacity
    int size = 0; //start size

    @SuppressWarnings("unchecked")
    E[] a = (E[]) new Object[capacity];

    /**
     * add an element and if it necessary resize stack
     *
     * @param newElement element for add
     */
    public void add(E newElement) {
        a[size] = newElement;
        size++;
        if (size * 2 > capacity) {
            resize();
        }
    }

    /**
     * remove last element from stack
     */
    public void pop() {
        if(size > 0)
            size--;
    }

    /**
     * don't remove elements
     *
     * @return value of last element
     */
    public E top() {
        return a[size - 1];
    }

    /**
     * @return size of stack
     */
    public int size() {
        return size;
    }

    /**
     * Return information about presence in Stack at least 1 element.
     *
     * @return false if Stack is empty, true otherwise.
     */
    public boolean notEmpty() {
        return size > 0;
    }

    /**
     * resize stack twice
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        E[] newA = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newA[i] = a[i];
        }
        a = newA;
    }
}
