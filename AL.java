import java.util.ArrayList;

public class AL {

    // Brute Force - O(n^2)
    public static int FMWC(ArrayList<Integer> height) {
        int maxWater = 0;
        for (int i = 0; i < height.size(); i++) {
            for (int j = i + 1; j < height.size(); j++) {
                int ht = Math.min(height.get(i), height.get(j)); // container ht
                int width = j - i; // container width
                int currWater = ht * width;
                maxWater = Math.max(maxWater, currWater);
            }
        }

        return maxWater;
    }

    // Optimized (2 pointer) - O(n)
    public static int findMaxWaterContainer(ArrayList<Integer> height) {
        int leftPtr = 0, rightPtr = height.size() - 1;
        int maxWater = 0;
        while (leftPtr < rightPtr) {
            int ht = Math.min(height.get(leftPtr), height.get(rightPtr));
            int width = rightPtr - leftPtr;
            int currWater = ht * width;
            maxWater = Math.max(currWater, maxWater);

            // move ptrs
            if (height.get(leftPtr) < height.get(rightPtr)) {
                leftPtr++;
            } else {
                rightPtr--;
            }
        }

        return maxWater;
    }

    // Brute Force
    // public static boolean pairSum1(ArrayList<Integer> list, int target) {

    // for(int i=0; i<list.size(); i++) {
    // for(int j=i+1; j<list.size(); j++) {
    // if(list.get(i) + list.get(j) == target) {
    // return true;
    // }
    // }
    // }

    // return false;
    // }

    // 2 pointer approach
    public static boolean pairSum1(ArrayList<Integer> list, int target) {
        int lp = 0;
        int rp = list.size() - 1;

        while (lp != rp) {
            // case 1
            if (list.get(lp) + list.get(rp) == target) {
                return true;
            }

            // case 2
            if (list.get(lp) + list.get(rp) < target) {
                lp++;
            } else {
                // case 3
                rp--;
            }
        }

        return false;
    }

    public static void swap(ArrayList<Integer> list, int idx1, int idx2) {
        int temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }

    public static void printMax(ArrayList<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        System.out.println("max = " + max);
    }

    // // O(n)
    // public static boolean pairSum2(ArrayList<Integer> list, int target) {
    // int bp = -1;
    // int n = list.size();
    // for (int i = 0; i < list.size(); i++) {
    // if (list.get(i) > list.get(i + 1)) { // breaking point
    // bp = i;
    // break;
    // }
    // }

    // int lp = bp + 1; // smallest
    // int rp = bp; // largest

    // while (lp != rp) {
    // // case1
    // if (list.get(lp) + list.get(rp) == target) {
    // return true;
    // }

    // // case 2
    // if (list.get(lp) + list.get(rp) < target) {
    // lp = (lp + 1) % n;
    // } else {
    // // case 3
    // rp = (n + rp - 1) % n;
    // }
    // }

    // return false;
    // }

    // 2 Pointer for Sorted Array
    public static boolean findSum1(ArrayList<Integer> list, int target) {
        int lp = 0, rp = list.size() - 1;
        while (lp < rp) {
            if (lp + rp == target) {
                return true;
            }
            if (lp + rp < target) {
                lp++;
            } else {
                rp--;
            }
        }

        return false;
    }

    public static boolean pairSum2(ArrayList<Integer> list, int target) {
        int bp = -1;
        int n = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > list.get(i + 1)) { // breaking point
                bp = i;
                break;
            }
        }

        int lp = bp + 1; // smallest
        int rp = bp; // largest

        while (lp != rp) {
            // case1
            if (list.get(lp) + list.get(rp) == target) {
                return true;
            }

            // case 2
            if (list.get(lp) + list.get(rp) < target) {
                lp = (lp + 1) % n;
            } else {
                // case 3
                rp = (n + rp - 1) % n;
            }
        }

        return false;
    }

    public static void main(String args[]) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<Boolean> list3 = new ArrayList<>();

        // Add Operation
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        System.out.println(list);

        // Get Operation
        System.out.println(list.get(0));

        // Delete Operation
        list.remove(1); // 1 is index
        System.out.println(list);

        // Modify Operation
        list.set(0, 2);
        System.out.println(list);

        // Contains Operation
        System.out.println(list.contains(3));
        System.out.println(list.contains(4));

        // Size of list
        System.out.println(list.size());

        // Iteration
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        ArrayList<Integer> height = new ArrayList<>();
        height.add(1);
        height.add(8);
        height.add(6);
        height.add(2);
        height.add(5);
        height.add(4);
        height.add(8);
        height.add(3);
        height.add(7);

        System.out.println(FMWC(height));
        System.out.println(findMaxWaterContainer(height));

        // 11, 15, 6, 8, 9, 10 - Sorted & Rotated
        list.add(11);
        list.add(15);
        list.add(6);
        list.add(8);
        list.add(9);
        list.add(10);
        int target = 100;
        System.out.println(pairSum2(list, target));

        // ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
        // ArrayList<Integer> list1 = new ArrayList<>();
        // ArrayList<Integer> list2 = new ArrayList<>();
        // ArrayList<Integer> list3 = new ArrayList<>();

        mainList.add(list1);
        mainList.add(list2);
        mainList.add(list3);

        for (int i = 1; i <= 5; i++) {
            list1.add(i * 1);
            list2.add(i * 2);
            list3.add(i * 3);
        }

        // print all numbers
        for (int i = 0; i < mainList.size(); i++) {
            ArrayList<Integer> currList = mainList.get(i);
            for (int j = 0; j < currList.size(); j++) {
                System.out.print(currList.get(j) + " ");
            }
            System.out.println();
        }
    }

}
