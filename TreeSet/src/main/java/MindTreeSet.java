import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Class with realising interface MyTreeSet
 * @param <E> type of elements
 */
public class MindTreeSet<E> implements  MyTreeSet<E>{

    Node start = null;
    Node finish = null;
    Node root = null;
    int size = 0;
    boolean setIsRetern = false;
    Comparator<? super E> comparator = null; // comparator of type E if it's exist

    /**
     * empty constructor
     */
    MindTreeSet() {
    }

    /**
     * constructor with comparator
     * @param cmp comparator of type E
     */
    MindTreeSet(Comparator<? super E> cmp) {
        comparator = cmp;
    }

    /**
     * Method to compare two elements of type E
     * @param e1 first element
     * @param e2 second element
     * @return result of compare
     */
    private int compare(E e1, E e2) {
        if (comparator == null) {
            Comparable<? super E> cmp = (Comparable<? super E>) e1;
            return cmp.compareTo(e2);
        }
        return comparator.compare(e1, e2);
    }

    private class TreeSetIterator implements Iterator<E>{
        Node<E> cur;
        boolean direction;

        TreeSetIterator(boolean r2) {
            direction = setIsRetern == r2;
            if (direction) {
                cur = start;
            }
            else {
                cur = finish;
            }
        }


        public boolean hasNext() {
            if (direction) {
                return cur.next != null;
            }
            return cur.prev != null;
        }

        public E next() {
            if (direction) {
                cur = cur.next;
            }
            else {
                cur = cur.prev;
            }
            return cur.value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        TreeSetIterator i = new TreeSetIterator(false);
        return i;
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
    public boolean contains(Object o) {
        return floor((E)o) == o;
    }

    @Override
    public Object[] toArray() {//
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {//
        return null;
    }

    /**
     * Add an element if it's not exist
     * @param e value of new element
     * @return true, if it's new element, false, if element is already exist
     */
    @Override
    public boolean add(E e) {
        Node n = floorNode(e);
        if (n == null) {
            Node newNode = new Node();
            newNode.value = e;
            if(start == null) {
                start = newNode;
                finish = newNode;
                root = newNode;
                size++;
            }
            else {
                start.l = newNode;
                newNode.next = start;
                newNode.parent = start;
                start.prev = newNode;

                start = newNode;
                size++;
            }
            return true;
        }

        if (compare(e, (E)n.value) == 0) {
            return false;
        }

        Node newNode = new Node();
        newNode.value = e;
        newNode.parent = n;
        newNode.r = n.r;
        if (newNode.r != null) {
            newNode.r.l = newNode;
        }
        newNode.next = n.next;
        n.next = newNode;
        n.r = newNode;
        if (n == finish) {
            finish = newNode;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {//
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {//
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {//
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {//
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {//
        return false;
    }

    @Override
    public void clear() {
        start = null;
        finish = null;
        root = null;
        size = 0;
        setIsRetern = false;
    }

    /**
     * Returns an iterator over the elements in this set in descending order.
     **/
    @Override
    public Iterator<E> descendingIterator() {
        TreeSetIterator t = new TreeSetIterator(true);
        return t;
    }

    /**
     * Returns a reverse order view of the elements contained in this set.
     **/
    @Override
    public MyTreeSet<E> descendingSet() { //
        return null;
    }

    /**
     * Returns the first (lowest) element currently in this set.
     **/
    @Override
    public E first() {
        return (E)start.value;
    }

    /**
     * Returns the last (highest) element currently in this set.
     **/
    @Override
    public E last() {
        return (E)finish.value;
    }

    /**
     * Help method, returns the Node with greatest element in this set less than or equal to the given element, or null if there is no such element.
     * @param e element, floor of which we want to find
     * @return Node with floor element
     */
    private Node floorNode(E e) {
        Node cur = root;
        while(compare(e, (E)cur.value) != 0 && (compare(e, (E)cur.value) < 0 && cur.l != null) && (compare(e, (E)cur.value) > 0 && cur.r != null)) {
            if (compare(e, (E)cur.value) < 0) {
                cur = cur.l;
            }
            else {
                cur  = cur.r;
            }
        }
        if (compare(e, (E)cur.value) >= 0)
            return cur;
        else
            return null;
    }

    /**
     * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
     *
     * @param e
     **/
    @Override
    public E floor(E e) {
        Node n = floorNode(e);
        if (n != null) {
            return (E)n.value;
        }
        return null;
    }

    /**
     * Returns the greatest element in this set strictly less than the given element, or null if there is no such element.
     *
     * @param e
     **/
    @Override
    public E lower(E e) {
        Node n = floorNode(e);
        if (n == null) {
            return null;
        }

        if (compare(e, (E)n.value) > 0) {
            return (E)n.value;
        }

        if (n.prev != null) {
            return (E)n.prev.value;
        }

        return null;
    }

    /**
     * Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
     *
     * @param e
     **/
    @Override
    public E ceiling(E e) {
        Node n = floorNode(e);

        if (n == null) {
            if (start != null) {
                return (E)start.value;
            }
            return null;
        }

        if (compare(e, (E)n.value) == 0) {
            return (E)n.value;
        }

        if (n.next != null) {
            return (E)n.next.value;
        }
        return null;
    }


    /**
     * Returns the least element in this set strictly greater than the given element, or null if there is no such element.
     *
     * @param e
     **/
    @Override
    public E higher(E e) {
        Node n = floorNode(e);

        if (n == null) {
            if (start != null) {
                return (E)start.value;
            }
            return null;
        }

        if (n.next != null) {
            return (E)n.next;
        }

        return null;
    }

    static class Node<E> {
        E value;
        Node l, r, parent, prev, next;
    }
}
