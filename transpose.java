public class transpose {

    public static void printmatrix(int matrix[][]) {
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < colum[0].length; j++) {
                System.out.println(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int row = 3, colum = 4;

        int matrix[][] = { { 1, 2, 3, 4 }, { 3, 4, 5, 6 }, { 4, 9, 5, 8 } };

        printmatrix(matrix);

        int transpose[][] = new int[row][colum];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                transpose[j][i] = matrix[row][colum];
            }
        }

        printmatrix(matrix);
    }
}