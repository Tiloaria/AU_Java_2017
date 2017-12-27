import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class PQueueTest {
    @Test
    public void PQueue() throws Exception {
        PQueue<Integer> pQueue = new PQueue<>();
    }

    @Test
    public void add() throws Exception {
        PQueue<Integer> pQueue = new PQueue<>();
        pQueue.add(2);
        pQueue.add(3);
        pQueue.add(1);
    }

    @Test
    public void size() throws Exception {
        PQueue<Integer> pQueue = new PQueue<>();
        assertEquals(0, pQueue.size());
        pQueue.add(2);
        assertEquals(1, pQueue.size());
        pQueue.add(3);
        assertEquals(2, pQueue.size());
        pQueue.add(1);
        assertEquals(3, pQueue.size());
    }

    @Test
    public void clear() throws Exception {
        PQueue<Integer> pQueue = new PQueue<>();
        pQueue.add(2);
        pQueue.add(3);
        pQueue.add(1);
        pQueue.clear();
        assertEquals(0, pQueue.size());
    }

    @Test
    public void poll() throws Exception {
        PQueue<Integer> pQueue = new PQueue<>();
        pQueue.add(2);
        pQueue.add(3);
        pQueue.add(1);
        assertEquals(1, (int) pQueue.poll());
        assertEquals(2, (int) pQueue.poll());
        assertEquals(3, (int) pQueue.poll());
    }

    @Test
    public void addSimilar() throws Exception {
        PQueue<Integer> pQueue = new PQueue<>();
        pQueue.add(1);
        pQueue.add(1);
        pQueue.add(1);
        assertEquals(3, pQueue.size());
        assertEquals(1, (int) pQueue.poll());
        assertEquals(1, (int) pQueue.poll());
        assertEquals(1, (int) pQueue.poll());
    }

    @Test
    public void queueWithComparator() {
        Comparator<Integer> cmpr = (a, b) -> a - b;
        PQueue<Integer> pQueue = new PQueue<Integer>(cmpr);
        pQueue.add(2);
        pQueue.add(3);
        pQueue.add(1);
        assertEquals(1, (int) pQueue.poll());
        assertEquals(2, (int) pQueue.poll());
        assertEquals(3, (int) pQueue.poll());
    }

    @Test
    public void checkFIFO() {
        Comparator<Integer> cmpr = (a, b) -> a - b;
        PQueue<Integer> pQueue = new PQueue<Integer>(cmpr);
        Integer a = new Integer(2);
        Integer b = new Integer(2);
        Integer c = new Integer(2);
        pQueue.add(a);
        pQueue.add(b);
        pQueue.add(c);
        assertTrue(a == pQueue.poll());
        assertTrue(b == pQueue.poll());
        assertTrue(c == pQueue.poll());
    }
}