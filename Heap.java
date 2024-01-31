import java.nio.file.Path;
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

    // Minimum time required to fill given N slots
    // We have an integer N which denotes the number of slots, and an array arr[]
    // consisting of K
    // integers in the range [1, N] repreand. Each element of the array are in the
    // range [1, N] which
    // represents the indices of the filled slots. At each unit of time, the index
    // with filled slot fills the
    // adjacent empty slots. The task is to find the minimum time taken to fill all
    // the N slots..
    // Sample Input 1 : N = 5, K = 5, arr[] = {1, 2, 3, 4, 5}
    // Sample Output 1 : 1
    // Sample Input 1 : N = 6, K = 2, arr[] = {2, 6}
    // Sample Output 1 : 2

    public static void minTime(int arr[], int N, int K) {// Tc O(n)
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[N + 1];
        int time = 0;
        for (int i = 0; i < K; i++) {
            q.add(arr[i]);
            vis[arr[i]] = true;
        }

        while (q.size() > 0) {
            for (int i = 0; i < q.size(); i++) {
                int curr = q.poll();
                if (curr - 1 >= 1 &&
                        !vis[curr - 1]) {
                    vis[curr - 1] = true;
                    q.add(curr - 1);
                }
                if (curr + 1 <= N &&
                        !vis[curr + 1]) {
                    vis[curr + 1] = true;
                    q.add(curr + 1);
                }
            }
            time++;
        }

        System.out.println(time - 1);

    }

    // Question 3 :
    // Path With Minimum Effort
    // We have a two-dimensional grid, each cell of which contains an integer cost
    // which represents
    // a cost to traverse through that cell, we need to find a path from the top
    // left cell to the bottom
    // right cell by which the total cost incurred is minimum.
    // Sample Input 1 :
    // { { 31, 100, 65, 12, 18 },
    // { 10, 13, 47, 157, 6 },
    // { 100, 113, 174, 11, 33 },
    // { 88, 124, 41, 20, 140 },
    // { 99, 32, 111, 41, 20 } }
    // Sample Output 1 : 327

    static String decode(String str) {
        Stack<Integer> integerstack = new Stack<>();
        Stack<Character> stringstack = new Stack<>();
        String temp = "", result = "";
        for (int i = 0; i < str.length(); i++) {
            int count = 0;
            if (Character.isDigit(str.charAt(i))) {
                while (Character.isDigit(str.charAt(i))) {
                    count = count * 10 + str.charAt(i) - '0';
                    i++;
                }
                i--;
                integerstack.push(count);
            } else if (str.charAt(i) == ']') {
                temp = "";
                count = 0;
                if (!integerstack.isEmpty()) {
                    count = integerstack.peek();
                    integerstack.pop();
                }
                while (!stringstack.isEmpty() && stringstack.peek() != '[') {
                    temp = stringstack.peek() + temp;
                    stringstack.pop();
                }
                if (!stringstack.empty() && stringstack.peek() == '[')
                    stringstack.pop();
                for (int j = 0; j < count; j++)
                    result = result + temp;
                for (int j = 0; j < result.length(); j++)
                    stringstack.push(result.charAt(j));
                result = "";
            } else if (str.charAt(i) == '[') {
                if (Character.isDigit(str.charAt(i - 1)))
                    stringstack.push(str.charAt(i));
                else {
                    stringstack.push(str.charAt(i));
                    integerstack.push(1);
                }
            } else {
                stringstack.push(str.charAt(i));
            }

        }
        while (!stringstack.isEmpty()) {
            result = stringstack.peek() + result;
            stringstack.pop();
        }

        return result;

    }

    // Minimum Operations to Halve Array Sum
    // We have an array Arr[], the task is to find out the minimum number of
    // operations to make the
    // sum of array elements lesser or equal to half of its initial value. In one
    // such operation, it is
    // allowed to half the value of any array element.
    // Sample Input 1 : [1, 5, 8, 19]
    // Sample Output 1 : 3

    static int minops(ArrayList<Integer> nums) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < nums.size(); i++) {
            pq.add(-nums.get(i));
        }
        double temp = sum;
        int cnt = 0;
        while (temp > sum / 2) {
            int x = -pq.peek();
            pq.remove();
            temp -= Math.ceil(x * 1.0 / 2);
            pq.add(x / 2);
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        // p = new PriorityQueue<>();
        // k = 3;
        // int arr[] = { 10, 20, 11, 70, 50, 40, 100, 5, };
        // List<Integer> list = KthLargest(arr);
        // for (int x : list) {
        // System.out.print(x + " ");
        // }

        int N = 6;
        int arr[] = { 2, 6 };
        int K = arr.length;
        minTime(arr, N, K);

        String str = "3[b2[ca]]";
        System.out.println(decode(str));// TC O(n2logn)

        ArrayList<Integer> nums = new ArrayList<Integer>(List.of(4, 6, 3, 9, 10, 2));
        int count = minops(nums);// TC O(nlogn)
        System.out.println(count);

    }
}
