import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpiralTest {
    Spiral spiral;

    @Before
    public void  SetUp(){
        int[][] b = new int[][]{{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        Spiral spiral = new Spiral(3, b);
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
        assertEquals(a, spiral.getSpiral());
    }

    /**
     * check correct of spiral array after sort
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
        spiral.sort();
        assertEquals(a, spiral.getSpiral());
    }

}