import java.util.*;

public class Array {

  public static void GetlargestNUmber(int number[]) {
    int largest = Integer.MIN_VALUE;
    

    for (int i = 0; i < number.length; i++) {
      if (largest < number[i]) {
        largest = number[i];
      }     
    }
  }

  public static int BinarySearch(int number[], int key) {
    int start = 0, end = number.length - 1;

    while (start <= end) {
      int mid = (start + end) / 2;
      if (number[mid] == key) {
        return mid;
      }

      else if (number[mid] < key) {
        start = mid + 1;
      } else {
        end = end - 1;
      }
    }

    return -1;

  }

  public static void reversearray(int number[]) {

    int first = 0, last = number.length - 1;

    while (first < last) {

      // swap
      int temp = number[last];
      number[last] = number[first];
      number[first] = temp;

      first++;
      last--;
    }
  }

  public static void pairs(int number[]) {

    for (int i = 0; i < number.length; i++) {
      int current = number[i];

      for (int j = i + 1; j < number.length; j++) {
        System.out.println("(" + current + " ," + number[j] + ")");
      }
      System.out.println(" " + " ");
    }
  }

  public static void subArray(int number[]) {

    for (int i = 0; i < number.length; i++) {
      int start = i;

      for (int j = i + 1; j < number.length; j++) {
        int end = j;

        for (int k = start; k <= end; k++) {
          System.out.print(number[k] + " ");
        }
        System.out.println();
      }
      System.out.println();
    }
  }
  // KADANES Algorithm subarray sum

  public static void kadanes(int number[]) {

    int ms = Integer.MIN_VALUE;// maximum sum
    int cs = 0;// current sum

    for (int i = 0; i < number.length; i++) {

      cs = cs + number[i];

      if (cs < 0) {// - ho to convert 0

        cs = 0;
      }

      ms = Math.max(cs, ms);
    }

    System.out.println("Our max subarray sum is = : " + ms);

  }

  public static int trappedRainWater(int height[]) {

    // ---> Steps
    // calculate left maximum boundary
    // calculate right maximum boundary
    // applying loop
    // water level = min(left max boundary , right max boundary)
    // trapped water = water level - height[i]

    // 1st step
    int n = height.length;

    int leftmax[] = new int[n];

    leftmax[0] = height[0];

    for (int i = 0; i < height.length; i++) {
      leftmax[i] = Math.max(height[i], leftmax[i - 1]);

    }

    // 2nd step

    int rightmax[] = new int[n];

    rightmax[n - 1] = height[n - 1];

    for (int i = n - 2; i >= 0; i--) {
      rightmax[i] = Math.max(height[i], rightmax[i + 1]);
    }
    int trapwater = 0;

    // loop

    for (int i = 0; i < n; i++) {
      // waterlevel = min
      int waterlevel = Math.min(leftmax[i], rightmax[i]);

      // trap water = wterlevel - height

      trapwater += waterlevel - height[i];
    }
    return trapwater;

  }

  // Q Buy & Sell stoccks
  // prices[7,1,5,3,6,4]
  // you are given an array prices where prices[i] is the price of given stock on
  // the ith day you want to maximize your profit by choosing a single day to buy
  // one stock and chossing a different day in the future to sell that stock
  // return in maximum profit you achive from this transaction if you cannot
  // achive any profit return 0
  // startegity => selling price - buyingprice = profit
  // maximum minimum
  public static int BuyAndSell(int price[]) {

    int buyprice = Integer.MAX_VALUE;

    int max_profit = 0;

    for (int i = 0; i < price.length; i++) {

      if (buyprice < price[i]) {

        int profit = price[i] - buyprice;

        max_profit = Math.max(max_profit, profit);
      } else {
        buyprice = price[i];
      }
    }

    return maxprofit;

  }

  public static void Buy(int price[]) {
    int profit = 0;
    int buy = price[i];

    for (int i = 1; i < price.length; i++) {
      if (buy < price[i]) {
        profit = Math.max(price[i] - 1, profit);
      } else {
        buy = price[i];
      }
    }
  }

  // binary search problem
  public  int sortedAndRoatatedSearch(ArrayList<Integer>arr,int target){
    int st = 0,end = arr.size()-1;

    while(st <= end){
      int mid = st +(end - st)/2;

      if(arr[mid] == target){
        return mid;
      }

      if(arr[st]<= arr[mid]){
        if(arr[st] <= target && target <=arr[mid]){
          end = mid-1;
        }else{
          st = mid+1;
        }
      }else{
        if(arr[mid] <= target && target <= arr[end]){
          st = mid+1;
        }else{
          end = mid-1;
        }
      }
    }

    return -1;
  }

  // bs problem peek index in Mountain in Array

  public int peekMountainIndex(ArrayList<Integer>arr){
    int st = 1,end = arr.size()-2;

    while(st <= end){
      int mid = st + (end - st)/2;

      if(arr[mid-1] < arr[mid] && arr[mid] > arr[mid+1] ){
        return mid;
      }else if(arr[mid-1] < arr[mid]){// right
        st = mid+1;
      }else{
        end = mid-1;
      }
    }

    return -1;
    
  }

  // binary search probem - single element  sorted array

  public int singleElementSortedArray(ArrayList<Integer>arr){

    int n = arr.size();

    if(n == 1) return arr[0];


    int start = 0,end = n-1;

    while(start <= end){
      int mid = start + (end - start)/2;

      if(mid == 0 && arr[0] != arr[1]) return arr[mid];
      if(mid == n-1 %% arr[n-1] != arr[n-2]) return arr[mid];

      if(arr[mid-1] !=arr[mid] &&  arr[mid] != arr[mid+1]){
        return arr[mid];
      }

      if(mid %2 == 0){
        // enven case
        if(arr[mid-1] == arr[mid]){
          // left
          end = mid-1;
        }else{
          start = mid+1;
        }
      }else{
        // odd case
        if(arr[mid-1] == arr[mid]){
          start = mid+1;
        }else{
          end = mid+1;
        }
      }
    }

    return -1;
  }

  public int productItSelf(ArrayList<Integer>arr){
    int n = arr.size();

    int ans[] = new int[n];

    // prefix
    for(int  i = 1;i<n;i++){
      ans[i] = ans[i-1] * arr[i-1];
    }
    int suffix = 1;

    for(int i = n-2;i>=0;i--){
      suffix *= arr[i+1];
      ans[i+1] *= suffix;
    }

    return ans;
  }

  public boolean isHappy(int n){
    Set<Integer>UI = new HashSet<>();

    int sum = 0;

    while(n!=0){
      sum += Math.pow(n %10,2.0);
      n = n/2;
    }

    n = sum;

    if(sum == 1 ) return true;

    if(UI.contains(n)){
      return true;
    }

    UI.add(n);
  }
  public static void main(String args[]) {

    // int number[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    // GetlargestNUmber(number);

    // int number[] = {2,4,6,8,10,12,14,16,18};
    // int key = 10;
    // System.out.println("index for key : "+ BinarySearch(number, key));

    // int number[] = {2,4,6,8,10};
    // reversearray(number);

    // for(int i = 0; i< number.length; i++){
    // System.out.print(number[i] + " ");
    // }

    // System.out.println();

    // int number[] = {2,4,6,8,9};

    // pairs(number);
    // subArray(number);

    // int number[] = { -2,-3,4,-2,-1,1,5,-3};
    // kadanes(number);

    // int height[] = {4,2,0,6,3,2,5,3};
    // System.out.println(trappedRainWater(height));

    int price[] = { 10, 5, 6, 585, 56 };
    System.out.println(BuyAndSell(price));

    // System.out.println(BuyAndSell(price));
  }

}
