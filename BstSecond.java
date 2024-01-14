import java.util.*;

public class BstSecond {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    /* sorted array in balanced bst */
    public static Node createBst(int arr[], int st, int end) {
        if (st > end) {
            return null;
        }

        int mid = (st + end) / 2;
        Node root = new Node(arr[mid]);
        root.left = createBst(arr, st, mid - 1);
        root.right = createBst(arr, mid + 1, end);
        return root;
    }

    /*
     * public static void preorder(Node root) {
     * if (root == null) {
     * return;
     * }
     * 
     * System.out.print(root.data);
     * preorder(root.left);
     * preorder(root.right);
     * }
     */
    /* convert bst to balanced bst */

    /*
     * public static void getInorder(Node root, ArrayList<Integer> inorder) {
     * if (root == null) {
     * return;
     * }
     * 
     * getInorder(root.left, inorder);
     * inorder.add(root.data);
     * getInorder(root.right, inorder);
     * }
     */

    /*
     * public static Node createBST(ArrayList<Integer> inorder, int st, int end) {
     * if (st > end) {
     * return null;
     * }
     * 
     * int mid = (st + end) / 2;
     * Node root = new Node(inorder.get(mid));
     * root.left = createBST(inorder, st, mid - 1);
     * root.right = createBST(inorder, mid + 1, end);
     * return root;
     * 
     * }
     */
    public static Node balanceBSt(Node root) {

        // step 1 inorder seq
        ArrayList<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);

        // step 2 inorder seq --> balance bst
        root = createBST(inorder, 0, inorder.size() - 1);
    }

    /* size of largest bst in BT */
    static class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        public Info(Boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxBST = 0;

    public static Info largestBST(Node root) {
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info leftInfo = largestBST(root.left);
        Info rightInfo = largestBST(root.right);
        int size = leftInfo.size + rightInfo.size + 1;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

        // valid bst condition

        if (root.data <= leftInfo.max || root.data >= rightInfo.min) {
            return new Info(false, size, min, max);
        }

        if (leftInfo.isBST && rightInfo.isBST) {
            maxBST = Math.max(maxBST, size);
            return new Info(true, size, min, max);
        }

        return new Info(false, size, min, max);
    }

    /* merge 2 bst */
    public static void getInorder(Node root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        getInorder(root.left, arr);
        arr.add(root.data);
        getInorder(root.right, arr);
    }

    public static Node createBST(ArrayList<Integer> arr, int st, int end) {
        if (st > end) {
            return null;
        }

        int mid = (st + end) / 2;
        Node root = new Node(arr.get(mid));
        root.left = createBST(arr, st, mid - 1);
        root.right = createBST(arr, mid + 1, end);
        return root;

    }

    public static Node mergeBST(Node root1, Node root2) {
        // step 1 sorted 1st bst
        ArrayList<Integer> arr1 = new ArrayList<>();
        getInorder(root1, arr1);

        // step 2 sorted 2nd bst
        ArrayList<Integer> arr2 = new ArrayList<>();
        getInorder(root2, arr2);

        // merge arr1 + arr2
        int i = 0, j = 0;
        ArrayList<Integer> final_arr = new ArrayList<>();
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                final_arr.add(arr1.get(i));
                i++;
            } else {
                final_arr.add(arr2.get(j));
                j++;
            }
        }

        while (i < arr1.size()) {
            final_arr.add(arr1.get(i));
            i++;
        }

        while (j < arr2.size()) {
            final_arr.add(arr2.get(j));
            j++;
        }

        // step 4 sorted arr list se balanced bst

        return createBst(final_arr, 0, final_arr.size() - 1);

    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String args[]) {
        /*
         * int arr[] = { 3, 5, 6, 8, 10, 11, 12 };
         * Node root = createBst(arr, 0, arr.length - 1);
         * preorder(root);
         */
        /*
         * Node root = new Node(8);
         * root.left = new Node(6);
         * root.left.left = new Node(5);
         * root.left.left.left = new Node(3);
         * 
         * root.right = new Node(10);
         * root.right.right = new Node(11);
         * root.right.right.right = new Node(12);
         */

        // root = balanceBSt(root);
        // preorder(root);
        // Info info = largestBST(root);
        // System.out.println("latgest Bst size = " + maxBST);

        // bst 1
        Node root1 = new Node(2);
        root1.left = new Node(1);
        root1.right = new Node(4);

        // bst 2

        Node root2 = new Node(9);
        root2.left = new Node(3);
        root2.right = new Node(12);

        Node root = mergeBST(root1, root2);
        preorder(root);

    }
}
