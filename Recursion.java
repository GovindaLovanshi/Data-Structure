import java.util.*;

public class Recursion {
    public static void PrintDecreasing(int n) {
        if (n == 1) {
            System.out.print(1);
            return;
        }
        System.out.print(n + " ");
        PrintDecreasing(n - 1);

    }

    public static void PrintIncreas(int n) {
        // base case
        if (n == 1) {
            System.out.print(n + " ");
            return;
        }
        PrintIncreas(n - 1);
        System.out.print(n + " ");
    }

    public static int fact(int n) {
        if (n == 0) {
            return 1;
        }
        int fnm1 = fact(n - 1);
        int fn = n * fnm1;
        return fn;
    }

    public static int calsum(int n) {
        if (n == 1) {
            return 1;
        }
        int Snm1 = calsum(n - 1);
        int sn = n + Snm1;
        // return calsum(n-1) + n;
        return sn;
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

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int fnm1 = fib(n - 1);
        int fnm2 = fib(n - 2);
        return fnm1 + fnm2;
    }

    public static int FirstOccur(int arr[], int key, int i) {
        if (i == arr.length) {
            return -1;
        }
        if (arr[i] == key) {
            return i;
        }

        return FirstOccur(arr, key, i + 1);
    }

    public static int LastOccurance(int arr[], int key, int i) {
        if (i == arr.length) {
            return -1;
        }
        int isFound = LastOccurance(arr, key, i + 1);
        if (isFound == -1 && arr[i] == key) {
            return i;
        }

        return isFound;

    }

    public static int OptimizedPower(int a, int n) {
        if (n == 0) {
            return 1;
        }

        // int halfpowsq = OptimizedPower(a, n/2) * OptimizedPower(a, n/2);// TC O(n)
        int halfpower = OptimizedPower(a, n / 2);
        int halfpowsq = halfpower * halfpower; // Tc O(logn)

        // check odd
        if (n % 2 != 0) {
            halfpowsq = a * halfpowsq;
        }

        return halfpowsq;
    }

    // tilling problem 2*n
    // similler to fibonacci number

    public static int tillingProblem(int n) {
        // base case
        if (n == 0 || n == 1) {
            return 1;
        }

        // kaam
        // vertical choice
        int fnm1 = tillingProblem(n - 1);

        // horizontal choice

        int fnm2 = tillingProblem(n - 2);

        int totalways = fnm1 + fnm2;
        // return fnm1 + fnm2;
        // return tillingProblem(n-1) + tillingProblem(n-2);
        return totalways;
    }

    // print all binary string of size n withot consescuutive(0011 ! ek bad ek same
    // number binary nhi hona chahiye) ones
    // 010110 binary string
    // valid string 010101 not valid 0011
    public static void printBinaryProb(int n, int lastplace, String str) {

        // base case
        if (n == 0) {
            System.out.println(str);
            return;
        }
        // kaam
        printBinaryProb(n - 1, 0, str + "0");
        if (lastplace == 0) {
            printBinaryProb(n - 1, 1, str + "1");
        }
    }

    // remove duplicate in a string => important
    public static void removeDuplicate(String str, int idx, StringBuilder newStr, boolean map[]) {
        if (idx == str.length()) {
            System.out.println(newStr);
            return;
        }

        // kaam
        char currchar = str.charAt(idx);
        if (map[currchar - 'a'] == true) {
            removeDuplicate(str, idx + 1, newStr, map);
        } else {
            map[currchar - 'a'] = true;
            removeDuplicate(str, idx + 1, newStr.append(currchar), map);
        }
    }

    // Freinds pairing problem
    // given n freinds each freind only one pair
    public static int freindsPair(int n) {
        // base case

        if (n == 1 || n == 2) {
            return n;
        }
        // choice
        // single
        int fnm1 = freindsPair(n - 1);

        // pair
        int fnm2 = freindsPair(n - 2);
        int pairways = (n - 1) * fnm2;
        int totways = fnm1 + pairways;
        

        return totways;

        // return freindsPair(n - 1) + (n - 1) * freindsPair(n - 2);
    }

    // Question 1 : For a given integer array of size N. You have to find all the
    // occurrences
    // (indices) of a given element (Key) and print them. Use a recursive function
    // to solve this
    // problem.
    // Sample Input : arr[ ] = {3, 2, 4, 5, 6, 2, 7, 2, 2}, key = 2
    // Sample Output : 1 5 7 8

    public static int length(String str) {
        if (str.length() == 0) {
            return 0;
        }
        return length(str.substring(1)) + 1;
    }

    // Question 4 : We are given a string S, we need to find the count of all
    // contiguous substrings
    // starting and ending with the same character.
    // Sample Input 1 : S = "abcab"
    // Sample Output 1 : 7
    // There are 15 substrings of "abcab" : a, ab, abc, abca, abcab, b, bc, bca,
    // bcab, c, ca, cab, a, ab, b
    // Out of the above substrings, there are 7 substrings : a, abca, b, bcab, c, a
    // and b. So, only 7
    // contiguous substrings start and end with the same character.
    // Sample Input 2 : S = "aba"
    // Sample Output 2 : 4
    // The substrings are a, b, a and aba.
    public static int countSubstrs(String str, int i, int j, int n) {
        if (n == 1) {
            return 1;
        }
        if (n <= 0) {
            return 0;
        }
        int res = countSubstrs(str, i + 1, j, n - 1) +
                countSubstrs(str, i, j - 1, n - 1) -
                countSubstrs(str, i + 1, j - 1, n - 2);
        if (str.charAt(i) == str.charAt(j)) {
            res++;
        }

        return res;
    }

    public static void main(String args[]) {
        // string str = "abcde";
        // System.out.println(length(str));
        // String str = "abcab";
        // int n = str.length();
        // System.out.print(countSubstrs(str, 0, n - 1, n));
        // int n = 5;
        // PrintDecreasing(n);
        // PrintIncreas(n);
        // System.out.println(fact(n));
        // System.out.println(calsum(n));
        // int arr[] = {6,8,5,9,2,8,5,0};
        // System.out.println(LastOccurance(arr, 5, 0));
        // int a = 2;
        // int n = 10;
        // System.out.println(OptimizedPower(a, n));
        // System.out.println(tillingProblem(4));
        // printBinaryProb(3, 0, "");
        // String str = "appna";
        // removeDuuplicate(str, 0,new StringBuilder(""), new boolean[26]);
        System.out.println(freindsPair(3));
    }

}