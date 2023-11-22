import java.util.*;

public class Heap {
    // insert in heap usig array
    // step ==> add at last index
    // fix heap parerent = x-1/2

    static class HeapA {
        ArrayList<Integer> arr = new ArrayList<>();

        public void add(int data) {
            // add at las idx

            int x = arr.size() - 1; // x is child indx O(1)
            int parent = (x - 1) / 2; // parent indx O(1)

            while (arr.get(x) < arr.get(parent)) { // O(logn) max heap => < to > n level node me to logn

                // swap
                int temp = arr.get(x);
                arr.set(x, arr.get(parent));
                arr.set(parent, temp);

                x = parent;// update
                parent = (x - 1) / 2;
            }
        }

        // peek
        public int peek() {
            return arr.get(0);// heap min hai root hee min heap hai
        }

        private void heapify(int i) {// logn heapify heap ko fix krta hai n node to logn
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIdx = i; // root

            if (left < arr.size() && arr.get(minIdx) > arr.get(left)) {// max heap => > to <
                minIdx = left;
            }

            if (right < arr.size() && arr.get(minIdx) > arr.get(right)) {// max heap => > to <
                minIdx = right;
            }

            if (minIdx != i) { // i root nhi hai to
                // swap
                int temp = arr.get(i);
                arr.set(i, arr.get(minIdx));
                arr.set(minIdx, temp);

                heapify(minIdx);

            }
        }

        public in remove() {// O(nlogn)

            int data = arr.get(0);

            // step 1 swap first and last aessa krnr pr min heap bigad jati h is heapify
            // function ka use krte h
            int temp = arr.get(data);
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);

            // step 2 delete last
            arr.remove(arr.size() - 1);

            // satep 3 heapify
            heapify(0);
            return data;

        }

        public boolean isEmpty() {
            return arr.size() == 0;
        }

        // heap sort => max heap --> assending min heap --> desecending order

        public static void heapifys(int arr[], int i, int size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int maxIdx = i; // root

            if (left < size && arr[left] > arr[maxIdx]) { // desending order > to <
                minIdx = left;
            }

            if (right < size && arr[right] > arr[maxIdx]) { // desending order > to <
                minIdx = right;
            }

            if (maxIdx != i) {
                // swap

                int temp = arr[i];
                arr[i] = arr[maxIdx];
                arr[maxIdx] = temp;

                heapifys(arr, maxIdx, size);
            }

        }

        public static void heapSort(int arr[]) {// O(nlogn)
            // step - build maxheap
            int n = arr.length;
            for (int i = n / 2; i >= 0; i--) {// O(nlogn)
                heapifys(arr, i, n);
            }

            // 2ns step

            for (int i = n - 1; i > 0; i--) {// O(nlogn)
                // swap(largest first with last)
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapifys(arr, 0, i);
            }
        }
    }

    // nearbby cars
    // we are given N points in a 2D plane which are location of N cars if we are at
    // tbe arigin print the nearsest K cars
    // C0(3,3)
    // c1(5,-1)
    // c2(-2 4)
    // ans C0 & C2

    static class Point implements Comprable<Point> {
        int x;
        int y;
        int distsq;
        int idx;

        public Point(int x, int y, int distsq, int idx) {
            this.x = x;
            this.y = y;
            this.distsq = distsq;
            this.idx = idx;
        }

        @Override
        public int comapreTo(Point p2) {
            return this.distsq - p2.distsq;// sorting distance ke bae asending
        }
    }

    // weakest soldier

    static class Row implements Comprable<Row> {
        int soldirs;
        int idx;

        public Row(int soldirs, int idx) {
            this.idx = idx;
            this.soldirs = soldirs;
        }

        @Override
        public int comapreTo(Row r2) {
            if (this.soldirs == r2.soldirs) {// count same
                return this.idx - r2.idx;
            } else {
                return this.soldirs - r2.soldirs;
            }
        }

    }

    /* sliding window maximum maximum of all sub array of size k O(nlogn) */
    static class Pair implements Comparable<Pair> {
        int value;
        int idx;

        public Pair(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public int comapreTo(Pair p2) {
            // asssending
            // return this.value - p2.value;
            // desending
            return p2.value - this.value;
        }
    }

    public static void main(String args[]) {

        // Heap h = new Heap();// h ya pq
        // h.add(3);// h ya pq
        // h.add(1);// h ya pq
        // h.add(4);
        // h.add(5);

        // while (!h.isEmpty()) {
        // System.out.println(h.peek());
        // h.remove();
        // }

        // int arr[] = { 1, 2, 3, 4, 5 };
        // heapSort(arr);

        // // print

        // for (int i = 0; i < arr.length; i++) {
        // System.out.println(arr[i] + "");
        // }
        // System.out.println();

        // int points[][] = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        // int k = 2;

        // PriorityQueue<Point> pq = new PriorityQueue<>();
        // for (int i = 0; I < points.length; i++) {
        // int distsq = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        // pq.add(new Point(points[i][0], points[i][1], distsq, i));

        // }

        // // nearset K cars
        // for (int i = 0; i < k; i++) {
        // System.out.println("C" + pq.remove().idx);
        // }

        // connect n ropes
        // given are N ropes of differnt length the task is to connect these ropes into
        // one rope with minimum cost such that the 'cost to connect two ropes is equal
        // to the sume of their length'
        // ropes = {4,3,2,6}
        // ans = 29
        /* connect2&3[5] connect 5 & 4[9] connect 9 & 6[15] */

        // int roaps[] = { 2, 3, 3, 4, 6 };
        // PriorityQueue<java.awt.Point> pq = new PriorityQueue<>();
        // for (int i = 0; i < roaps.length; i++) {
        // pq.add(roaps[i]);
        // }

        // int cost = 0;
        // while (pq.size() > 1) {
        // int min = pq.remove();
        // int min2 = pq.remove();

        // cost += min + min2;
        // pq.add(min + min2);
        // }

        // System.out.println("cost connecting roaps" + cost)

        // int army[][] = { { 1, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0
        // } };
        // int k = 2;

        // PriorityQueue<Row> pq = new PriorityQueue<>();
        // for (int i = 0; i < army.length; i++) {
        // int count = 0;
        // for (int j = 0; j < army[0].length; j++) {
        // count += army[i][j] == 1 ? 1 : 0;
        // }
        // pq.add(new Row(count, i));
        // }

        // for (int i = 0; i < k; i++) {
        // System.out.println("R" + pq.remove().idx);
        // }

        // int arr[] = { 1, 3, -1 - 3, 5, 3, 6, 7 };
        // int k = 3; // window size
        // int result[] = new int[arr.length - k + 1];// n-k+1
        // PriorityQueue<Pair> pq = new PriorityQueue<>();

        // // 1st window
        // for (int i = 0; i < k; i++) {
        // pq.add(new Pair(arr[i], i));
        // }

        // result[0] = pq.peek().value;

        // for (int i = k; i < arr.length; i++) {
        // while (pq.size() > 0 && pq.peek().idx <= (i - k)) {
        // pq.remove();
        // }

        // pq.add(new Pair(arr[i], i));
        // result[i - k + 1] = pq.peek().value;
        // }

        // for (int i = 0; i < result.length; i++) {
        // System.out.print(result[i] + " ");
        // }
        // System.out.println();

    }
}
