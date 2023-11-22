import java.util.*;

public class BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (root.data < val) {
            // insert in left
            root.left = insert(root.left, val);
        } else {
            // insert in right
            root.right = insert(root.right, val);
        }

        return root;
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    /* serch in bst TC O(h)h = height of tree */
    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        if (root.data > key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }

    }

    /* delete a node 3 case 1st No child)leaf node 2nd one child 3rd two children */
    public static Node Delete(Node root, int val) {
        if (root.data < val) {
            root.right = Delete(root.right, val);
        } else if (root.data > val) {
            root.left = Delete(root.left, val);
        } else {
            // val == root
            // case 1 - leaf Node
            if (root.left == null && root.right == null) {
                return null;
            }

            // case 2 - single child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // case 3 both childern
            Node IS = findInorderSuccessor(root.right);
            root.data = IS.data;
            root.right = Delete(root.right, IS.data);
        }

        return root;
    }

    public static Node findInorderSuccessor(Node root) {// findInorderSuccessor is left most node in right sub tree
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    /* print in range k1 = 5 & k2 = 12 */
    public static void printInRange(Node root, int k1, int k2) {
        if (root == null) {
            return;
        }

        if (root.data >= k1 && root.data <= k2) {
            printInRange(root.left, k1, k2);
            System.out.print(root.data);
            printInRange(root.right, k1, k2);
        } else if (root.data < k1) {
            printInRange(root.left, k1, k2);
        } else {
            printInRange(root.right, k1, k2);
        }
    }

    /* root to leaf paths */
    public static void printRoot2Leaf(Node root, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.data);
        if (root.left == null && root.right == null) {
            printPath(path);
        }

        printRoot2Leaf(root.left, path);
        printRoot2Leaf(root.right, path);
        path.remove(path.size() - 1);

    }

    public static void printPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + "-> ");

        }
        System.out.println("Null");
    }

    /* validate BST asking company amazon meta others important */
    public static boolean isvalid(Node root, Node min, Node max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.data <= min.data) {
            return false;
        }

        if (max != null && root.data >= max.data) {
            return false;
        }

        return isvalid(root.left, min, root) && isvalid(root.right, root, max);
    }

    /* mirror a bst TC O(n) */
    static class Bst {
        int data;
        Node left;
        Node right;

        public Bst(int data) {
            this.data = data;
            this.left = this.right = null;

        }
    }

    public static Node createMirror(Node root) {
        if (root == null) {
            return null;
        }

        Node leftMirror = createMirror(root.left);
        Node rightMirror = createMirror(root.right);

        root.left = rightMirror;
        root.right = leftMirror;
        return root;
    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    /* sortd array to balanced bst */

    public static void main(String args[]) {
        // int value[] = { 8, 5, 3, 1, 7, 6, 10, 11, 14 };
        // Node root = null;

        /*
         * for (int i = 0; i < value.length; i++) {
         * root = insert(root, value[i]);
         * }
         * 
         * inorder(root);
         * System.out.println();
         */

        /*
         * if (search(root, 1)) {
         * System.out.println("found");
         * } else {
         * System.out.println("not found");
         * }
         */

        /*
         * root = Delete(root, 1);
         * System.out.println();
         * 
         * inorder(root);
         */
        // printRoot2Leaf(root,ArrayList<>());

        /*
         * if (isvalid(root, null, null)) {
         * System.out.println(" is valid");
         * } else {
         * System.out.println(" is not valid");
         * }
         */

        /*
         * Node root = new Node(8);
         * root.left = new Node(2);
         * root.right = new Node(3);
         * root.left.left = new Node(9);
         * root.left.right = new Node(10);
         * root.right.right = new Node(11);
         * 
         * root = createMirror(root);
         * preorder(root);
         */

    }
}
