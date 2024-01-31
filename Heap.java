import java.util.*;

public class Heap {
    // K’th largest element in a stream
    // We have an infinite stream of integers, find the k’th largest element at any
    // point of time.
    // Input : stream[] = {10, 20, 11, 70, 50, 40, 100, 5, ...} k = 3
    // Output : {_, _, 10, 11, 20, 40, 50, 50, ...}

    static PriorityQueue<Integer> p;
    static int k;

    static List<Integer> KthLargest(int arr[]) {
        List<Integer> l = new ArrayList<>();
        for (int val : arr) {
            if (p.size() < k) {
                p.add(val);
            } else {
                if (val > p.peek()) {
                    p.poll();// The poll() method of Java Deque Interface is used to retrieve and remove the
                             // head of the deque. On the other hand, if the deque is empty, the method may
                             // return null.
                    p.add(val);
                }
            }

            if (p.size() >= k) {
                l.add(p.peek());
            } else {
                l.add(0);
            }

        }
        return l;
    }

    public static void main(String[] args) {
        p = new PriorityQueue<>();
        k = 3;
        int arr[] = { 10, 20, 11, 70, 50, 40, 100, 5, };
        List<Integer> list = KthLargest(arr);
        for (int x : list) {
            System.out.print(x + " ");
        }

    }
}