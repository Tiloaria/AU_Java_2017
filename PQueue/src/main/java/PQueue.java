import java.util.ArrayList;
import java.util.Comparator;

public class PQueue<E extends Comparable<? super E>> {
    int size = 0;
    /*
    Top is point[1],  it's better, course we can touch all of elements,
    children are 2i and 2i + 1
     */
    ArrayList<E> points = new ArrayList<>();
    Comparator<? super E> comparator;

    /**
     * initialization with naturalOrder comparator
     */
    public PQueue() {
        comparator = Comparator.naturalOrder();
    }

    /**
     * initialization with newComparator
     * @param newComparator comparator for E
     */
    public PQueue( Comparator<? super E> newComparator) {
        comparator =  newComparator;
    }

    /**
     * if it isn't zero element we should add it
     * @param e
     */
    public void add(E e) {
        if (points.size() == 0) {
            points.add(e); //to have zero element
        }
        points.add(e);
        size++;
        siftUp(size);
    }

    public int size() {
        return size;
    }

    /**
     *
     */
    public void clear() {
        size = 0;
        points.clear();
    }

    public E poll (){
        if (size == 0)
            return null;
        E ans = points.get(0);
        siftDown(1);
        return points.get(0);
    }

    private void siftUp(int i) {
        // while we are not top and father is less
        while (i > 1 && (comparator.compare(points.get(i / 2), points.get(i)) < 0)) {
            //swap(points.get(i), points.get(i / 2));
            E newE = points.get(i);
            points.set(i, points.get(i/2));
            points.set(i/2, newE);
            i /= 2;
        }
    }

    private void siftDown(int i) {
        while (true) {
            int l = 2 * i;
            // choose bigerst of children
            if (l + 1 <= size && comparator.compare(points.get(l + 1), points.get(l)) > 0) {
                l++;
            }
            // if all children are less
            if (!(l <= size && comparator.compare(points.get(l), points.get(i)) < 0)) {
                break;
            }
            swap(points.get(l), points.get(i));
            E newE = points.get(l);
            points.set(l, points.get(i));
            points.set(i, newE);
            i = l; // go to child
        }
    }

    private void swap(E x1, E x2) {
        E newEdit = x2;
        x2 = x1;
        x1 = newEdit;
    }
}
