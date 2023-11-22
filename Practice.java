public class Practice {
   // public static int GetlargestNUmber(int number[]){
       // int largest = Integer.MIN_VALUE;

       // for(int i=0; i<number.length; i++){
       //     if(largest < number[i]){
       //        largest = number[i];
       //     }
       // }
      //   return largest;
   // }
   //public static int BinarySearch(int number[],int key){
         // int start = 0, end = number.length-1;

        //while(start <= end){
         // int mid = (start+end)/2;
          //if(number[mid] == key){
          //     return mid;
         // }
       //}
      //if(number[mid] < key){
     //   start = mid+1;
      //}else{
      //  end = end-1;
     // }

      //  return -1;

    //}
  
  public static void reversearray(int number[]){

    int first = 0 ,last = number.length-1;

    while(first< last){

      //swap
      int temp = number[last];
      number[last] = number[first];
      number[first] = temp;

      first++;
      last--;
    }
  }
public static void pairs(int number[]){

    for(int i=0; i<number.length; i++){
      int curr = number[i];

      for(int j=i+1; i<number.length; j++){
        System.out.println(curr + " " +  number[j]);
      }
      System.out.println(" " + " ");
    }
  }

  public static void subArray(int number[]){

    for(int i=0; i<number.length; i++){
      int start = i;

      for(int j=i; j<number.length; j++){
        int end =j;

        for(int k=start; k<=end; k++){
          System.out.print(number[k] + " ");
        }
        System.out.println();
      }
      System.out.println();
    }
  }
     // KADANES Algorithm

  public static void kadanes(it number[]){

    int ms = Integer.MIN_VALUE;
    int cs = 0;

    for(int i=0; i<number.length; i++ ){

      cs = cs + number[i];

      if(cs < 0){

        cs = 0;
      }

      ms = Math.max(cs,ms);
    }

    System.out.println("Our max subarray sum is = : " + ms);
 
  }

  public static int trappedRainWater(int height[]){

    //---> Steps
    // calculate left maximum boundary
    //  calculate right maximum boundary
    // applying loop
    // water level = min(left max boundary , right max boundary)
    // trapped water = waterlevel - height[i]

    //1st step 
    int n = height.length;

    int leftmax[] = new int [n];

    leftmax[0] = height[0];

    for(int i=0; i<height.length; i++){
      leftmax[i] = Math.max(height[i], leftmax[i-1]);

    }

    // 2nd step

    int rightmax [] = new int[n];

    rightmax[n-1] = height[n-1];

    for(int i=n-2; i>=0; i--){
      rightmax[i] = Math.max(height[i], rightmax[i+1]);
    }
      int trapwater = 0;

      //loop

    for(int i=0; i<n; i++){
        // waterlevel = min
        int waterlevel = Math.min(leftmax[i], rightmax[i]);
        
        // trap water = wterlevel - height

        trapwater += waterlevel - height[i];
    }
    return trapwater;


  }

  public static void main(String args[]){

   // int number[] = {1,2,3,4,5,6,7,8,9};
    //System.out.println("largest value of ;"+ GetlargestNUmber(number));

    //int number[] = {2,4,6,8,10,12,14,16,18};
    //int key = 10;
    //System.out.println("index for key  : "+ BinarySearch(number, key));
    

    //int number[] = {2,4,6,8,10};
   // reversearray(number);

    //for(int i = 0; i< number.length; i++){
    //  System.out.print(number[i] + " ");
    //}

    //System.out.println();

    //int number[] = {2,4,6,8,9};

    //pairs(number);
   // subArray(number);

     //int number[] = { -2,-3,4,-2,-1,1,5,-3};
     //kadanes(number);

     int height[]  = {4,2,0,6,3,2,5,3};
     System.out.println(trappedRainWater(height));
  } 
}