import java.util.*;

public class DP {

    // Tribonacci Numbers
    // The Tribonacci series is a generalization of the Fibonacci sequence where
    // each term is the sum
    // of the three preceding terms.
    // a(n) = a(n-1) + a(n-2) + a(n-3) with a(0) = a(1) = 0, a(2) = 1.
    // Input : 5
    // Output : 0, 0, 1, 1, 2
    // Input : 10
    // Output : 0, 0, 1, 1, 2, 4, 7, 13, 24, 44
    // Input : 201
    // Output : 0, 0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274, 504, 927, 1705, 3136,
    // 5768, 10609, 19513
    static void prinTrib(int n) {
        int dp[] = new int[n];
        dp[0] = dp[1] = 0;
        dp[2] = 2;

        for (int i = 3; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < n; i++) {
            System.out.println(dp[i] + " ");
        }
    }

    static void printFib(int n) {
        int dp[] = new int[n];
        dp[0] = dp[1] = 0;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        for (int i = 0; i < n; i++) {
            System.out.println(dp[i] + " ");
        }
    }

    public static boolean isSorted(int arr[], int i) {
        if (i == arr.length - 1) {
            return true;
        }
        if (arr[i] <= arr[i + 1]) {
            return false;
        }
        return isSorted(arr, i + 1);
    }

    // Print all combinations of balanced parentheses
    // Write a function to generate all possible n pairs of balanced parentheses..
    // Input: n=1
    // Output: {}
    // Input : n=2
    // Output: {}{} {{}}

    static void printPranthesis(char str[], int pos, int n, int open, int close) {
        if (close == n) {
            for (int i = 0; i < str.length; i++) {
                System.out.print(str[i]);
            }
            System.out.println();
            return;
        } else {
            if (open > close) {
                str[pos] = '}';
                printPranthesis(str, pos + 1, n, open, close + 1);
            }
            if (open < n) {
                str[pos] = '{';
                printPranthesis(str, pos + 1, n, open + 1, close);
            }
        }
    }

    static void PrintPranthesis(char str[], int n) {
        if (n > 0) {
            printPranthesis(str, 0, n, 0, 0);
            return;
        }
    }

    // Maximum profit after buying and selling stocks with transaction fees
    // We have an array of positive integers containing the price of stocks and
    // transaction fee, the
    // task is to find the maximum profit and the difference of days on which you
    // are getting the
    // maximum profit.
    // Input: arr[] = {6, 1, 7, 2, 8, 4} transactionFee = 2
    // Output: 8 1
    // Input: arr[] = {7, 1, 5, 3, 6, 4} transactionFee = 1
    // Output: 5 1

    static int max_profit(int a[], int b[], int n, int fee) {
        int i, j, profit;
        int l, r, difference_day = 1, sum = 0;
        b[0] = 0;
        b[1] = difference_day;
        for (i = 1; i < n; i++) {
            l = 0;
            r = difference_day;
            sum = 0;

            for (j = n - 1; j >= i; j--) {
                profit = (a[r] - a[l] - fee);
                if (profit > 0) {
                    sum = sum + profit;
                }
                l++;
                r++;
            }
            if (b[0] < sum) {
                b[0] = sum;
                b[1] = difference_day;
            }
            difference_day++;
        }

        return 0;
    }

    // Longest Increasing Path in Matrix
    // We have a matrix of NrowsandMcolumns.Fromm[i][j], we can move to m[i+1][j],
    // if m[i+1][j] >
    // m[i][j], or can move to m[i][j+1] if m[i][j+1] > m[i][j]. The task is to
    // print the longest path length if
    // we start from (0, 0).
    // Input : N = 4, M = 4
    // m[][] = { { 1, 2, 3, 4 },
    // { 2, 2, 3, 4 },
    // { 3, 2, 3, 4 },
    // { 4, 5, 6, 7 } };
    // Output : 7
    // Longest path is 1 2 3 4 5 6 7.
    // Input : N = 2, M =2
    // m[][] = { { 1, 2 },
    // { 3, 4 } };
    // Output :3
    // Longest path is either 1 2 4 or 1 3 4.
    // SC O(n2)
    // TC O(n2)
    static int LIP(int dp[][], int mat[][], int n, int m, int x, int y) {
        if (dp[x][y] < 0) {
            int result = 0;
            if (x == n - 1 && y == m - 1) {
                return dp[x][y] = 1;
            }

            if (x == n - 1 || y == m - 1) {
                result = 1;
            }

            if (x + 1 < n && mat[x][y] < mat[x + 1][y]) {
                result = 1 + LIP(dp, mat, n, m, x + 1, y);
            }

            if (y + 1 < m && mat[x][y] < mat[x][y + 1]) {
                result = Math.max(result, 1 + LIP(dp, mat, n, m, x, y + 1));
            }

            dp[x][y] = result;
        }

        return dp[x][y];
    }

    static int wrapper(int mat[][], int n, int m) {
        int dp[][] = new int[10][10];
        for (int i = 0; i < 10; i++)
            Arrays.fill(dp[i], -1);
        return LIP(dp, mat, n, m, 0, 0);
    }

    // Question 5 :
    // Number of Parenthesis Combinations
    // Given N number of parenthesis (pair of opening and closing parenthesis), you
    // have to count all
    // the valid combinations of the parenthesis.
    // Input : N = 4
    // Output : 14
    // //Following 14 combinations
    // {
    // (((()))), ((()())), ((())()), ((()))(), (()(())), (()()())
    // (()())(), (())(()), (())()(), ()((())),()(()()), ()(())()
    // ()()(()), ()()()()
    // }

    public static int helper(int left, int right) {

        if (left == 0 && right == 0) {
            ans++;
        }

        if (left > right) {
            return 0;
        }

        if (left > 0) {
            helper(left - 1, right);
        }

        if (right > 0) {
            helper(left, right - 1);
        }

        return ans;
    }

    // find possible ways for balanced paranthessis
    private static int countWays(int n) {
        // if n is odd no possible valid paranthesis
        if ((n & 1) != 0) {
            return 0;
        }

        return helper(n / 2, n / 2);
    }

    public static void main(String args[]) {
        // int i = 1;
        // prinTrib(n);
        // int arr[] = { 6, 8, 5, 9, 2, 8, 5, 0 };
        // System.out.println(isSorted(arr, 0));
        // int n = 3;
        // char[] str = new char[2 * n];
        // // PrintPranthesis(str, n);
        // int arr[] = { 6, 1, 7, 2, 8, 4 };
        // int n = arr.length;
        // int[] b = new int[2];
        // int tranFee = 2;
        // max_profit(arr, b, n, tranFee);
        // System.out.println(b[0] + " ," + b[1]);

        // int mat[][] = { { 1, 2, 3, 4 }, { 2, 2, 3, 4 }, { 3, 2, 3, 4 }, { 4, 5, 6, 7
        // } };
        // int n = 4, m = 4;
        // // System.out.println(wrapper(mat, n, m));
        // System.out.println(countWays(4));
    }
}
