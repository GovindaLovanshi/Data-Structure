import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;

public class QueueB {
    static class Stack {
        Deque<Integer> deque = new LinkedList<>();

        public void push(int data) {
            deque.addLast(data);
        }

        public int pop() {
            return deque.removeLast();
        }

        public int peek() {
            return deque.getLast();
        }
    }

    static class Queue {
        Deque<Integer> deque = new LinkedList<>();

        public void add(int data) {
            deque.addLast(data);
        }

        public int remove() {
            return deque.removeFirst();
        }
        
        public int peek() {
            return deque.getFirst();
        }
    }
    // stack using 2 queue
    static class stack {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        // empty
        public static boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        // push
        public static void push(int data) { // O(1)
            if (!q1.isEmpty()) {
                q1.add(data);
            } else {
                q2.add(data);
            }
        }

        // pop
        public static int pop() { // O(n)
            if (isEmpty()) {
                System.out.println(" empty stack");
                return -1;
            }

            int top = -1;
            // case 1
            if (!q1.isEmpty()) {
                while (q1.isEmpty()) {
                    top = q1.remove();
                    if (!q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }
            } else {// case 2
                while (q2.isEmpty()) {
                    top = q2.remove();
                    if (!q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }

            }
            return top;
        }

        // peek
        public static int peek() { // O(n)
            if (isEmpty()) {
                System.out.println(" empty stack");
                return -1;
            }

            int top = -1;
            // case 1
            if (!q1.isEmpty()) {
                while (q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }
            } else {// case 2
                while (q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }

            }
            return top;

        }

    }

    public static void slidingWindowMax(int nums[], int k) {
        Deque<Integer> q = new LinkedList<>();

        for(int i=0; i<k; i++) {
            while(!q.isEmpty() && nums[q.getLast()] < nums[i]) {
                q.removeLast();
            }
            q.addLast(i);
        }

        // for(int i=k; i<nums.length; i++) {
        //     System.out.print(nums[q.getLast()] + " ");

        //     while(!q.isEmpty() && )
        // }

    }

    // first non repeating letter in a stream of characters
    // aabccxb
    // a __> non reaepting
    // aa reapeting
    public static void firstnonReapeating(String str) { // TC O(n)
        int freq[] = new int[26]; // a to z tracking
        Queue<Character> q = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            q.add(ch);
            freq[ch - 'a']++;// charc- charc = integer

            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }

            if (q.isEmpty()) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(q.peek() + "");
            }
        }
        System.out.println();
    }

    // interleave 2 halves of a queue (even length) interleve --> ek dusre ke uper
    // jodna examole choti banana
    // 1 st half 1 2 3 4 5 |half 2 nd 6 7 8 9 10
    // 1 6 2 7 3 8 4 9 5 10
    public static void interLeave(Queue<Integer> q) { // TC O(n); SC O(n)
        Queue<Integer> FirstHalf = new LinkedList<>();
        int size = q.size();

        for (int i = 0; i < size / 2; i++) {
            FirstHalf.add(q.remove()); // q se remove kiya or firsthalf me add kr diya
        }

        while (!FirstHalf.isEmpty()) {
            q.add(FirstHalf.remove());// first half se remove kiya or q me add kr diya
            q.add(q.remove()); // q se remove kiya or q me add kr diya
        }

    }

    // queue reversal TC O(n) SC O(n)
    public static void qreversal(Queue<Integer> Q) {
        Stack<Integer> s = new Stack<>();

        while (!Q.isEmpty()) {
            s.push(Q.remove());
        }

        while (!s.isEmpty()) {
            Q.add(s.pop());
        }
    }

//     Generate Binary Numbers
// Given a number N. The task is to generate and print all binary numbers with decimal values from
// 1 to N.
// Sample Input 1 : N =2
// Sample Output 1 : 1 10
// Sample Input 2 : 5.
// Sample Output 2 : 1 10 11 100 101

static void generatePrintBinary(int n){
    Queue<String> q = new LinkedList<String>();
    q.add("1");
    while (n-- > 0) {
    String s1 = q.peek();
    q.remove();
    System.out.println(s1);
    String s2 = s1;
    q.add(s1 + "0");
    q.add(s2 + "1");
    }
}   

// Question 2 :
// Connect n ropes with minimum cost
// Given are N ropes of different lengths, the task is to connect these ropes into one rope with
// minimum cost, such that the cost to connect two ropes is equal to the sum of their lengths.
// Sample Input 1 : N = 4, arr = [4 3 2 6]
// Sample Output 1 : 29
// Sample Input 2 : N = 2, arr = [1 2 3]
// Sample Output 2 : 9

static int minCost(int arr[], int n){
    PriorityQueue<Integer> pq
    = new PriorityQueue<Integer>();
    for (int i = 0; i < n; i++) {
    pq.add(arr[i]);
    }
    int res = 0;
    while (pq.size() > 1) {
    int first = pq.poll();
    int second = pq.poll();
    res += first + second;
    pq.add(first + second);
    }
    return res;
    }


//     Question 3 :
// Job Sequencing Problem
// We have an array of jobs where every job has a deadline and associated profit if the job is
// finished before the deadline. It is also given that every job takes a single unit of time, so the
// minimum possible deadline for any job is 1. Maximize the total profit if only one job can be
// scheduled at a time.
// Sample Input 1 :
// JobID Deadline Profit
// a 4 20
// b 1 10
// c 1 40
// d 1 30
// Sample Output 1 : c, a

// solve this question wit greedy algo

// Question 4 :
// Reversing the first K elements of a Queue
// We have an integer k and a queue of integers, we need to reverse the order of the first k
// elements of the queue, leaving the other elements in the same relative order.
// Sample Input 1 : Q = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100] ,k=5
// Sample Output 1 : Q = [50, 40, 30, 20, 10, 60, 70, 80, 90, 100]





// Question 5 :
// Maximum of all subarrays of size k
// We have an array arr[] of size N and an integer K. Find the maximum for each and every
// contiguous subarray of size K.
// Sample Input 1 : N=9, K=3 arr= 1 2 3 1 4 5 2 3 6
// Sample Output 1 : 3 3 4 5 5 5 6


static void printMax(int arr[], int n, int k){
    Deque<Integer> Qi = new LinkedList<Integer>();
    int i;
    for (i = 0; i < k; ++i) {
    while (!Qi.isEmpty() && arr[i] >=
    arr[Qi.peekLast()])
    Qi.removeLast();
    Qi.addLast(i);
    }
    for (; i < n; ++i) {
    System.out.print(arr[Qi.peek()] + " ");
    while ((!Qi.isEmpty()) && Qi.peek() <=
    i - k)
    Qi.removeFirst();
    while ((!Qi.isEmpty()) && arr[i] >=
    arr[Qi.peekLast()])
    Qi.removeLast();
    Qi.addLast(i);
    }
    System.out.print(arr[Qi.peek()]);
    }
    



    public static void main(String args[]) {

        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.pop(3);

        while (!s.isEmpty()) {
        System.out.println(s.peek());
        s.pop();
        }

        String str = "aabccbx";

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);
        q.add(8);
        q.add(9);
        q.add(10);

        interLeave(q);

        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
        Q.add(2);
        Q.add(3);
        Q.add(4);
        Q.add(5);

        qreversal(Q);
        prit q
        while(q.isEmpty()){
        System.out.print(q.remove() + " ");
        }
        System.out.println();

        // print q
        while (q.isEmpty()) {
        System.out.print(Q.remove() + " ");
        }
        System.out.println();

    }
}
