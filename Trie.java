import java.util.*;

import org.w3c.dom.Node;

public class Trie {
    // Group Anagrams Together
    // Given an array of strings strs, group the anagrams together. You can return
    // the answer in any
    // order.
    // An Anagram is a word or phrase formed by rearranging the letters of a
    // different word or
    // phrase, typically using all the original letters exactly once.
    // Sample Input 1 : strs = ["eat","tea","tan","ate","nat","bat"]
    // Sample Output 1 : [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
    // Sample Input 2 : strs = [""]
    // Sample Output 2 : [[""]]
    // Sample Input 3 : strs = ["a

    class TrieNode {
        List<String> data;
        TrieNode children[];
        boolean isEnd;

        TrieNode() {
            data = new ArrayList<>();
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    static TrieNode root;
    List<List<String>> ans;

    public List<List<String>> groupAnagrams(String[] strs) {
        ans = new ArrayList<>();
        root = new TrieNode();
        for (String word : strs) {
            build(word);
        }
        dfs(root);
        return ans;
    }

    public void build(String s) {
        TrieNode temp = root;
        char[] word = s.toCharArray();
        Arrays.sort(word);
        for (char c : word) {
            TrieNode child = temp.children[c - 'a'];
            if (child == null)
                temp.children[c - 'a'] = new TrieNode();
            temp = temp.children[c - 'a'];
        }
        temp.isEnd = true;
        temp.data.add(s);
    }

    public void dfs(TrieNode rt) {
        if (rt.isEnd) {
            ans.add(rt.data);
        }
        for (int i = 0; i < 26; i++) {
            if (rt.children[i] != null)
                dfs(rt.children[i]);
        }
    }

    // Question 2 : MEDIUM
    // Longest Word in Dictionary
    // Given an array of strings words representing an English Dictionary, return
    // the longest word in
    // words that can be built one character at a time by other words in words.
    // If there is more than one possible answer, return the longest word with the
    // smallest
    // lexicographical order. If there is no answer, return the empty string.
    // Note that the word should be built from left to right with each additional
    // character being
    // added to the end of a previous word.
    // Sample Input 1 : words = ["w","wo","wor","worl","world"]
    // Sample Output 1 : "world"
    // The word "world" can be built one character at a time by "w", "wo", "wor",
    // and "worl".
    // Sample Input 2 : words = ["a","banana","app","appl","ap","apply","apple"]
    // Sample Output 2 : "apple"
    // Both "apply" and "apple" can be built f

    private static class Node {
        private char data;
        private String word;
        private boolean isEnd;
        private Node[] children;

        public Node(char data) {
            this.data = data;
            this.word = null;
            this.isEnd = false;
            this.children = new Node[26];
        }
    }

    private Node root = new Node('/');
    private String ans = "";

    private void insert(String word) {
        Node curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            int childIdx = word.charAt(i) - 'a';
            if (curr.children[childIdx] == null) {
                curr.children[childIdx] = new Node(word.charAt(i));
            }
            curr = curr.children[childIdx];
        }
        curr.isEnd = true;
        curr.word = word;
    }

    private void dfs(Node node) {
        if (node == null) {
            return;
        }
        if (node.word != null) {
            if (node.word.length() > ans.length()) {
                ans = node.word;
            } else if (node.word.length() == ans.length() &&
                    node.word.compareTo(ans) < 0) {
                ans = node.word;
            }
        }
        for (Node child : node.children) {
            if (child != null && child.word != null) {
                dfs(child);
            }
        }
    }

    public String longestWord(String[] words) {
        for (String word : words) {
            insert(word);
        }
        Node curr = this.root;
        dfs(curr);
        return ans;
    }

    public static void main(String[] args) {

    }

}