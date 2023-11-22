import java.util.*;

public class DynamicPrograming {
    public static int fib(int n, int f[]) {// tc O(n)
        if (n == 0 || n == 1) {
            return n;
        }

        if (f[n] != 0) {// already value calculated
            return f[n];
        }

        f[n] = fib(n - 1, f) + fib(n - 2, f);
        return f[n];
    }

    public static int fibTabulation(int n) {// tc O(n)
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // climbiring Stairs similer to fibonacci same call in function same tree
    // fibnocci
    public static int climbingStairsCountWays(int n) {
        // base case
        if (n == 0) {
            return 1;
        }
        if (n < 0) {// negative value
            return 0;
        }

        return climbingStairsCountWays(n - 1) + climbingStairsCountWays(n - 2);// tc exponantional
    }

    // climbing stairs using memoizatio tc O(n) gpod

    public static int climbingStairsMemoization(int n, int ways[]) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        if (ways[n] != -1) {// already calculated
            return ways[n];
        }

        ways[n] = climbingStairsMemoization(n - 1, ways) + climbingStairsMemoization(n - 2, ways);
        return ways[n];
    }

    public static int climbingTab(int n) {// Tc O(n)
        int dp[] = new int[n + 1];
        dp[0] = 1;

        // tabulation loop
        for (int i = 0; i <= n; i++) {
            if (i == 1) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        return dp[n];
    }

    // 0 1 knapsack
    public static int ksnapsack(int val[], int wet[], int capacity, int item) {
        // base csae
        if (capacity == 0 || item == 0) {
            return 0;
        }

        if (wet[item - 1] <= capacity) {
            // valid
            // include
            int ans1 = val[item - 1] + ksnapsack(val, wet, capacity - wet[item - 1], item - 1);
            // exclude
            int ans2 = ksnapsack(val, wet, capacity, item - 1);
            return Math.max(ans1, ans2);// max profit
        } else {
            return ksnapsack(val, wet, capacity, item - 1);
        }
    }

    // o snapsack memoization TC O(n*w) size
    public static int ksnapsackMemo(int val[], int wet[], int capacity, int item, int dp[][]) {
        // base csae
        if (capacity == 0 || item == 0) {
            return 0;
        }

        if (dp[item][capacity] != -1) {
            return dp[item][capacity];
        }
        if (wet[item - 1] <= capacity) {
            // valid
            // include
            int ans1 = val[item - 1] + ksnapsackMemo(val, wet, capacity - wet[item - 1], item - 1, dp);
            // exclude
            int ans2 = ksnapsackMemo(val, wet, capacity, item - 1, dp);
            dp[item][capacity] = Math.max(ans1, ans2);// max profit
            return dp[item][capacity];
        } else {
            dp[item][capacity] = ksnapsackMemo(val, wet, capacity, item - 1, dp);
            return dp[item][capacity];
        }
    }

    // 0 1 ksapsack tabulation

    public static void printDp(int dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int ksnapsackTabu(int val[], int wet[], int capacity) {
        int n = val.length;
        int dp[][] = new int[n + 1][capacity + 1];
        for (int i = 0; i < dp.length; i++) {
            // 0 th colume
            dp[i][0] = 0;
        }

        for (int j = 0; j < dp[0].length; j++) {
            // 0 th row
            dp[0][j] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                int value = val[i - 1];// ith item val
                int weight = wet[i - 1];// i th item wt
                if (weight <= j) {
                    // valid condition
                    int includeProfit = value + dp[i - 1][j - weight];
                    int exculidProfit = dp[i - 1][j];
                    dp[i][j] = Math.max(includeProfit, exculidProfit);
                } else {
                    // invalid
                    int exculidProfit = dp[i - 1][j];
                    dp[i][j] = exculidProfit;
                }
            }
        }

        return dp[n][capacity];
        // printDp(dp);

    }

    // target sub subset varietion of 0 1 knapsack
    public static boolean targetSumSubset(int arr[], int sum) {// TC O(n * sum)
        int n = arr.length;
        boolean dp[][] = new boolean[n + 1][sum + 1];
        // i = items & j = target sum
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                int value = arr[i - 1];
                // include condition
                if (value <= j && dp[i - 1][j - value] == true) {
                    dp[i][j] = true;
                } // exclude
                else if (dp[i - 1][j] == true) {
                    dp[i][j] = true;
                }
            }
        }
        print(dp);
        return dp[n][sum];
    }

    public static void print(boolean dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int unboundedKnapsack(int val[], int wet[], int capacity) {
        int n = val.length;
        int dp[][] = new int[n + 1][capacity + 1];
        for (int i = 0; i < dp.length; i++) {
            // 0 th colume
            dp[i][0] = 0;
        }

        for (int j = 0; j < dp[0].length; j++) {
            // 0 th row
            dp[0][j] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                // valid condition same code 0 1 knapsack
                if (wet[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wet[i - 1]], dp[i - 1][j]);// only one change
                                                                                          // dp[i-1][j-wt[i-1]] ki jagah
                                                                                          // dp[i][j-wt[i-1]]
                } else {
                    // invalid
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][capacity];
        // printDP(dp);

    }

    public static void printDP(int dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // rod cutting variation of unbounded knapsack TC O(n * totalrodlen)
    public static int rodCutting(int length[], int price[], int totalRod) {
        int n = price.length;
        int dp[][] = new int[n + 1][totalRod + 1];

        // initile
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < totalRod + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        // j = total rod
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < totalRod + 1; j++) {
                // valid cond
                if (length[i - 1] <= j) {
                    // j == total rod
                    // dp[i][j] = Math.max(val[i-1] + dp[i][j-wt[i-1]],dp[i-1][j]);
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - length[i - 1]], dp[i - 1][j]);
                } else {
                    // in valid
                    dp[i][j] = dp[i - 1][j];// exclude
                }
            }
        }

        return dp[n][totalRod];
    }

    /* coin change */

    // longest common sub seqence TC O(exponantional)
    public static int LCS(String str1, String str2, int n, int m) {
        // base
        if (n == 0 || m == 0) {
            return 0;
        }

        // n = str1 length m = str2 length
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            // last char same
            return LCS(str1, str2, n - 1, m - 1);
        } else {// not same
            int ans1 = LCS(str1, str2, n - 1, m);
            int ans2 = LCS(str1, str2, n, m - 1);
            return Math.max(ans1, ans2);
        }
    }

    // LCS memoization TC O(n*m)
    public static int LCS2(String str1, String str2, int n, int m, int dP[][]) {
        if (n == 0 || m == 0) {
            return 0;
        }

        if (dP[n][m] != -1) {
            // already calculated ans store
            return dP[n][m];
        }

        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            return dP[n][m] = LCS2(str1, str2, n - 1, m - 1, dP) + 1;
        } else {
            int ans1 = LCS2(str1, str2, n - 1, m, dP);
            int ans2 = LCS2(str1, str2, n, m - 1, dP);
            return dP[n][m] = Math.max(ans1, ans2);
        }

    }

    // lcs tabulation TC OO(n*m);
    public static int LCSTabulation(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // big problem to small proble
        int dP[][] = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dP[i][j] = 0;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dP[i][j] = dP[i - 1][j - 1] + 1;// +1 is lie same character
                } else {
                    // not same lst char
                    int ans1 = dP[i - 1][j];
                    int ans2 = dP[i][j - 1];
                    dP[i][j] = Math.max(ans1, ans2);
                }
            }
        }

        return dP[n][m];
    }

    // Longest common SubString tc O(n*m)
    public static int longestcommonSubstring(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int dp1[][] = new int[n + 1][m + 1];
        int ans = 0;

        // initilize
        for (int i = 0; i < n + 1; i++) {
            dp1[i][0] = 0;
        }
        for (int j = 0; j < m + 1; j++) {
            dp1[0][j] = 0;
        }

        // bottom Up
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp1[i][j] = dp1[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp1[i][j]);
                } else {
                    dp1[i][j] = 0;
                }
            }
        }

        return ans;

    }

    // longest increasing subsequence variation of lcs
    public static int lcs(int arr1[], int arr2[]) {// lcs in array kel liye
        int n = arr1.length;
        int m = arr2.length;
        int dp[][] = new int[n + 1][m + 1];

        // initilize
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = 0;
        }

        // bottom up
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int ans1 = dp[i - 1][j];
                    int ans2 = dp[i][j - 1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }

        return dp[n][m];
    }

    public static int lis(int arr[]) {
        HashSet<Integer> set = new HashSet<>();// order not mentain but unique and sort
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        int arr2[] = new int[set.size()];// sorted unique element
        int i = 0;
        for (int num : set) {
            arr2[i] = num;
            i++;
        }

        Arrays.sort(arr2);// assending order
        return lcs(arr, arr2);
    }

    // edit diistance O(n*m)
    public static int editDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int dp[][] = new int[n + 1][m + 1];

        // initilze
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                if (j == 0) {
                    dp[i][j] = i;
                }
            }
        }

        // bottom up
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // difference chara
                    int add = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(add, Math.min(delete, replace));
                }
            }
        }

        return dp[n][m];
    }

    // string coversion
    // wildcard matching TC O(n*m)
    public static boolean wildcardMatching(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean dp[][] = new boolean[n + 1][m + 1];

        // initilize
        dp[0][0] = true;// case 3
        // pattern is " " case 1
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = false;
        }

        // s = " " case 2
        for (int j = 1; j < m + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // bottom up
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // case 1 --> ith char == jth char || jth cchar == ?
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        // string -> n pattern -> m
        return dp[n][m];// n = string length p = pattern.length
    }

    // catalan number recue
    public static int catalanRec(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            ans += catalanRec(i) * catalanRec(n - i - 1);
        }

        return ans;
    }

    public static int catalanMemo(int n, int catalan[]) {//
        if (n == 0 || n == 1) {
            return 1;
        }

        if (catalan[n] != -1) {
            return catalan[n];
        }

        for (int i = 0; i < n - 1; i++) {
            ans += catalanRec(i, catalan) * catalanRec(n - i - 1, catalan);
        }

        return catalan[n] = ans;

    }

    public static int catalanTabu(int n) {// TC (n*n)
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];// ci = cj * ci-j-1
            }
        }

        return dp[n];
    }

    // counting trees various of catalan number Tc O(n2)
    public static int countingBstTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int leftSubTree = dp[j];
                int rightSubTree = dp[i - j - 1];
                // dp[i] += dp[j] * dp[i - j - 1];// ci = cj * ci-j-1
                dp[i] += leftSubTree * rightSubTree;
            }
        }

        return dp[n];
    }

    // mountain range variation catalan number
    public static int mountainRanges(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // i pairs --> mountain ranges => ci
            for (int j = 0; j < i; j++) {
                int inside = dp[j];
                int outside = dp[i - j - 1];
                dp[i] += inside * outside;
                // dp[i] += dp[j] * dp[i - j - 1];// ci = cj * ci-j-1
            }
        }

        // n pairs O(n2)
        return dp[n];
    }

    // matrix chain multiplication Recursive find minimum cost
    public static int mcm(int arr[], int i, int j) {// i = starting poinyt j = ending point

        if (i == j) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int cost1 = mcm(arr, i, k);// Ai...Ak => arr[i-1] x arr[k]
            int cost2 = mcm(arr, k + 1, j);// Ai+1 .. Aj => arr[k] x arr[j]
            int cost3 = arr[i - 1] * arr[k] * arr[j];// a * b * d
            int finalCost = cost1 + cost2 + cost3;
            ans = Math.min(ans, finalCost);
        }

        return ans;
    }

    // mcm memoization
    public static int mcmMemo(int arr[], int i, int j, int dp[][]) {
        if (i == j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            // already calculated anser
            return dp[i][j];
        }

        int ans = Integer.MAX_VALUE;

        for (int k = i; k <= j - 1; k++) {
            int cost1 = mcmMemo(arr, i, k, dp);
            int cost2 = mcmMemo(arr, k + 1, j, dp);
            int cost3 = arr[i - 1] * arr[k] * arr[j];
            int finalCost = cost1 + cost2 + cost3;
            ans = Math.min(ans, finalCost);
        }

        return dp[i][j] = ans;
    }

    // minimum partitioining
    // minimum subset sum difference
    // partitioning subbset
    // variation of 0 1 knapsack

    public static int minimumPartioning(int arr[]) {// TC O(nw)
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        // w = sum /2
        int w = sum / 2;// half

        int dp[][] = new int[n + 1][w + 1];

        // bottom up knapsack
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < w + 1; j++) {
                if (arr[i - 1] <= j) {
                    // valid ya include conditionn
                    dp[i][j] = Math.max(arr[i - 1] + dp[i - 1][j - arr[i - 1]], dp[i - 1][j]);
                } else {
                    // unvalid ya exclude
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int sum1 = dp[n][w];
        int sum2 = sum - sum1;// half
        return Math.abs(sum1 - sum2);// absulate difference

        /*
         * agar difference 0 chahiye to
         * int sum1 = dp[n][w];
         * if(sum1 == w){
         * sout("is possible");
         * }else{
         * sout("not possible")
         * }
         */
    }

    // minimum array jump
    public static int minArrayJump(int nums[]) {
        int n = nums.length;
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;

        // big problem is 0 se n-1 th tak small problem n-2 se n-1 tak
        for (int i = n - 2; i >= 0; i--) {
            int steps = nums[i];
            int ans = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + steps && j < n; j++) {// j<n arr.length se bahar ni ho jaye
                if (dp[j] != -1) {
                    ans = Math.min(ans, dp[j] + 1);
                }
            }
            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
            }
        }

        // dp[0] --> 0 to n-1
        return dp[0];
    }

    // tribonacci series
    static void printTrib(int n) {
        int dp[] = new int[n];
        dp[0] = dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i < n; i++)
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        for (int i = 0; i < n; i++)
            System.out.print(dp[i] + " ");
    }

    public static void main(String args[]) {
        // int n = 5;
        int ways[] = new int[n + 1];
        Arrays.fill(ways, -1);// initilize har jagah -1
        int f[] = new int[n + 1];// value 0 se n tak calcu
        System.out.println(fib(n, f));
        System.out.println(fibTabulation(n));
        int val[] = { 15, 14, 10, 45, 30 };
        int wet[] = { 2, 5, 1, 3, 4 };
        int capacity = 7;
        // int dp[][] = new int[val.length + 1][capacity + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(ksnapsack(val, wet, capacity, val.length));
        System.out.println(ksnapsackTabu(val, wet, capacity));
        // int arr[] = { 4, 2, 7, 1, 3 };
        int sum = 10;
        // System.out.println(targetSumSubset(arr, sum));
        System.out.println(unboundedKnapsack(val, wet, capacity));
        int length[] = { 1, 2, 3, 4, 5, 6, 7, 8 };// picess length
        int price[] = { 1, 5, 8, 9, 10, 17, 17, 20 };
        int totalRod = 8;
        System.out.println(rodCutting(length, price, totalRod));
        String str1 = "abcdge";
        String str2 = "abedg";
        System.out.println(LCS(str1, str2, str1.length(), str2.length()));
        // mamoization
        // int dP[][] = new int[n + 1][m + 1];

        // intilize
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dP[i][j] = -1;
            }
        }

        System.out.println(LCS2(str1, str2, n, m, dp));
        System.out.println(LCSTabulation(str1, str2));
        System.out.println(longestcommonSubstring(str1, str2));
        // int arr[] = { 50, 3, 10, 7, 40, 80 };
        System.out.println(lis(arr));
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(editDistance(word1, word2));// TC O(n*m);
        String s = "abcd";
        String p = "****ba******ab";
        System.out.println(wildcardMatching(s, p));
        System.out.println(catalanRec(c));
        int c = 5;
        int catalan[] = new int[c + 1];
        Arrays.fill(catalan, -1);
        System.out.println(catalanMemo(c, catalan));
        System.out.println(catalanTabu(n));
        System.out.println(countingBstTrees(n));
        int arr[] = { 1, 2, 3, 4, 3 };
        int n = arr.length;
        System.out.println(mcm(arr, 1, n - 1));
        int dp[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);// row bias fill
        }

        System.out.println(mcmMemo(arr, i, n - 1, dp));// Tc is better recursion compersion
        int numbers[] = { 6, 5, 11, 1 };
        System.out.println(minimumPartioning(numbers));
        int nums[] = { 2, 3, 1, 1, 4 };
        System.out.println(minArrayJump(nums));
    }
}
