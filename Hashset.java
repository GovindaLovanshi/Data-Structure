import java.util.*;

//import javax.sound.sampled.SourceDataLine;

public class Hashset {
    // find latiner array from tickets
    // 'mumbai -> Beng'
    // mumbai -> delhi
    // goa -> chennai
    // delhi -> goa

    public static String getstart(HashMap<String, String> tickets) {
        HashMap<String, String> revMap = new HashMap<>();

        for (String key : tickets.keySet()) {
            revMap.put(tickets.get(key), key);
        }

        for (String key : tickets.keySet()) {
            if (!revMap.containsKey(key)) {
                return key;
            }
        }

        return null;
    }

    public static void main(String args[]) {
        /*
         * // union & Intersection of 2 arrays O(n+m) linre
         * // arr1 = {7,3,9} arr2 = {6,3,9,2,9,4}
         * int arr1[] = { 7, 3, 9 };
         * int arr2[] = { 6, 3, 9, 2, 9, 4 };
         * HashSet<Integer> set = new Hashset<>();
         * 
         * // union
         * for (int i = 0; i < arr1.length; i++) {
         * set.add(arr1[i]);
         * }
         * 
         * for (int i = 0; i < arr2.length; i++) {
         * set.add(arr2[i]);
         * }
         * 
         * System.out.println("union = " + set.size());
         * 
         * /*
         * for (int i = 0; i < set.size(); i++) {
         * System.out.println(arr[i]);
         * }
         */

        // intersection
        /*
         * set.clear();
         * 
         * for (int i = 0; i < arr1.length; i++) {
         * set.add(arr1[i]);
         * }
         * int count = 0;
         * for (int i = 0; i < arr2.length; i++) {
         * if (set.contains(arr2[i])) {
         * count++;
         * set.remove(arr2[i]);
         * }
         * }
         * 
         * System.out.println("my intersection =" + count);
         */

        /*
         * HashMap<String, String> tickets = new HashMap<>();
         * tickets.put("Chennai", "Bengaluru");// O(n)
         * tickets.put("mumbai", "Delhi");
         * tickets.put("Goa", "Chennai");
         * tickets.put("Delhi", "Goa");
         * 
         * String start = getstart(tickets);
         * System.out.println(start);
         * 
         * for (String key : tickets.keySet()) {
         * System.out.print("-->" + tickets.get(start));
         * start = tickets.get(start);
         * }
         * System.out.println();
         */

        /*
         * largest sub array with 0 sum
         * arr = {15-2,2,-8,,1,7,10}
         */

        /*
         * int arr[] = { 15, -2, 2, -8, 1, 7, 10 };// O(n)
         * 
         * HashMap<Integer, Integer> map = new HashMap<>();
         * 
         * int sum = 0;
         * int length = 0;
         * 
         * for (int j = 0; j < arr.length; j++) {
         * sum += arr[j];
         * if (map.containsKey(sum)) {
         * length = Math.max(length, j - map.get(sum));
         * } else {
         * map.put(sum, j);
         * }
         * }
         * 
         * System.out.println("largest sub array with sum =>" + length);
         */

        /*
         * sub a array sum equal to k
         * arr= {1 2 3} k=3
         * ans = 2 {1 2 } {3}
         * sum(j)-k = sum(i) ==> formula
         */

        int arr[] = { 10, 2, -2, -20, 10 };// TC O(n)
        int k = -10;

        HashMap<Integer, Integer> map = new HashMap<>();

        // (sum count)
        map.put(0, 1);

        int sum = 0;
        int ans = 0;

        for (int j = 0; j < arr.length; j++) {
            sum += arr[j]; // sum(j)
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
        }

        map.put(sum, map.getOrDefault(sum, 0) + 1);

        System.out.println(ans);
    }

}