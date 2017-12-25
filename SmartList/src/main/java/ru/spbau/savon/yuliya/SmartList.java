package ru.spbau.savon.yuliya;

import java.util.*;

/**
 * Have size and elements, save 1 element in data, 2...5 elements to list, 5 and more in ArrayList
 *
 * @param <E> type of elements
 */
public class SmartList<E> extends AbstractList<E> implements List<E> {
    int size;
    Object data;

    /**
     * Create SmartList
     */
    public SmartList() {
        size = 0;
        data = null;
    }

    /**
     * Create from Collection
     *
     * @param collection
     */
    public SmartList(Collection<E> collection) {
        size = collection.size();

        if (collection.size() == 1) {
            data = collection.iterator().next();
            return;
        }

        if (collection.size() >= 2 && collection.size() <= 5) {
            Object[] newArray = new Object[5];
            for (int i = 0; i < 5; i++) {
                newArray[i] = ((ArrayList<E>) data).toArray()[i]; }

            data = newArray;
            return;
        }

        ArrayList<E> tmp = new ArrayList<>();
        tmp.addAll(collection);
        data = tmp;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        E newElement = e;
        if (size == 0) {
            data = (Object) (newElement);
            size = 1;
            return true;
        }
        if (size >= 1 && size <= 4) {
            Object[] newArray;
            if (size == 1) {
                newArray = new Object[2];
                newArray[0] = data;
                newArray[1] = e;
            } else {
                newArray = new Object[size + 1];
                for (int i = 0; i < size; i++) {
                    newArray[i] = ((E[]) data)[i];
                }
                newArray[size] = e;
            }
            data = (Object) newArray;
            size++;
            return true;
        }
        if (size == 5) {
            ArrayList<E> newList = new ArrayList<E>();
            for (int i = 0; i < 5; i++) {
                newList.add(((E[]) data)[i]);
            }
            newList.add(e);
            data = newList;
            size = 6;
            return true;
        }
        ((ArrayList<E>) data).add(e);
        size++;
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= size || size == 0) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 1) {
            return (E) data;
        }

        if (size >= 2 && size <= 5) {
            return ((E[]) data)[index];
        }

        return ((ArrayList<E>) data).get(index);

    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size || size == 0) {
            throw new IndexOutOfBoundsException(); //remove is impossible
        }

        if (size == 1) {
            Object prev = data;
            data = null;
            size = 0;
            return (E) prev;
        }

        if (size >= 2 && size <= 5) {
            Object[] curArray = (Object[]) data;
            Object prev = curArray[index];
            size--;
            if (size == 1) {
                if (index == 1) {
                    data = curArray[0];
                } else {
                    data = curArray[1];
                }
                return (E) prev;
            }

            ArrayList<E> result = new ArrayList<E>();
            for (Object c : curArray) {
                if (c != prev) {
                    result.add((E) c);
                }
            }
            data = result.toArray();

            return (E) prev;
        }

        ArrayList<E> arrayList = (ArrayList<E>) data;
        E prev = arrayList.remove(index);
        size--;
        if (size == 5) {
            Object[] newArray = new Object[5];
            for (int i = 0; i < 5; i++) {
                newArray[i] = ((ArrayList<E>) data).toArray()[i];
            }

            data = newArray;
        }

        return prev;

    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index >= size || size == 0) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 1) {
            Object prev = data;
            data = element;
            return (E) prev;
        }

        if (size >= 2 && size <= 5) {
            Object[] array = (Object[]) data;
            Object prev = array[index];
            array[index] = element;

            return (E) prev;
        }

        ArrayList<E> arrayList = (ArrayList<E>) data;
        Object prev = arrayList.get(index);
        arrayList.set(index, element);

        return (E) prev;
    }

    @Override
    public void clear() {
        size = 0;
        data = null;
    }

}

