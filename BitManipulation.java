import java.util.*;
public class BitManipulation {
    public static void checkifoddeven(int n){
        int bitmask = 1;
        if((n & bitmask) == 0){
            System.out.println("even number");
        }else{
            System.out.println("odd number");
        }
    }
    public static int setithbit(int n,int i){
      int bitmask = 1<<i;
      return n|bitmask;
    }
    public static int getithbit(int n, int i){
        int bitmask = 1<<i;
        if((n & bitmask) == 0){
            return 0;
        }else{
            return 1;
        }
    }
    public static int clearithbit(int n, int i){
        int bitmask = ~(i<<i);
        return n & bitmask;
    }
    public static int updateithbit(int n, int i, int newbit){
       /*if(newbit ==0;){
          return clearithbit(n, i);
        }else{
               return setithbit(n, i);
        }*/
         n = clearithbit(n, i);
        int bitmask = newbit<<i;
        return n|bitmask;
    }


    public static int clearlastithbit(int n,int i){
        int bitmask = (~0)<<i;//0=>-1
        return n & bitmask;
    }

    public static int clearibitsinranger(int n,int i,int j){
        int a = ((~0)<<(j+1));
        int b = (1<<i)-1;
        int bitmask = a | b;
        return n & bitmask; 
    }

    public static boolean checkifnumberpoweriftwo(int n){
        return (n & (n-1)) == 0;
    }

    public static int countsetbit(int n){// TC O(logn)
        int count = 0;
        while(n > 0){ // check if LSB
            if((n&1) != 0){
                count++;
            }
            n = n>>1;
        }
        return count;
    }

    public static int fastexponention(int a, int n){
        int ans = 1;
        while(n>0){
            if((n & 1) != 0){ // check LSB
                ans = ans * a;
            }
            a = a*a;
            n = n>>1;
        }
        return ans;
    }


    
    public static void main(String args[]){

        //System.out.println(updateithbit(10, 2,1));
       // System.out.println(clearlastithbit(15, 2));
       //System.out.println(clearibitsinranger(10,2,4));
       //System.out.println(checkifnumberpoweriftwo(166));
       //System.out.println(countsetbit(16));
       //System.out.println(fastexponention(5, 3));

        
       
    }
    

}
