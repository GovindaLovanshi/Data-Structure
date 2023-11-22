import java.util.*;
public class String {

    public static boolean Pallindrome(String Str){

        for(int i=0; i<Str.length()/2; i++){
            int n = Str.length();

            if(Str.charAt(i) != Str.charAt(n-i-1)){
                // not a pallindrom
                return false;
            }
           
        }
        return true;
    }

    // given a route containing 4 direction (E W N S) find the shortest path to reach destination
    public static float getshortestpath(String path){

        int x=0 , y=0;

        for(int i=0; i<path.length();i++){
            char dir = path.charAt(i);

            // south
          if(dir == 'S'){
            y--;
          }   // north
          else if(dir == 'N'){
            Y++;
          }
            // west
            else if(dir == 'w'){
              x--;
            }
            // east
            else{
             x++;
            }

        }
        

        int x2 = x*x;
        int y2 = y*y;
        return (float)Math.sqrt(x2+y2); // math formula
    }

    
    
    public static String subString(String Str, int si,int ei) {

        String SubStr = " ";

        for(int i =si;i<ei; i++){
            SubStr += Str.charAt(i);
        }
        return SubStr;
    }
    // for given a set of string print the largest string 

    // for a given string convert to each the first letter of each word to uppercase

    public static String ToUppercase(String Str){ // O(n)

        StringBuilder sb = new StringBuilder(" ");
        
        char ch = Character.toUpperCase(Str.charAt(0));
        sb.append(ch);
       for(int i=1; i<Str.length;i++){
          if(Str.charAt(i) == ' ' && i<Str.length()-1){
             sb.append(Str.charAt(i));
             i++;
             sb.append(Character.toUpperCase(str.charAt(i)));
            }else{
                sb.append(Str.charAt(i));
            }
       }

       return sb.toString();

    }

    // String Compress
    // "aaabbcccdd", "a3b2c3d2"
    public static String Compress(String Str){
        String newstr = " ";

        // aaabc
        for(int i=0; i<Str.length; i++){
            Integer Count = 1;// Integer ->class
            while(I<Str.length()-1 && Str.charAt(i) == Str.charAt(i+1)){
                Count++;
                i++;
            }
            newstr += Str.chatAt(i);
            if(Count>1){
                newstr = newstr+Count.toString();
                       
            }

            
        }
        return newstr;

    }
    
   public static void main(String[] args) {
        String Str = "racecar";
        Pallindrome(Str);

     // String path = "WNEENESNNN";
      // System.out.println(getshortestpath(path));
      //String Str = "Hello World";
      //System.out.println(subString(Str, 0, 5));     
      // System.out.println(Str.Substring(0,5));
       
     /* String Fruits[] = {"apple","mango","banana"};

      String largest = Fruits[0];
      for(int i=1; i<Fruits.length; i++){
        if(largest.compareTo(Fruits[i]) < 0){
            largest = Fruits[i];
        }
      }

      System.out.println(largest);

     // String Str = "my name is Exactra";
      //System.out.println(ToUppercase(Str));
      String Str = "aaabbcccdd";

      System.out.println(Compress(Str)); // Time Complexity O(n) --> reson i++
      */
    }
}