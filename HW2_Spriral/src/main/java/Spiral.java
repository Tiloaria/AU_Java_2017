import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Savon Yuliya
 */

public class Spiral {
    private int size = 1;
    private int[][] matrix = new int[1][1];

    /**
     * check new_matrix size and if it's ok create new matrix
     * @param new_size
     * @param new_matrix
     */

    public Spiral(int new_size, int [] [] new_matrix) {
        if((new_size % 2 == 1) & (new_matrix.length == new_size) & (new_matrix[0].length == new_size)) {
            matrix = new_matrix;
            size = new_size;
        }
    }

    /**
     * return ArrayList with numbers from array by spiral
     * @return
     */

    public ArrayList<Integer> getSpiral() {

        int center = size / 2;
        int currentX = center;
        int currentY = center;
        int [] dX = new int[] {1, 0, -1, 0};
        int [] dY = new int[] {0, 1, 0, -1};
        int len = 2;
        ArrayList<Integer> out = new ArrayList<>();
        out.add(matrix[currentX][currentY]);
        while(len < size) {
            currentX--;
            currentY--;
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < len; j++) {
                    currentX += dX[i];
                    currentY += dY[i];
                    out.add(matrix[currentX][currentY]);
                }
            }
            len += 2;
        }
        return out;
    }

    /**
     * sort matrix strings by first element
     */
    public void sort() {
        Arrays.sort(matrix, new Comparator<int []>() {
            public int compare(int [] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
    }

    public static void main(String [] args) {
        int[][] a = new int[][]{{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        Spiral spiral = new Spiral(3, a);
        System.out.println(spiral.getSpiral());
    }
}
