public class JAVA {
    public static void bintodec(int binNum){
        int mynum = binNum;
        int pow = 0;
        int decnum = 0;
        while(binNum>0){
            int lastdigit = binNum % 10;
            decnum = decnum+(lastdigit * (int)Math.pow(2, pow));
             pow++;
             binNum = binNum / 10;
        }
        System.out.println("Deciof :"  + mynum +" " + decnum);
    }
    public static void dectobin(int n) {
        int mynum = n;
        int pow = 0;
        int binnum = 0;
        while(n>0){
            int rem = n%2;
            binnum = binnum+(rem*(int)Math.pow(10, pow));
            pow++;
            n = n/2;
        }
        System.out.println("BinOf : " + mynum + " "+ binnum);
        
    }
    public static void main (String args[]){
        bintodec(1010);
        dectobin(10);

    }
}