import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpiralTest {
    Spiral spiralOwn;
    Spiral spiral;
    Spiral spiralFive;

    @Before
    public void setUp() {
        int[][] own = new int[][]{{1}};
        spiralOwn = new Spiral(1, own);

        int[][] a = new int[][]{{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        spiral = new Spiral(3, a);

        int[][] five = new int[][]{{5, 5, 5}, {3, 3, 3}, {1, 1, 1}};
        spiralFive = new Spiral(3, five);
    }

    /**
     * check correct of output Array
     *
     * @throws Exception
     */
    @Test
    public void getSpiral() throws Exception {
        ArrayList<Integer> ownA = new ArrayList<>();
        ownA.add(1);
        assertEquals(ownA, spiralOwn.getSpiral());

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);

        a.add(4);
        a.add(5);
        a.add(6);

        a.add(7);
        a.add(8);
        a.add(9);
        assertEquals(a, spiral.getSpiral());

        ArrayList<Integer> five = new ArrayList<>();
        five.add(3);
        five.add(3);
        five.add(1);
        five.add(1);
        five.add(1);
        five.add(3);
        five.add(5);
        five.add(5);
        five.add(5);
        assertEquals(five, spiralFive.getSpiral());
    }

    /**
     * check correct of spiral array after sort
     *
     * @throws Exception
     */
    @Test
    public void sort() throws Exception {
        ArrayList<Integer> ownA = new ArrayList<>();
        ownA.add(1);
        spiralOwn.sort();
        assertEquals(ownA, spiralOwn.getSpiral());

        ArrayList<Integer> a = new ArrayList<>();
        a.add(4);
        a.add(3);
        a.add(9);

        a.add(8);
        a.add(7);
        a.add(5);

        a.add(6);
        a.add(1);
        a.add(2);
        spiral.sort();
        assertEquals(a, spiral.getSpiral());

        ArrayList<Integer> five = new ArrayList<>();
        five.add(3);
        five.add(3);
        five.add(5);
        five.add(5);
        five.add(5);
        five.add(3);
        five.add(1);
        five.add(1);
        five.add(1);

        spiralFive.sort();
        assertEquals(five, spiralFive.getSpiral());
    }

}