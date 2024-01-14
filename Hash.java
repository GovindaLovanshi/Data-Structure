import java.util.*;

public class Hash {
    // valid anagram // worst case O(n^2)
    public static boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);// increase frequency
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (map.get(ch) != null) { // ch value exist
                if (map.get(ch) == 1) {
                    map.remove(ch);
                } else {
                    map.put(ch, map.get(ch) - 1);// decresing freq
                }
            } else {
                return false;
            }
        }

        return map.isEmpty();
    }

    public static void main(String args[]) {
        /*
         * // majority element
         * int arr[] = { 1, 3, 2, 5, 1, 3, 1, 5, 1 };O(n)
         * HashMap<Integer, Integer> map = new HashMap<>();
         * 
         * for (int i = 0; i < arr.length; i++) {
         * map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
         * 
         * }
         * 
         * // Set<Integer> keySet = map.keySet();
         * 
         * for (Integer key : map.keySet()) {
         * if (map.get(key) > arr.length / 3) {
         * System.out.println(key);
         * }
         * }
         * 
         */

        String s = "race";// O(n)
        String t = "care";

        System.out.println(isAnagram(s, t));
    }
}
