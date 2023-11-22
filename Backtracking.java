import java.io.CharArrayReader;

public class Backtracking {

    public static void ChangeArray(int arr[], int index, int value) {
        // base case
        if (index == arr.length) {
            printarr(arr);
            return;
        }
        // recursion
        arr[index] = value;
        ChangeArray(arr, index + 1, value + 1);
        arr[index] = arr[index] - 2;

    }

    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // find subset
    public static void findSubset(String str, String ans, int i) {// TC O(n *2^n) SC O(n)

        // base case
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("null");
            } else {
                System.out.println(ans);
            }
            return;
        }
        // recursion
        // yes choice
        findSubset(str, ans + str.charAt(i), i + 1);
        // no choice
        findSubset(str, ans, i + 1);

    }

    // find & print all permutation of a string
    public static void findPermutations(String str, String ans) { // TC O(n*N!)
        // base case
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        // recursion
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            // "abcde" => ab + de = abde remove c
            String NewStr = str.substring(0, i) + str.substring(i + 1);
            findPermutations(NewStr, ans + curr);
        }

    }

    // grid ways find the number of ways to rach from (0,0) t (N-1,M-1)in N*M grid
    // allowed moves-- rigth or down
    public static int GridWays(int i, int j, int n, int m) { // TC O(2^n+m) very bad code small number allwed
                                                             // exponantional TC`
        // base case
        if (i == n - 1 && j == m - 1) { // condition for last cell
            return 1;
        } else if (i == n || j == n) {// condition for boundry cross
            return 0;
        }

        // recursion
        int W1 = GridWays(i + 1, j, n, m); // Right
        int W2 = GridWays(i, j + 1, n, m);// Down
        return W1 + W2;

    }

    // N quene problem
    public static boolean ISSAFE(char board[][], int row, int col) {
        // vertical up // colume same row --
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // diagnoal left up row -1 colum -1
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;// safe nqueens
    }

    public static void nQueens(char board[][], int row) {// O(ni) n n-1 n-2 n-3 n-4 T(n) = 1quene * T(n-1) + issafe() n
                                                         // factorial
                                                         // reqrencee relationt T(n) = n*T(n-1) + isSfae // base case
        if (row == board.length) {
            printBoard(board);
            // count++;// all ways
            return;
            // return true;// function type boolean hai only one solution ke liye
        }

        // colum loop
        for (int i = 0; i < board.length; i++) {
            if (ISSAFE(board, row, j)) {
                board[row][j] = 'Q';
                nQueens(board, row + 1);// function call
                /*
                 * if(nQueens(board, row + 1)){
                 * return true;// only one solution ke liye
                 * }
                 */
                board[row][j] = 'x';// backtracking
            }
        }

        // returne false
    }

    public static void printBoard(char board[][]) {
        System.out.println("-------chessboard--------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.println(board[i][j]);
            }
            System.out.println();
        }
    }

    static int count = 0;

    // sudoku
    public static boolean isSafe(int sudoku[][], int row, int col, int digit) {

        // colom check
        for (int i = 0; i <= 8; i++) {
            if (sudoku[i][col] == digit) {
                return false;
            }
        }

        // row check
        for (int j = 0; j <= 8; j++) {
            if (sudoku[row][j] == digit) {
                return false;
            }

        }

        // grid
        int sr = (row / 3) * 3;// starting row
        int sc = (col / 3) * 3;// starting col

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;

    }

    public static boolean sudokuSolver(int sudoku[][], int row, int col) {

        // base case
        if (row == 9) {
            return true;
        }

        // Recursion
        int nextrow = row, nextcol = col + 1;
        if (col + 1 == 9) {
            nextrow = row + 1;
            nextcol = 0;
        }

        if (sudoku[row][col] != 0) {
            return sudokuSolver(sudoku, nextrow, nextcol);
        }

        for (int digit = 1; digit <= 9; digit++) {
            if (isSafe(sudoku, row, col, digit)) {
                sudoku[row][col] = digit; // passing digit
                if (sudokuSolver(sudoku, nextrow, nextcol)) { // solution exists
                    return true;
                }

                sudoku[row][col] = 0;
            }

        }
        return false;
    }

    public static void printsudoku(int sudoku[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        // int arr[] = new int[5];
        // ChangeArray(arr, 0, 1);
        // String str = "abc";
        // findSubset(str, "", 0);
        // findPermutations(str, "");
        // int n = 3;
        // int m = 3;
        // System.out.println(GridWays(0, 0, n, m));
        int n = 4;
        char board[][] = new char[n][n];
        // intilize for
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 'x';
            }
        }

        nQueens(board, 0);
        /*
         * if(nQueens(board, 0)){
         * sout("solution is posiible");
         * printBoard(board);
         * }else{
         * sout("soluti ois not possible");// only one solution wale me
         * }
         */
        System.out.println("total number ways of solve n quenns = " + count);

        int sudoku[][] = { { 0, 0, 8, 0, 0, 0, 0, 0, 0 },
                { 4, 9, 0, 1, 5, 7, 0, 0, 2 },
                { 0, 0, 3, 0, 0, 4, 1, 9, 0 },
                { 1, 8, 5, 0, 6, 0, 0, 2, 0 },
                { 0, 0, 0, 0, 2, 0, 0, 6, 0 },
                { 9, 6, 0, 4, 0, 5, 3, 0, 0 },
                { 0, 3, 0, 0, 7, 2, 0, 0, 4 },
                { 0, 4, 9, 0, 3, 0, 0, 5, 7 },
                { 8, 2, 7, 0, 0, 9, 0, 1, 3 } };

        if (sudokuSolver(sudoku, 0, 0)) {
            System.out.println("solution exists");
            printsudoku(sudoku);
        } else {
            System.out.println("solutio does not exists");
        }

    }

}
