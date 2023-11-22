import java.util.*;

public class SagmentTree {

    // contructing sgment tree O(n) creation

    static int tree[];

    public static void initilize(int n) {
        tree = new int[4 * n];
    }

    // buil tree maximum in creation
    public static void builTree(int i, int si, int sj, int arr[]) {// O(n)
        // base case
        if (si == sj) {
            tree[i] = arr[si];
            return;
        }

        int mid = (si + sj) / 2;
        builTree(2 * i + 1, si, mid, arr);
        builTree(2 * i + 2, mid + 1, sj, arr);

        tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);
    }

    // minimum
    public static void builTreeMin(int i, int si, int sj, int arr[]) {// O(n)
        // base case
        if (si == sj) {
            tree[i] = arr[si];
            return;
        }

        int mid = (si + sj) / 2;
        builTree(2 * i + 1, si, mid, arr);
        builTree(2 * i + 2, mid + 1, sj, arr);

        tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
    }

    public static int getMin(int arr[], int qi, int qj) {
        int n = arr.length;
        return getMinUtll(0, 0, n - 1, qi, qj);
    }

    public static int getMinUtll(int i, int si, int sj, int qi, int qj) {
        if (si > qj && aj < qi) {
            // no overlap
            return Integer.MAX_VALUE;
        } else if (si >= qi || sj <= qj) {
            return tree[i];// complete overlap
        } else {
            // partial overlap
            int mid = (si + sj) / 2;
            int leftAns = getMinUtll(2 * i + 1, mid, sj, qi, qj);
            int rightAns = getMinUtll(2 * i + 2, mid + 1, sj, qi, qj);
            return Math.min(leftAns, rightAns);
        }
    }

    // maximum element query selec
    public static int getMax(int arr[], int qi, int qj) {
        int n = arr.length;
        return getMaxUtll(0, 0, n - 1, qi, qj);
    }

    public static int getMaxUtll(int i, int si, int sj, int qi, int qj) {
        if (si > qj && aj < qi) {
            // no overlap
            return Integer.MIN_VALUE;
        } else if (si >= qi || sj <= qj) {
            return tree[i];// complete overlap
        } else {
            // partial overlap
            int mid = (si + sj) / 2;
            int leftAns = getMaxUtll(2 * i + 1, mid, sj, qi, qj);
            int rightAns = getMaxUtll(2 * i + 2, mid + 1, sj, qi, qj);
            return Math.max(leftAns, rightAns);
        }
    }

    // min
    public static void updateQueryMax(int arr[], int idx, int newVal) {
        arr[idx] = newVal;// new value update
        int n = arr.length;
        updateQueryMaxUtill(0, 0, n - 1, idx, newVal);
    }

    public static void updateQueryMaxUtill(int i, int si, int sj, int idx, int newVal) {
        if (si > idx || idx > sj) {
            return;
        }

        tree[i] = Math.max(tree[i], newVal);
        if (si != sj) {
            // no leaf
            int mid = (si + sj) / 2;
            updateQueryMaxUtill(2 * i + 1, si, mid, idx, newVal);// left
            updateQueryMaxUtill(2 * i + 2, mid + 1, sj, idx, newVal);// right
        }
    }

    public static void updateQueryMin(int arr[], int idx, int newVal) {
        arr[idx] = newVal;// new value update
        int n = arr.length;
        updateQueryUtillMin(0, 0, n - 1, idx, newVal);
    }

    public static void updateQueryUtillMin(int i, int si, int sj, int idx, int newVal) {
        if (si > idx || idx > sj) {
            return;
        }

        if (si == sj) {
            tree[i] = newVal;
        }

        if (si != sj) {
            // no leaf
            tree[i] = Math.min(tree[i], newVal);
            int mid = (si + sj) / 2;
            updateQueryUtillMin(2 * i + 1, si, mid, idx, newVal);// left
            updateQueryUtillMin(2 * i + 2, mid + 1, sj, idx, newVal);// right
        }
    }

    public static int buildBST(int arr[], int i, int start, int end) {
        // base case
        if (start == end) {
            tree[i] = arr[start];
            return arr[start];
        }

        // find mid
        int mid = (start + end) / 2;

        buildBST(arr, 2 * i + 1, start, mid);// left sunbtree - 2*i+1
        buildBST(arr, 2 * i + 2, mid + 1, end);// right subtree - 2*i+2
        tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
        return tree[i];

    }

    // query st O(logn) n time ke lie O(nlogn)
    public static int getSumUtill(int i, int si, int sj, int qi, int qj) {
        // case 1
        if (qj <= si || qi >= sj) {// non overlaping
            return 0;
        } // case 2
        else if (si >= qi && sj <= qj) {// complete overlap
            return tree[i];
        } // case 3
        else {// partial overlap
            int mid = (si + sj) / 2;
            int left = getSumUtill(2 * i + 1, si, mid, qi, qj);
            int right = getSumUtill(2 * i + 2, mid + 1, sj, qi, qj);
            return left + right;
        }
    }

    public static int getSum(int arr[], int si, int sj) {
        int n = arr.length;
        return getSumUtill(0, 0, n - 1, qi, qj);
    }

    /* update in sagtgment trees update at an index */// O(logn)
    public static void updateUtill(int i, int si, int sj, int idx, int diff) {
        // overlaping condition
        if (idx > sj || idx < si) {
            return;
        }

        tree[i] += diff;
        if (si != sj) {
            // non leaf
            int mid = (si + sj) / 2;
            updateUtill(2 * i + 1, si, mid, idx, diff);// left
            updateUtill(2 * i + 2, mid + 1, sj, idx, diff);// right
        }
    }

    public static void update(int arr[], int idx, int newVal) {
        // update in array O(constant)
        int n = arr.length;
        int diff = newVal - arr[idx];// newvalue - oldvalue
        arr[idx] = newVal;

        updateUtill(0, 0, n - 1, idx, diff);
    }

    public static void main(String args[]) {
        // int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };
        int n = arr.length;
        initilize(n);
        buildBST(arr, 0, 0, n - 1);

        // for(int i=0; i<tree.length;i++){
        // System.out.print(tree[i]+"");
        // }

        System.out.println(getSum(arr, 2, 5));

        update(arr, 2, 2);
        System.out.println(getSum(arr, 2, 5));
        builTree(0, 0, n - 1, arr);
        // for (int i = 0; i < tree.length; i++) {
        // System.out.print(tree[i] + " ");
        // }

        int max = getMax(arr, 2, 5);// 2 to index check value maximum
        System.out.println(max);//

        update(arr, 2, 20);

        max = update(arr, 2, 20);
        System.out.println(max);// update val 20

        int min = getMin(arr, 2, 5);
        System.out.println(min);

        updateQueryMin(arr, 2, 5);

        min = updateQueryMin(arr, 2, 5);
        System.out.println(min);
    }
}
