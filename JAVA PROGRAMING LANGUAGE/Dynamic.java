import java.util.*;
public class Dynamic {
    //Scanner Sc = new Scanner(System.in);
    //memoization code
    public static int fib(int n,int f[]){
        if(n==0||n==1){
            return n;
        }
        if(f[n] != 0){
            return f[n];
        }
        f[n] = fib(n-1, f)+fib(n-2, f);
        return f[n];
    }
    public static int fibtabulation(int n){
        int dp[] = new int [n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }
        return dp[n];
    }
    public static int countways(int n, int ways[]){
        //memoization code O(n)
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(ways[n] !=-1){
            return ways[n];

        }
        ways[n] = countways(n-1,ways)+ countways (n-2,ways);
        return ways[n];
    }
    public static int countwaystab(int n){
        int dp[] = new int[n+1];
        dp[0] = 1;
        for(int i=1;i<=n;i++){
            if(i == 1){
                dp[i] = dp[i-1] + 0;
            }else {
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        return dp[n];
    }
    public static int knapsack(int val[],int wt[],int w,int n,int dp[][]){//time comlexity o(n+w)
        if(w==0 || n==0){
            return 0;
        }
        if(dp[n][w] != -1){
            return dp[n][w];
        }
        if(wt[n-1] <= w){//valid
        int ans1 = val[n-1]+ knapsack(val, wt, w-wt + [n-1], n-1, dp);
        int ans2 = knapsack(val, wt, w, n-1, dp);
        dp[n][w] = Math.max(ans1,ans2);
        return dp[n][w];
        }else{
            dp[n][w] = knapsack(val, wt, w, n-1, dp);
            return dp[n][w];
        }
    }
   // public static int unboundesnapsack(int val[],int wt[],int w){
        
    
    public static void main (String args[]){//tabulation code
       //int n = SC.nextInt();
       int n = 4;
       // int f[] = new int [n+1];
      // int ways[] = new int[n+1];
       //Arrays.fill(ways,-1);
       // System.out.println(fibtabulation(n));
      // System.out.println(countways(n, ways));
      // System.out.println(countwaystab(n));
      int val[] = {15,14,10,45,30};
      int wt[] =  {2,5,1,3,4};
      int w = 7;
      int dp[][] = new int [val.length+1][w+1];
      for(int i=0;i<dp.length+1;i++){
           for(int j=0; j<dp[0].length; j++){
              dp [i] [j] = -1;
        }   }
            System.out.println(knapsack(val, wt, w, val.length, dp));
    }
}