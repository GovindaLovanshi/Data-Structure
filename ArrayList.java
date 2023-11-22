import java.util.*;

public class ArrayList {

    public static boolean PairSum1(ArrayList<Integer> list, int Target) {// bruite force approach TC o(n)
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; i < list.size(); j++) {
                if (size.get(i) + size.get(j) == Target) {
                    return true;
                }
            }
        }
        return false;
    }

    // pointer 2 approach
    public static boolean PairSum1(ArrayList<Integer> list, int Target) { // Tc O(n)

        int lp = 0;// left pointer
        int rp = list.size() - 1;// right pointer

        while (lp != rp) {
            // case 1
            if (list.get(lp) + list.get(rp) == Target) {
                return true;

            } // acse 2
            if (list.get(lp) + list.get(rp) < Target) {
                lp++;
            } // case 3
            else {
                rp--;
            }
        }
        return false;

    }

    // sorted and roatated arrray psir sum 2 // pointer approach
    public static boolean PairSum2(ArrayList<Integer> list, int Target) { // Tc O(n)
        // find pivot
        int bp = -1;// breaking point sorted array break
        int n = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (size.get(i) > Size.get(i + 1)) {
                bp = i;
                break;
            }
        }

        int lp = bp + 1; // smallest
        int rp = bp; // largest bp = i

        while (lp != rp) {
            // case 1
            if (list.get(lp) + list.get(rp) == Target) {
                return true;

            }
            if (list.get(lp) + list.get(rp) < Target) {
                lp = (lp + 1) % n;// rotation
            } else {
                rp = (n + rp - 1) % n;//
            }
        }
        return false;
    }

    // container with most water for a given lines oon a X aix use 2 line to fron
    // container such thai it holds maximum wataer
    public static int storeWater(ArrayList<Integer> height) {// TC O(n^2)
        int maxwater = 0;
        // bruite force
        for (int i = 0; i < height.size(); i++) {// peek first line O(n)
            for (int j = i + 1; j < height.size(); j++) {// peek second line O(n)
                int ht = Math.min(height.get(i), height.get(i));
                int width = i - j;
                int currwater = ht * width;
                maxwater = Math.max(maxwater, currline);
            }
        }
        return maxwater;
    }

    // container with most water for a given lines oon a X aix use 2 line to fron
    // container such thai it holds maximum wataer
    // 2 pointer approach
    public static int storeWater(ArrayList<Integer> height) { // TC O(n) pointer approach
        int lp = 0;
        int rp = height.size() - 1;
        int maxwater = 0;
        while (lp < rp) {
            // calculate water
            int ht = Math.min(height.get(lp), height.get(rp));
            int width = rp - lp;
            int currwater = ht * width;
            maxwater = Math.max(maxwater, currwater);

            // update pointer
            if (height.get(lp) < height.get(rp)) {
                lp++;
            } else {
                lp--;
            }
        }
        return maxwater;
    }

    public static void main(String args[]) {
        /*
         * ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
         * A rrayList<Integer>list1 = new ArrayList<>();
         * ArrayList<Integer> list2 = new ArrayList<>();
         * ArrayList<Integer> list3 = new ArrayList<>();
         * 
         * for(int i=1; i<=5; i++){
         * list1.add(i*1);
         * list2.add(i*2);
         * list3.add(i*3);
         * }
         * mainList.add(list1);
         * mainList.add(list2);
         * mainList.add(list3);
         * 
         * System.out.println(mainList);
         * // nested lpoop
         * for(int i=0; i<mainList.size(); i++){
         * ArrayList<Integer> Curr = new ArrayList<>();
         * for(int j=0; j<curr.size(); j++){
         * System.out.println(curr.get(j)+" ");
         * }
         * System.out.println();
         * 
         * }
         */
        // find if any pair in a sorted ArrayList Has a Target sum
        // make a lkist sorted and roatated
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

    }

}
