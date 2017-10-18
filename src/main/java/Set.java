public class Set<T extends Comparable<? super T>> {

    private Node<T> root = null;
    private int size = 0;

    public void add(T  newElem) {

    }

    private boolean contains(T elem) {

    }

    private int size () {
      return size;
    }

    private class Node<P extends Comparable<? super P>> {
        P data;
        Node<P> left = null;
        Node<P> right = null;
        Node (P newData) {
            data = newData
        }
    }
}
