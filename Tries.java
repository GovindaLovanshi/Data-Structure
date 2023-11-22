import java.util.*;

public class Tries {

    static class Node {
        Node Children[] = new Node[26];
        boolean eow = false; // eow = end of the word

        public Node() {
            for (int i = 0; i < 26; i++) {
                Children[i] = null;
            }
        }
    }

    // public static Node root = new Node();

    // /*
    // * public static void insertion(String word) {
    // * Node curr = root;
    // *
    // * for (int level = 0; level < word.length(); level++) {// O(L) l = largest
    // word
    // * kki length
    // * int idx = word.charAt(level) - 'a';
    // * if (curr.Children[idx] == null) {
    // * curr.Children[idx] = new Node(); // craete new Node
    // * }
    // * curr = curr.Children[idx];
    // * }
    // *
    // * curr.eow = true;
    // * }
    // */

    // public static boolean search(String key) {

    // Node curr = root;
    // for (int level = 0; level < key.length(); level++) {
    // int idx = key.charAt(level) - 'a';
    // if (curr.Children[idx] == null) {
    // return false;

    // curr = curr.Children[idx];
    // }

    // }

    // return curr.eow == true;
    // }

    // /*
    // * word break problem
    // * given an input string and a dicitionary of words find out if the input
    // string
    // */

    // public static boolean wordBreak(String key) { // o(n) key ki length

    // for (int i = 1; i <= key.length(); i++) {
    // if (search(key.subString(0, i)) &&
    // wordBreak(key.subString(i))) {
    // return true;
    // }
    // }
    // return false;
    // }

    // /* prefix problem */

    // static class Node {
    // Node Children[] = new Node[26];
    // boolean eow = false; // eow = end of the word
    // int freq;

    // public Node() {
    // for (int i = 0; i < Children.length; i++) {
    // Children[i] = null;
    // }
    // freq = 1;
    // }
    // }

    // public static Node root = new Node();

    // public static void insert(String word) {

    // Node curr = root;

    // for (int i = 0; i < word.length(); i++) {
    // int idx = word.charAt(i) - 'a';
    // if (curr.Children[idx] == null) {
    // curr.Children[i] = new Node();
    // } else {
    // curr.Children[idx].freq++;
    // }

    // curr = curr.Children[idx];
    // }

    // curr.eow = true;
    // }

    // public static void findPrefix(Node root, String ans) {// O(l) l = levels in
    // my

    // if (root == null) {
    // return;
    // }

    // if (root.freq == 1) {
    // System.out.println(ans);
    // return;
    // }

    // for (

    // int i = 0; i < root.Children.length; i++) {
    // if (root.Children[i] != null) {
    // findPrefix(root.Children[i], ans + (char) (i + 'a'));
    // }
    // }

    // for (

    // int i = 0; i < root.Children.length; i++) {
    // if (root.Children[i] != null) {
    // findPrefix(root.Children[i], ans + (char) (i + 'a'));
    // }
    // }
    // }

    // // starts with problem*

    // // public static boolean starsWith(String prefix) {// tc O(L)
    // // Node curr = root;

    // // for (int i = 0; i < prefix.length(); i++) {
    // // int idx = prefix.charAt(i) - 'a';
    // // if (curr.Children[idx] == null) {
    // // return false;
    // // }

    // // curr = curr.Children[idx];
    // // }

    // // return true;
    // // }

    // public static int countNode(Node root) {
    // if (root == null) {
    // return 0;
    // }

    // int count = 0;

    // for (int i = 0; i < 26; i++) {// a b c d e f . . . . . loop ults krne pr
    // apply ans aa jayega
    // if (root.Children[i] != null) {
    // count += countNode(root.Children[i]);
    // }
    // }

    // return count + 1;
    // }

    // /* largest word with all the prefix */
    // public static String ans = "";

    // public static void longestWord(Node root, StringBuilder temp) {
    // if (root == null) {
    // return;
    // }

    // for (int i = 0; i < 26; i++) {
    // if (root.Children[i] != null && root.Children[i].eow == true) {
    // char ch = (char) (i + 'a');
    // temp.append(ch);
    // if (temp.length() > ans.length()) {
    // ans = temp.toString();
    // }
    // longestWord(root.Children[i], temp);
    // temp.deleteCharAt(temp.length() - 1);// backtrack
    // }
    // }
    // }

    public static void main(String args[]) {

        // String words[] = { "the", "a", "there", "their", "any", "thee" };

        /*
         * for (int i = 0; i < words.length(); i++) {
         * insertion(words[i]);
         * }
         * 
         * 
         */
        // System.out.println(search(thee));

        /*
         * String arr[] = {"i","like","sam""samsung","mobile","ice"};
         * 
         * for(int i=0; i<arr.length;i++){
         * search(arr[i]);
         * }
         * 
         * String key = "ilikesamsung";
         * 
         * System.out.println(wordBreak(key));
         *
         *
         */

        /*
         * String arr[] = {"zebra","dog","duck","dov"};
         * for(int i=0; i<arr.length;i++){
         * insert(arr[i]);
         * }
         * 
         * root.freq = -1;
         * findPrefix(root,"");
         */

        /*
         * String words[] = { "apple", "app", "mango", "woman" };
         * String prefix1 = "app";
         * String prefix2 = "moon";
         * 
         * // create a trie
         * for (int i = 0; i < words.length; i++) {
         * insert(words[i]);
         * }
         * 
         * System.out.println(starsWith(prefix1));
         * System.out.println(starsWith(prefix2));
         */

        /*
         * String str = { "ababa" };
         * 
         * // suffix --> insert in trie
         * for (int i = 0; i < str.length(); i++) {
         * String suffix = str.subString(i);// find suffix
         * insert(suffix);// create trie insert
         * }
         * 
         * System.out.println(countNode(root));
         */

        String words[] = { "a", "banana", "app", "appl", "ap", "apply", "apple" };

        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }

        longestWord(root, new StringBuilder(""));
        System.out.println(ans);

    }
}
