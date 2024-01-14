import java.util.*;

public class Graph {

    // Find Minimum Depth of a Binary Tree
    // We have a binary tree, find its minimum depth. The minimum depth is the
    // number of nodes along
    // the shortest path from the root node down to the nearest leaf node. For
    // example, minimum
    // height of below Binary Tree is 2.
    // Sample Input 1 :
    // 1
    // /
    // 8
    // \
    // 2
    // / \ /
    // 6 5 9
    // Sample Output 1 : 2

    static class Node {
        int data;
        Node left, right;
    }

    static class Qitem {
        Node node;
        int depth;

        public Qitem(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }

    }

    static int minDepth(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Qitem> q = new LinkedList<>();
        Qitem qi = new Qitem(root, 1);
        q.add(qi);

        while (q.isEmpty() == false) {
            qi = q.peek();
            q.remove();
            Node node = qi.node;
            int depth = qi.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }

            if (node.left != null) {
                qi.node = node.left;
                qi.depth = depth + 1;
                q.add(qi);
            }

            if (node.right != null) {
                qi.node = node.right;
                qi.depth = depth + 1;
                q.add(qi);
            }
        }

        return 0;
    }

    static Node newNode(int data) {
        Node temp = new Node();
        temp.data = data;
        temp.left = temp.right = null;
        return temp;
    }

    // Question 3 :
    // Minimum time required to rot all oranges
    // We have a matrix of dimension m*n where each cell in the matrix can have
    // values 0, 1 or 2
    // which has the following meaning:
    // 0: Empty cell 1: Cells have fresh oranges 2: Cells have rotten oranges
    // We have to determine what is the minimum time required so that all the
    // oranges become
    // rotten. A rotten orange at index [i,j] can rot other fresh orange at indexes
    // [i-1,j], [i+1,j], [i,j-1],
    // [i,j+1] (up, down, left and right). If it is impossible to rot every orange
    // then simply return -1.

    // Input: arr[][C] = { {2, 1, 0, 2, 1},
    // {0, 0, 1, 2, 1},
    // {1, 0, 0, 2, 1}};
    // Output:
    // All oranges cannot be rotten.
    // Explanation:
    // At 0th time frame:
    // {2, 1, 0, 2, 1}
    // {0, 0, 1, 2, 1}
    // {1, 0, 0, 2, 1}
    // At 1st time frame:
    // {2, 2, 0, 2, 2}
    // {0, 0, 2, 2, 2}
    // {1, 0, 0, 2, 2}
    // At 2nd time frame:
    // {2, 2, 0, 2, 2}
    // {0, 0, 2, 2, 2}
    // {1, 0, 0, 2, 2}
    // ...
    // The 1 at the bottom left corner of the matrix is never rotten.

    // public final static int R = 3;
    // public final static int C = 5;

    // static class Ele {
    // int x = 0;
    // int y = 0;

    // Ele(int x, int y) {
    // this.x = x;
    // this.y = y;
    // }
    // }

    // static boolean isValid(int i, int j) {
    // return (i >= 0 && j >= 0 && i < R && j < C);
    // }

    // static boolean isDelim(Ele temp) {
    // return (temp.x == -1 && temp.y == -1);
    // }

    // static boolean checkAll(int arr[][]) {
    // for (int i = 0; i < R; i++) {
    // for (int j = 0; j < C; j++) {
    // if (arr[i][j] == 1) {
    // return true;
    // }
    // }
    // }
    // return false;
    // }

    // static int Solution(int arr[][]) {
    // Queue<Ele> Q = new LinkedList<>();
    // Ele temp;
    // int ans = 0;
    // for (int i = 0; i < R; i++) {
    // for (int j = 0; j < C; j++) {
    // if (arr[i][j] == 2) {
    // Q.add(new Ele(i, j));
    // }
    // }
    // }
    // Q.add(new Ele(-1, -1));

    // while(!Q.isEmpty()){
    // boolean flag = false;
    // while(!isDelim(Q.peek())){
    // temp = Q.peek();
    // if(isValid(temp.x+1, temp.y) && arr[temp.x+1][temp.y] == 1){
    // if(!flag){
    // ans++;
    // flag = true;
    // }
    // }
    // }
    // }
    // }

    // Question 4 :
    // Find the size of the largest region in the Boolean Matrix
    // We have a matrix where each cell contains either a ‘0’ or a ‘1’, and any cell
    // containing a 1 is
    // called a filled cell. Two cells are said to be connected if they are adjacent
    // to each other
    // horizontally, vertically, or diagonally. If one or more filled cells are also
    // connected, they form a
    // region. find the size of the largest region.
    // Input: M[][5] = { {0, 0, 1, 1, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 0, 0}, {0, 0,
    // 0, 0 1} }
    // Output: 4

    static int RoW, COL, count;

    static boolean isSafe(int M[][], int row, int col, boolean visited[][]) {
        return ((row >= 0) && (row < RoW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !visited[row][col]));
    }

    static void DFS(int M[][], int row, int col, boolean[][] visited) {
        int[] rowNbr = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] colNbr = { -1, 0, 1, -1, 1, -1, 0, 1 };

        visited[row][col] = true;
        for (int k = 0; k < 8; k++) {
            if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited)) {
                count++;
                DFS(M, row + rowNbr[k], col + colNbr[k], visited);
            }
        }
    }

    static int largestRegion(int[][] M) {
        boolean[][] visited = new boolean[RoW][COL];
        int result = 0;
        for (int i = 0; i < RoW; i++) {
            for (int j = 0; j < COL; j++) {
                if (M[i][j] == 1 && !visited[i][j]) {
                    count = 1;
                    DFS(M, i, j, visited);
                    result = Math.max(result, count);
                }
            }
        }
        return result;
    }

    // Question 5 :
    // Word Ladder
    // We have a dictionary, and two words ‘start’ and ‘target’ (both of same
    // length). Find length of
    // the smallest chain from ‘start’ to ‘target’ if it exists, such that adjacent
    // words in the chain only
    // differ by one character and each word in the chain is a valid word i.e., it
    // exists in the dictionary.
    // It may be assumed that the ‘target’ word exists in dictionary and length of
    // all dictionary words
    // // is same.

    // Input:
    // Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN},
    // start = TOON, target = PLEA
    // Output: 7
    // Explanation: TOON – POON – POIN – POIE – PLIE – PLEE– PLEA

    static int shortestChainLen(String start, String target, Set<String> D) {
        if (start == target) {
            return 0;
        }

        if (!D.contains(target)) {
            return 0;
        }

        int level = 0, wordlength = start.length();
        Queue<String> Q = new LinkedList<>();
        Q.add(start);
        while (!Q.isEmpty()) {
            ++level;
            int sizefQ = Q.size();
            for (int i = 0; i < sizefQ; i++) {
                char[] word = Q.peek().toCharArray();
                Q.remove();
                for (int pos = 0; pos < wordlength; ++pos) {
                    char orig_char = word[pos];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        word[pos] = c;
                        if (String.valueOf(word).equals(target)) {
                            return level + 1;
                        }
                        if (!D.contains(String.valueOf(word))) {
                            continue;
                        }
                        D.remove(String.valueOf(word));
                        Q.add(String.valueOf(word));
                    }

                    word[pos] = orig_char;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        // Node root = newNode(1);
        // root.left = newNode(2);
        // root.right = newNode(3);
        // root.left.left = newNode(4);
        // root.left.right = newNode(5);
        // System.out.println(minDepth(root));

        int M[][] = { { 0, 0, 1, 1, 0 },
                { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 1 } };
        RoW = 4;
        COL = 5;
        System.out.println(largestRegion(M));

        Set<String> D = new HashSet<String>();
        D.add("poon");
        D.add("plee");
        D.add("same");
        D.add("poie");
        D.add("plie");
        D.add("poin");
        D.add("plea");
        String start = "toon";
        String target = "plea";
        System.out.print("Length of shortest chain is: " + shortestChainLen(start, target, D));

    }
}
