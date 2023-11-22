import java.util.*;

public class BinaryTree {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class binarytree {
        static int idx = -1;

        public static Node Buildtree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = Buildtree(nodes); // add kr dega
            newNode.right = Buildtree(nodes);

            return newNode;
        }

        // order tree Traversales
        public static void preorder(Node root) {// O(n)
            if (root == null) {
                return;
                // System.out.println("-1");
            }

            System.out.print(root.data + " ");// root
            preorder(root.left);// left
            preorder(root.right);// right
        }

        // inorder traversal
        public static void inordertrav(Node root) {// tc O(n)
            if (root == null) {
                return;
            }

            inordertrav(root.left);// left
            System.out.print(root.data + " ");// root
            inordertrav(root.right);// right
        }

        // poost order

        public static void postorder(Node root) {
            if (root == null) {
                return;
            }

            postorder(root.left);
            postorder(root.left);
            System.out.print(root.data + " ");
        }

        // level order traversal it is bfs technique brifth defth first
        public static void levelorder(Node root) { // Tc o(2n) important
            if (root == null) {
                return;
            }

            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while (!q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }

    }

    /*
     * static class Node {
     * int data;
     * Node left, right;
     * 
     * public Node(int data) {
     * this.data = data;
     * this.left = null;
     * this.right = null;
     * }
     * }
     */

    // height of tree
    public static int height(Node root) { // O(n)
        if (root == null) {
            return 0;
        }

        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh, rh) + 1;// +1 is liye kyo ki self ko bhi add

    }

    // count node
    public static int count(Node root) {// tc O(n)
        if (root == null) {
            return 0;
        }

        int leftcount = count(root.left);
        int rcount = count(root.right);
        return leftcount + rcount + 1;
    }

    // sum of node
    public static int sum(Node root) { // TC O(n)
        if (root == null) {
            return 0;
        }

        int leftsum = sum(root.left);
        int rightsum = sum(root.right);
        int treesum = leftsum + rightsum + root.data;
        return treesum;
    }

    /*
     * public static int diameter(Node root) {// O(n^2)=> height O(n) + all node
     * O(n) approach 1
     * if (root == null) {
     * return 0;
     * }
     * 
     * int leftdiam = diameter(root.left);
     * int leftheight = height(root.left);
     * int rightdiam = diameter(root.right);
     * int rightheight = height(root.right);
     * 
     * int selfroot = leftheight + rightheight + 1;
     * return Math.max(selfroot, Math.max(leftdiam, rightdiam));
     * }
     */

    static class Information {
        int height;
        int diam;

        public Information(int diam, int height) {
            this.height = height;
            this.diam = diam;
        }
    }

    public static Information Diameter(Node root) { // TC O(n)
        if (root == null) {
            return new Information(0, 0);
        }

        Information leftInfo = Diameter(root.left);
        Information rightInfo = Diameter(root.right);

        int diam = Math.max(Math.max(leftInfo.diam, rightInfo.diam), leftInfo.height + rightInfo.height + 1);
        int height = Math.max(leftInfo.height, rightInfo.height);

        return new Information(diam, height);
    }

    /*
     * subtree of anothe tree
     * 1 fund subroot in tree
     * 2 check identical (subtree,node subtree)
     */

    public static boolean isIdentical(Node node, Node subRoot) {
        if (node == null && subRoot == null) {
            return true;
        } else if (node == null || subRoot == null || node.data != subRoot.data) {
            return false;
        }

        if (!isIdentical(node.left, subRoot.left)) {
            return false;
        }
        if (!isIdentical(node.right, subRoot.right)) {
            return false;
        }

        return true;
    }

    public static boolean isSubtree(Node root, Node subRoot) {

        if (root == null) {
            return false;
        }
        if (root.data == subRoot.data) {// check self
            if (isIdentical(root, subRoot)) {
                return true;
            }
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

    }
    /* top view of tree */

    static class Info {
        Node node;
        int hd;// horizontal distance

        public Info(Node node, int hd) {
            this.node = node;
            this.hd = hd;

        }
    }

    public static void topView(Node root) {
        // level order

        Queue<Info> Q = new LinkedList();
        HashMap<Integer, Node> map = new HashMap<>();

        int min = 0, max = 0;
        Q.add(new Info(root, 0));
        Q.add(null);

        while (!Q.isEmpty()) {
            Info curr = Q.remove();
            if (curr == null) {
                if (Q.isEmpty()) {
                    break;
                } else {
                    Q.add(null);
                }

            } else {

                if (!map.containsKey(curr.hd)) {// first time my hd is occurring
                    map.put(curr.hd, curr.node);
                }

                if (curr.node.left != null) {
                    Q.add(new Info(curr.node.left, curr.hd - 1));
                    max = Math.max(max, curr.hd - 1);
                }

                if (curr.node.right != null) {
                    Q.add(new Info(curr.node.right, curr.hd + 1));
                    max = Math.max(max, curr.hd + 1);
                }
            }

        }

        for (int i = min; i < max; i++) {
            System.out.print(map.get(i).data);
        }
        System.out.println();

    }

    /* K th levelof a tree */
    public static void KLevel(Node root, int level, int k) {
        if (root == null) {
            return;
        }

        if (level == k) {
            System.out.println(root.data + " ");
            return;
        }

        KLevel(root.left, level + 1, k);
        KLevel(root.right, level + 1, k);
    }

    /* Lowest common ancestor */
    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if (root == null) {
            return false;
        }
        path.add(root);

        if (root.data == n) {
            return true;
        }

        boolean foundleft = getPath(root.left, n, path);
        boolean foundright = getPath(root.right, n, path);

        if (foundleft || foundright) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static Node lca(Node root, int n1, int n2) {// TC O(n) SC O(n)

        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getPath(root, n1, path1);
        getPath(root, n2, path2);

        // calculate lca
        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }

        // last equal node --> 1th node
        Node lca = path1.get(i - 1);
        return lca;

    }

    /* Lowest common ancestor approach 2 */
    public static Node lca2(Node root, int n1, int n2) {

        if (root == null) {
            return null;
        }

        if (root == null || root.data == n1 || root.data == n2) {
            return root;
        }

        Node leftLca = lca2(root.left, n1, n2);
        Node rightLca = lca2(root.right, n1, n2);

        // leftca = val rightca = null
        if (rightLca == null) {
            return leftLca;
        }
        if (leftLca == null) {
            return rightLca;
        }

        return root;
    }

    /* minimum distance between two nodesTC O(n) */
    public static int lcaDist(Node root, int n) {
        if (root == null) {
            return -1;
        }

        if (root.data == n) {
            return 0;
        }

        int leftDist = lcaDist(root.left, n);
        int rightdist = lcaDist(root.right, n);

        if (leftDist == -1 && rightdist == -1) {
            return -1;
        } else if (leftDist == -1) {
            return rightdist + 1;
        } else {
            return leftDist + 1;
        }
    }

    public static int Mdbe(Node root,int n1,int n2){
        Node lca = lca2(root, n1, n2);
        int dist1 = lcaDist(lca,n1);
        int dist2 = lcaDist(lca,n2)
    }

    /* Kth Ancestor of node 1 _> find node 2 if (root.dta == node){ return 0} */
    public static int Kthancestar(Node root, int n, int k) {

        if (root == null) {
            return 0;
        }
        if (root.data == n) {
            return 0;
        }

        int leftdist = Kthancestar(root.left, n, k);
        int rightdist = Kthancestar(root.right, n, k);

        if (leftdist == -1 && rightdist == -1) {
            return -1;
        }

        int max = Math.max(leftdist, rightdist);
        if (max + 1 == k) {
            System.out.println(root.data);
        }

        return max + 1;
    }

    /* Transform to sum tree */
    public static int Tranformsumtree(Node root) {
        if (root == null) {
            return 0;
        }

        int leftchild = Tranformsumtree(root.left);
        int rightchid = Tranformsumtree(root.right);

        int data = root.data;

        int newLeft = root.left == null ? 0 : root.left.data;
        int newRight = root.right == null ? 0 : root.right.data;

        root.data = newLeft + leftchild + newRight + rightchid;
        return data;
    }

    public static void Preorder(Node root) {// O(n)
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");// root
        Preorder(root.left);// left
        Preorder(root.right);// right
    }

    public static void main(String args[]) {
        // int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1 };// TC O(n)
        // BinaryTree tree = new BinaryTree();
        // Node root = tree.Buildtree(nodes);
        // System.out.println(root.data);

        // tree.preorder(root);
        // tree.inordertrav(root);
        // tree.levelorder(root);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // sub tree

        Node subRoot = new Node(2);
        subRoot.left = new Node(4);
        subRoot.right = new Node(5);

        /*
         * System.out.println(root);
         * System.out.println(Diameter(root).diam);
         * System.out.println(Diameter(root).height);
         */
        // System.out.println(isSubtree(root, subRoot));
        // topView(root);
        // int k = 2;
        // int n1 = 4, n2 = 7;
        // System.out.println(lca(root, n1, n2).data);
        // System.out.println(lca2(root, n1, n2).data);
        // int n1 = 4, n2 = 7;
        // System.out.println(Mdbe(subRoot, n1, n2));
        // int n = 5, k = 1;
        // System.out.println(Kthancestar(root, n, k));
        Tranformsumtree(root);
        Preorder(root);

    }
}
// height tree edge ki termm me node-1