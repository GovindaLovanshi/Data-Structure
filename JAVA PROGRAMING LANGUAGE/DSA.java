import java.util.*;

public class DSA {

    // Rat in a Maze
    // You are given a starting position for a rat which is stuck in a maze at an
    // initial point (0, 0) (the
    // maze can be thought of as a 2-dimensional plane). The maze would be given in
    // the form of a
    // square matrix of order N * N where the cells with value 0 represent the
    // maze’s blocked
    // locations while value 1 is the open/available path that the rat can take to
    // reach its destination.
    // The rat's destination is at (N - 1, N - 1).
    // Your task is to find all the possible paths that the rat can take to reach
    // from source to
    // destination in the maze.
    // The possible directions that it can take to move in the maze are 'U'(up) i.e.
    // (x, y - 1) , 'D'(down)
    // i.e. (x, y + 1) , 'L' (left) i.e. (x - 1, y), 'R' (right) i.e. (x + 1, y)

    // solution Algorithm
    // . Create a solution matrix, initially filled with 0’s.
    // 2. Create a recursive function, which takes the initial matrix, output matrix
    // and position
    // of rat (i, j).
    // 3. if the position is out of the matrix or the position is not valid then
    // return.
    // 4. Mark the position output[i][j] as 1 and check if the current position is
    // destination or
    // not. If destination is reached print the output matrix and return.
    // 5. Recursively call for position (i+1, j) and (i, j+1).
    // 6. Unmark position (i, j), i.e output[i][j] = 0.

    public static void printSolutio(int solution[][]) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                System.out.print(" " + solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSafe(int maze[][], int x, int y) {
        // if x y oytside maze return false
        // if (x >= 0 && x < maze.length && y >= 0 && y < maze.length && maze[x][y] ==
        // 1) {
        // return false;
        // }
        return (x >= 0 && x < maze.length && y >= 0 && y < maze.length && maze[x][y] == 1);
    }

    public static boolean solveMaze(int maze[][]) {
        int N = maze.length;
        int solution[][] = new int[N][N];
        if (solveMazeUtil(maze, 0, 0, solution) == false) {
            System.out.print("Solution does not extist");
            return false;
        }

        printSolutio(solution);
        return true;
    }

    public static boolean solveMazeUtil(int maze[][], int x, int y, int solution[][]) {
        if (x == maze.length - 1 && y == maze.length - 1 && maze[x][y] == 1) {
            solution[x][y] = 1;
            return true;
        }

        // check if maze[x][y] is valid
        if (isSafe(maze, x, y) == true) {
            if (solution[x][y] == 1) {
                return false;
            }
            if (solveMazeUtil(maze, x + 1, y, solution)) {
                return true;
            }
            if (solveMazeUtil(maze, x, y + 1, solution)) {
                return true;
            }

            solution[x][y] = 0;
            return false;
        }

        return false;
    }

    // question 2
    // Keypad Combinations
    // Given a string containing digits from 2-9 inclusive, print all possible
    // letter combinations that
    // the number could represent. You can print the answer in any order.
    // A mapping of digits to letters (just like on the telephone buttons) is given
    // below. Note that 1
    // does not map to any letters.

    // ample Input 1 : digits = "23"
    // Sample Output 1 : "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
    // Sample Input 2 : digits = "2"
    // Sample Output 2 : "a", "b", "c"
    // Sample Input 3 : digits = ""
    // Sample Output 3 : ”

    final static char[][] L = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
            { 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' },
            { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

    public static void letterCombination(String D) {
        int len = D.length();
        if (len == 0) {
            System.out.println("");
            return;
        }

        bfs(0, len, new StringBuilder(), D);
    }

    public static void bfs(int pos, int len, StringBuilder sb, String D) {
        if (pos == len) {
            System.out.println(sb.toString());
        } else {
            char[] letters = L[Character.getNumericValue(D.charAt(pos))];
            for (int i = 0; i < letters.length; i++) {
                bfs(pos + 1, len, new StringBuilder(sb).append(letters[i]), D);
            }
        }
    }

    // Knight’s Tour
    // Given a N*N board with the Knight placed on the first block of an empty
    // board. Moving
    // according to the rules of chess, knights must visit each square exactly once.
    // Print the order of
    // each cell in which they are visited.
    // Sample Input 1 : N = 8
    // Sample Output 1 :
    // 0 59 38 33 30 17 8 63
    // 37 34 31 60 9 62 29 16
    // 58 1 36 39 32 27 18 7
    // 35 48 41 26 61 10 15 28
    // 42 57 2 49 40 23 6 19
    // 47 50 45 54 25 20 11 14
    // 56 43 52 3 22 13 24 5
    // 51 46 55 44 53 4 21 12

    static int N = 8;

    public static boolean isSafe(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N
                && sol[x][y] == -1);
    }

    public static void printSolution(int sol[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.print(sol[x][y] + " ");
            System.out.println();
        }
    }

    public static boolean solveKT() {
        int sol[][] = new int[8][8];
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                sol[x][y] = -1;
        int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        // As the Knight starts from cell(0,0)
        sol[0][0] = 0;
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
            return false;
        } else {
            printSolution(sol);
        }
        return true;
    }

    public static boolean solveKTUtil(int x, int y, int movei, int sol[][], int xMove[], int yMove[]) {
        int k;
        int next_x;
        int next_y;
        if (movei == N * N) {
            return true;
        }

        for (k = 0; k < 8; k++) {
            next_x = x + xMove[k];
            next_y = y + yMove[k];
            if (isSafe(next_x, next_y, sol)) {
                sol[next_x][next_y] = movei;
                if (solveKTUtil(next_x, next_y, movei + 1,
                        sol, xMove, yMove)) {
                    return true;
                } else {
                    sol[next_x][next_y] = -1; // backtracking
                }
            }
        }

        return false;
    }

    // Check if a Binary Tree is univalued or not
    // We have a binary tree, the task is to check if the binary tree is univalued
    // or not. If found to be
    // true, then print “YES”. Otherwise, print “NO”.

    // static class Node {// Tc O(h) Sc O(1)
    // int data;
    // Node left;
    // Node right;

    // static Node newNode(int data) {
    // Node temp = new Node();
    // temp.data = data;
    // temp.left = temp.right = null;
    // return (temp);
    // }

    // }

    // static boolean isUnivalTree(Node root) {
    // if (root == null) {
    // return true;
    // }
    // if (root.left != null && root.data != root.left.data) {
    // return false;
    // }
    // if (root.right != null && root.data != root.right.data) {
    // return false;
    // }

    // return isUnivalTree(root.left) && isUnivalTree(root.right);
    // }

    // Invert Binary Tree
    // Mirror of a Tree: Mirror of a Binary Tree T is another Binary Tree M(T) with
    // left and right
    // children of all non-leaf nodes interchanged.

    // Mirror of tree

    // class Node { // TC O(n) SC O(n)
    // int data;
    // Node left, right;

    // public Node(int item) {
    // data = item;
    // left = right = null;
    // }
    // }

    // class Solution {
    // Node root;

    // void mirror() {
    // root = mirror(root);
    // }

    // Node mirror(Node node) {
    // if (node == null) {
    // return node;
    // }

    // /* do the subtree */
    // Node left = mirror(node.left);
    // Node right = mirror(node.right);

    // /* swap left and right pointers */
    // node.left = right;
    // node.right = left;

    // return node;
    // }

    // void inOrder() {
    // inOrder(root);
    // }

    // void inOrder(Node node) {
    // if (node == null) {
    // return;
    // }

    // inOrder(node.left);
    // System.out.print(node.data + "");
    // inOrder(node.right);
    // }
    // }

    // Delete leaf nodes with values as x
    // We have a binary tree and a target integer x, delete all the leaf nodes
    // having value as x. Also,
    // delete the newly formed leaves with the target value as x.

    // static class Node {// Tc O(n) SC O(1)
    // int data;
    // Node left, right;

    // static Node newNode(int data) {
    // Node newNode = new Node();
    // newNode.data = data;
    // newNode.left = null;
    // newNode.right = null;
    // return (newNode);
    // }

    // }

    // static Node deleteLeaves(Node root, int x) {
    // if (root == null) {
    // return null;
    // }

    // root.left = deleteLeaves(root.left, x);
    // root.right = deleteLeaves(root.right, x);

    // if (root.data == x && root.left == null && root.right == null) {
    // return null;
    // }

    // return root;
    // }

    // static void inorder(Node root) {
    // if (root == null) {
    // return;
    // }

    // inorder(root.left);
    // System.out.print(root.data + " ");
    // inorder(root.right);
    // ;
    // }

    // Find All Duplicate Subtrees
    // We have a binary tree, find all duplicate subtrees. For each duplicate
    // subtree, we only need to
    // return the root node of any one of them. Two trees are duplicates if they
    // have the same
    // structure with the same node values.

    // static HashMap<String, Integer> m; // TC O(n * n) Sc O(n*n)

    // static class Node {
    // int data;
    // Node left;
    // Node right;

    // Node(int data) {
    // this.data = data;
    // left = null;
    // right = null;
    // }
    // }

    // static String inorder(Node root) {
    // if (root == null) {
    // return "";
    // }

    // String str = "(";
    // str += inorder(root.left);
    // str += Integer.toString(root.data);
    // str += inorder(root.right);
    // str += ")";

    // if (m.get(str) != null && m.get(str) == 1) {
    // System.out.print(root.data + "");
    // }

    // if (m.containsKey(str)) {
    // m.put(str, m.get(str) + 1);
    // } else {
    // m.put(str, 1);
    // }

    // return str;
    // }

    // static void printAllDuplicate(Node root) {
    // m = new HashMap<>();
    // inorder(root);
    // }

    // Maximum Path Sum in a Binary Tree
    // Wehaveabinary tree, find the maximum path sum. The path may start and end at
    // any node in
    // the tree.
    // Input1:

    class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    class Res {
        public int val;
    }

    class Solution {
        Node root;

        int findMaxUtil(Node node, Res res) {
            if (node == null)
                return 0;
            int l = findMaxUtil(node.left, res);
            int r = findMaxUtil(node.right, res);
            int max_single = Math.max(Math.max(l, r) + node.data,
                    node.data);
            int max_top = Math.max(max_single, l + r + node.data);
            res.val = Math.max(res.val, max_top);
            return max_single;
        }

        int findMaxSum() {
            return findMaxSum(root);

        }

        int findMaxSum(Node node) {
            Res res = new Res();
            res.val = Integer.MIN_VALUE;
            findMaxUtil(node, res);
            return res.val;
        }
    }// Tc O(n) Sc O(1)

    public static void main(String args[]) {
        int maze[][] = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
        solveMaze(maze);

        letterCombination("2");
        solveKT();

        // Node root = newNode(1);
        // root.left = newNode(1);
        // root.right = newNode(1);
        // root.left.left = newNode(1);
        // root.left.right = newNode(1);
        // root.right.right = newNode(1);

        // if (isUnivalTree(root)) {
        // System.out.print("yes");
        // } else {
        // System.out.print("No");
        // }

        // Node tree = new Node();
        // tree.root = new Node(1);
        // tree.root.left = new Node(2);
        // tree.root.right = new Node(3);
        // tree.root.left.left = new Node(4);
        // tree.root.left.left = new Node(5);

        // System.out.println("inorder traversal of input tree is :");
        // tree.inOrder();
        // System.out.println("");
        // tree.mirror();
        // System.out.println("inorder traversal of binary tree is :");
        // tree,inOrder();

        // deleteLeaves(root, 3);
        // System.out.println("inorder traversal after deletion");
        // inorder(root);

        // Node root = null;
        // root = new Node(1);
        // root.left = new Node(2);
        // root.right = new Node(3);
        // root.left.left = new Node(4);
        // root.right.left = new Node(2);
        // root.right.left.left = new Node(4);
        // root.right.right = new Node(4);
        // printAllDups(root);

        Solution tree = new Solution();
        tree.root = new Node(10);
        tree.root.left = new Node(2);
        tree.root.right = new Node(10);
        tree.root.left.left = new Node(20);
        tree.root.left.right = new Node(1);
        tree.root.right.right = new Node(-25);
        tree.root.right.right.left = new Node(3);
        tree.root.right.right.right = new Node(4);
        System.out.println("maximum path sum is : " + tree.findMaxSum());

    }
}