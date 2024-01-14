import java.util.*;
import java.io.*;
import java.sql.Time;

public class Phodo {

    // Range Sum of BST
    // We have a Binary Search Tree consisting of N nodes and two positive integers
    // L and R, the
    // task is to find the sum of values of all the nodes that lie in the range [L,
    // R]..
    // Sample Input1::
    // 8
    // / \
    // 5 11
    // / \
    // \
    // 3
    // 6 20
    // Sample Output1 : 11

    // static class Node {// TC O(n) SC O(n)

    // int val;
    // Node left, right;
    // }

    // static Node newNode(int item) {
    // Node temp = new Node();
    // temp.val = item;
    // temp.left = temp.right = null;
    // return temp;
    // }

    // static int sum = 0;

    // static int rangeSumBST(Node root, int low, int high) {
    // if (root == null) {
    // return 0;
    // }

    // Queue<Node> q = new LinkedList<>();
    // q.add(root);

    // while (q.isEmpty() == false) {
    // Node curr = q.peek();
    // q.remove();
    // if (curr.val >= low && curr.val <= high) {
    // sum += curr.val;

    // }

    // if (curr.left != null && curr.val > low) {
    // q.add(curr.left);
    // }

    // if (curr.right != null && curr.val < high) {
    // q.add(curr.right);
    // }

    // }
    // return sum;
    // }

    // static Node insert(Node node, int data) {
    // if (node == null) {
    // return newNode(data);
    // }

    // if (data <= node.val) {
    // node.left = insert(node.left, data);
    // } else {
    // node.right = insert(node.right, data);
    // }

    // return node;
    // }

    // Question 2 :
    // Find the closest element in Binary Search Tree
    // We have a binary search tree and a target node K. The task is to find the
    // node with minimum
    // absolute difference with given target value K.
    // 8
    // / \
    // 5 11
    // / \
    // \
    // 3
    // 6 20
    // Sample Input 1 : 5
    // Sample Output 1 : 5 (difference is 0)
    // Sample Input 2 : .19
    // Sample Output 2 : 20 (difference is 1)

    // static int minimum_difference, minimum_diff_key;// TC O(h) TC O(1)

    // static class Node {
    // int key;
    // Node left, right;
    // }

    // static Node newNode(int key) {
    // Node node = new Node();
    // node.key = key;
    // node.left = node.right = null;
    // return (node);

    // }

    // static void maxDiffUtil(Node ptr, int k) {
    // if (ptr == null) {
    // return;
    // }

    // if (ptr.key == k) {
    // minimum_diff_key = k;
    // return;
    // }

    // if (minimum_difference > Math.abs(ptr.key)) {
    // minimum_difference = Math.abs(ptr.key - k);
    // minimum_diff_key = ptr.key;
    // }

    // if (k < ptr.key) {
    // maxDiffUtil(ptr.left, k);
    // } else {
    // maxDiffUtil(ptr.right, k);
    // }
    // }

    // static int maxDiff(Node root, int key) {
    // minimum_difference = 999999999;
    // minimum_diff_key = -1;
    // maxDiffUtil(root, key);
    // return minimum_diff_key;
    // }

    // Question 3 :
    // Find k-th smallest element in BST
    // We have the root of a binary search tree and K as input, find Kth smallest
    // element in BST.
    // 8
    // 5 11
    // / \
    // \
    // 3
    // 6 20
    // Sample Input 1 : k=3
    // Sample Output 1 : 8
    // Sample Input 2 : k=5
    // Sample Output 2 : 5

    class Node { // Tc O(n) SC O(h)
        int data;
        Node left, right;

        Node(int x) {
            data = x;
            left = right = null;
        }
    }

    static int count = 0;

    public static Node insert(Node root, int x) {
        if (root == null) {
            return new Node(x);
        }

        if (x < root.data) {
            root.left = insert(root.left, x);
        } else if (x > root.data) {
            root.right = insert(root.right, x);
        }

        return root;
    }

    public static Node KthSmallest(Node root, int k) {
        if (root == null) {
            return null;
        }

        Node left = KthSmallest(root.left, k);
        if (left != null) {
            return left;
        }
        count++;
        if (count == k) {
            return root;
        }

        return KthSmallest(root.right, k);
    }

    public static void printKthSmallest(Node root, int k) {
        Node res = KthSmallest(root, k);
        if (res == null) {
            System.out.println("There are less than k nodes in the BST");
        } else {
            System.out.println("K-th Smallest Element is " + res.data);
        }
    }

    // Question 4 :
    // Two Sum BSTs
    // Given a BST, transform it into a greater sum tree where each node contains
    // sum of all nodes
    // greater than that node.
    // Sample Input 1 :
    // 5
    // / \
    // 3 7
    // / \ / \
    // 2 4 6 8
    // 10
    // / \
    // 6 15
    // / \ / \
    // 3 8 11 18
    // x = 16
    // Sample Output 1 : 3
    // The pairs are:
    // (5, 11), (6, 10) and (8, 8)

    // Time Complexity : o(n1+n2)
    // Space Complexity: o(h1+h2)

    static Node root1;
    static Node root2;

    static int countPairs(Node root1, Node root2, int x) {
        if (root1 == null && root2 == null) {
            return 0;
        }

        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        Node top1, top2;

        int count = 0;
        while (true) {
            while (root1 != null) {
                st1.push(root1);
                root1 = root1.left;
            }

            while (root2 != null) {
                st2.push(root2);
                root2 = root2.right;
            }
            if (st1.isEmpty() || st2.isEmpty()) {
                break;
            }

            top1 = st1.peek();
            top2 = st2.peek();

            if ((top1.data + top2.data) == x) {
                count++;
                st1.pop();
                st2.pop();
                root1 = top1.right;
                root2 = top2.left;
            } else if ((top1.data + top2.data) < x) {
                st1.pop();
                root1 = top1.right;
            } else {
                st2.pop();
                root2 = top2.left;
            }
        }

        return count;
    }

    // Maximum Sum BST in Binary Tree
    // We have a binary tree, the task is to print the maximum sum of nodes of a
    // sub-tree which is
    // also a Binary Search Tree.

    // Sample Input 1 :
    // 5
    // / \
    // 9 2
    // /
    // \
    // 6
    // / \
    // 8 7
    // 3
    // Sample Output 1 : 8

    static class info {
        int max;
        int min;
        boolean isBST;
        int sum;
        int currmax;

        info(int m, int mi, boolean BST, int su, int curr) {
            max = m;
            min = mi;
            isBST = BST;
            sum = su;
            currmax = curr;

        }

        info() {
        }
    }

    static class INT {
        int a;
    }

    static info MaxSumBstUtil(Node root, INT maxsum) {
        if (root == null) {
            return new info(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0, 0);
        }

        if (root.left == null && root.right == null) {
            maxsum.a = Math.max(maxsum.a, root.data);
            return new info(root.data, root.data, true, root.data, maxsum.a);
        }

        info L = MaxSumBstUtil(root.left, maxsum);
        info R = MaxSumBstUtil(root.right, maxsum);

        info BST = new info();
        if (L.isBST && R.isBST && L.max < root.data && R.min > root.data) {
            BST.max = Math.max(root.data, Math.max(L.max, R.max));
            BST.min = Math.max(root.data, Math.max(L.min, R.min));

            maxsum.a = Math.max(maxsum.a, R.sum + root.data + L.sum);
            BST.sum = R.sum + root.data + L.sum;
            BST.currmax = maxsum.a;
            BST.isBST = true;
            return BST;
        }

        BST.isBST = false;
        BST.currmax = maxsum.a;
        BST.sum = R.sum + root.data + L.sum;
        return BST;
    }

    static int MaxSumBSt(Node root) {
        INT maxsum = new INT();
        maxsum.a = Integer.MIN_VALUE;
        return MaxSumBstUtil(root, maxsum).currmax;
    }

    public static void main(String args[]) {
        // Node root = null;
        // root = insert(root, 10);
        // insert(root, 5);
        // insert(root, 15);
        // insert(root, 3);
        // insert(root, 7);
        // insert(root, 18);
        // int L = 7, R = 15;
        // System.out.print(rangeSumBST(root, L, R));

        // Node root = newNode(9);
        // root.left = newNode(4);
        // root.right = newNode(17);
        // root.left.left = newNode(3);
        // root.left.right = newNode(6);
        // root.left.right.left = newNode(5);
        // root.left.right.right = newNode(7);
        // root.right.right = newNode(22);
        // root.right.right.left = newNode(20);
        // int k = 18;
        // System.out.println(maxDiff(root, k));

        // Node root = null;
        // int keys[] = { 20, 8, 22, 4, 12, 10, 14 };
        // for (int x : keys)
        // root = insert(root, x);
        // int k = 3;
        // printKthSmallest(root, k);

        // root1 = new Node(5);
        // root1.left = new Node(3);
        // root1.right = new Node(7);
        // root1.left.left = new Node(2);
        // root1.left.right = new Node(4);
        // root1.right.left = new Node(6);
        // root1.right.right = new Node(8);
        // root2 = new Node(10);
        // root2.left = new Node(6);
        // root2.right = new Node(15);
        // root2.left.left = new Node(3);
        // root2.left.right = new Node(8);
        // root2.right.left = new Node(11);
        // root2.right.right = new Node(18);
        // int x = 16;
        // System.out.println("Pairs = " + countPairs(root1, root2, x));

        Node root = new Node(5);
        root.left = new Node(14);
        root.right = new Node(3);
        root.left.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(9);
        root.left.left.right = new Node(1);
        System.out.println(MaxSumBSt(root));

    }
}