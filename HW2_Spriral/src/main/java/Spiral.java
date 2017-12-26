import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Savon Yuliya
 */

/**
 * Exist matrix N*N with int values ans odd N.
 * getSpriral -  out elements of matrix.
 * sort - sort columns of matrix by first elements.
 */
public class Spiral {
    private int size = 1;
    private int[][] matrix = new int[1][1];

    /**
     * check newMatrix size and if it's ok create new matrix
     *
     * @param newSize new size of matrix
     * @param newMatrix new matrix
     */
    public Spiral(int newSize, int[][] newMatrix) {
        if ((newSize % 2 == 1) & (newMatrix.length == newSize) & (newMatrix[0].length == newSize)) {
            matrix = newMatrix;
            size = newSize;
        }
    }

    /**
     * return ArrayList with numbers from array by spiral
     *
     * @return return ArrayList with values bypass spiral
     */
    public ArrayList<Integer> getSpiral() {

        int center = size / 2;
        int currentX = center;
        int currentY = center;
        int[] dX = new int[]{1, 0, -1, 0};
        int[] dY = new int[]{0, 1, 0, -1};
        int len = 2;
        ArrayList<Integer> out = new ArrayList<>();
        out.add(matrix[currentX][currentY]);
        while (len < size) {
            currentX--;
            currentY--;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < len; j++) {
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
        Arrays.sort(matrix, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        Spiral spiral = new Spiral(3, a);
        System.out.println(spiral.getSpiral());
    }
}
