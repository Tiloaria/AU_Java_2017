import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpiralTest {
    private Spiral spiralOwn;
    private Spiral spiralNine;
    private Spiral spiralFive;

    @Before
    public void setUp() {
        int[][] own = new int[][]{{1}};
        spiralOwn = new Spiral(1, own);

        int[][] a = new int[][]{{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        spiralNine = new Spiral(3, a);

        int[][] five = new int[][]{{5, 5, 5}, {3, 3, 3}, {1, 1, 1}};
        spiralFive = new Spiral(3, five);
    }

    @org.junit.Test
    public void easyTest() {
        ArrayList<Integer> ownA = new ArrayList<>();
        ownA.add(1);

        assertEquals(ownA, spiralOwn.getSpiral());

        spiralOwn.sort();
        assertEquals(ownA, spiralOwn.getSpiral());
    }

    /**
     * Check that Spirals with equals strings after sort are equals
     */
    @org.junit.Test
    public void equalsTest() {
        int[][] a2 = new int[][]{{8, 7, 9}, {1, 6, 2}, {4, 5, 3}};
        //int[][] a = new int[][]{{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        Spiral spiral2 = new Spiral(3, a2);

        assertNotEquals(spiralNine.getSpiral(), spiral2.getSpiral());
        spiralNine.sort();
        spiral2.sort();
        assertEquals(spiralNine.getSpiral(), spiral2.getSpiral());
    }

    @org.junit.Test
    public void spiralTestFive() {
        int[][] five = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                five[i][j] = i * 5 + (j + 1);
            }
        }

        Spiral spiralSize5 = new Spiral(5, five);
        assertEquals("[13, 12, 17, 18, 19, 14, 9, 8, 7, 6, 11, 16, 21, 22, 23, 24, 25, 20, 15, 10, 5, 4, 3, 2, 1]",
                spiralSize5.getSpiral().toString());
    }
    /**
     * check correct of output Array
     * @throws Exception
     */
    @Test
    public void getSpiral() throws Exception {
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
        assertEquals(a, spiralNine.getSpiral());

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
     * check correct of spiralNine array after sort
     * @throws Exception
     */
    @Test
    public void sort() throws Exception {
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
        spiralNine.sort();
        assertEquals(a, spiralNine.getSpiral());

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