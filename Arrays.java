import java.util.*;

public class Arrays {

    public static boolean Search(int matrix[][], int key) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == key) {
                    System.out.println("found at cell " + i + j);
                    return true;
                }
            }
        }
        System.out.println("key not found");
        return false;

    }

    // spiral matrix amzon appple microsoft google oracle
    public static void spiralMatrix(int matrix[][]) {
        int startrow = 0;
        int startcol = 0;
        int endrow = matrix.length - 1;
        int endcol = matrix[0].length - 1;

        while (startrow <= endrow && startcol <= endcol) {

            // top -> col
            for (int j = startcol; j <= endcol; j++) {
                System.out.print(matrix[startrow][j] + " ");
            }

            // right -> row
            for (int i = startrow + 1; i <= endrow; i++) {
                System.out.print(matrix[i][endcol] + " ");

            }

            // bottom
            for (int j = endcol - 1; j >= startcol; j--) {
                if (startrow == endrow) { // odd condition
                    break;
                }
                System.out.print(matrix[endrow][j] + " ");
            }

            // left
            for (int i = endrow - 1; i >= startrow + 1; i--) {
                if (startcol == endcol) {// odd condition
                    break;
                }
                System.out.print(matrix[i][startcol] + " ");

            }

            startcol++;
            startrow++;
            endcol--;
            endrow--;
        }
        System.out.println();
    }

    // matrixdignal sum
    public static int DiagnalSum(int matrix[][]) {// TC o(n) its good amazon microsoft samsung
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            // primary diagonal
            sum += matrix[i][i];
            // secondary diagonal
            if (i != matrix.length - 1 - i) { // i != j odd condition => i+j = n-1 , j = n-1-i
                sum += matrix[i][matrix.length - i - 1];
            }
        }
        return sum;

    }

    // serach for a key in row wise & col wise sorted matrix
    public static Boolean StairCaseSearch(int matrix[][], int key) { // TC O(n) oracle adobe n*m n>>m Tc O(n) , n*m n<<m
                                                                     // Tc (n+m)

        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == key) {
                System.out.println("found key at ( " + row + " ," + col + ") ");
                return true;

            } else if (key < matrix[row][col]) { // key = cell value
                col--;
            } else { // key > row,col
                row++;
            }
            System.out.println("key not found :");

        }
        return false;

    }

    // matrix multipication
    /*
     * public static void MatrixMulti(int a[][], int b[][], int c[][]) {
     * 
     * for (int i = 0; i < a.lenght; i++) {
     * for (int j = 0; j < b[0].length; j++) {
     * c[i][j] = 0;
     * for (int k = 0; k < c[0].length; k++) {
     * c[i][j] = c[i][j] + (a[i][k] * b[j][k]);
     * }
     * System.out.print(c[i][j]);
     * }
     * System.out.println();
     * 
     * }
     * }
     */

    public static void main(String args[]) {
        Scanner Sc = new Scanner(System.in);
        // int a[][] = new int[3][3];
        // int b[][] = new int[3][3];
        // int c[][] = new int[3][3];
        // MatrixMulti(a, b, c);

        // int matrix[][] = new int[3][3];
        // int n = matrix.length , m = matrix[0].length;

        // for(int i=0;i<n; i++){
        // for(int j=0; j<m;j++){
        // matrix[i][j] = Sc.nextInt();
        // }
        // }
        // output
        // for(int i=0;i<n; i++){
        // for(int j=0; j<m;j++){
        // System.out.println(matrix[i][j] + " ");
        // }

        // }

        /*
         * int matrix[][] = { { 1, 2, 3, 4 },
         * { 5, 6, 7, 8 },
         * { 9, 10, 11, 12 },
         * { 13, 14, 15, 16 } };
         * 
         * // System.out.print(spiralMatrix(matrix));
         * // System.out.print(DiagnalSum(matrix));
         * int key = 8;
         * StairCaseSearch(matrix, key);
         */

    }

}