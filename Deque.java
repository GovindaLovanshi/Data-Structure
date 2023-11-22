import java.util.*;

public class Deque {// deque => double ended queue add first add last removefirst removelast
                    // getfirst getlast
    // stack using deque
    static class stack {
        Deque<Intege> deque = new LinkedList<>();

        public void push(int data) {// O(1)
            deque.addLast(data);
        }

        public int pop() {// O(1)
            return deque.removeLast();
        }

        public int peek() {// O(1)
            return deque.getLast();
        }
    }

    // stack using deque
    static class Queue {
        Deque<Intege> deque = new LinkedList<>();

        public void add(int data) {// O(1)
            deque.addLast(data);
        }

        public int remove() {// O(1)
            return deque.removeFirst();
        }

        public int peek() {// O(1)
            return deque.getFirst();
        }
    }

    // Generate Binary Numbers
    // GivenanumberN.The task is
    // togenerateandprintallbinarynumberswithdecimalvaluesfrom
    // Sample Input 1: N =2 Sample Output 1: 1 10
    static void generatePrintBinary(int n) {
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

    public class Solution {
        static void printMax(int arr[], int n, int k) {
            Deque<Integer> Qi = new LinkedList<Integer>();
            int i;
            for (i = 0; i < k; ++i) {
                while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()]) {
                    Qi.removeLast();
                    Qi.addLast(i);
                }

            }
            for (; i < n; ++i) {
                System.out.print(arr[Qi.peek()] + " ");
                while ((!Qi.isEmpty()) && Qi.peek() <= i - k) {

                }
                Qi.removeFirst();
                while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()]) {
                    Qi.removeLast();
                    Qi.addLast(i);
                }

            }
            System.out.print(arr[Qi.peek()]);
        }
    }

    public static void main(String args[]) {
        // Stack s = new Stack();
        // s.push(1);
        // s.push(2);
        // s.push(3);

        // System.out.println("peek"+ s.peek() );
        // System.out.println(s.pop());
        // System.out.println(s.pop());
        // System.out.println(s.pop());

        Queue q = new Queue();
        q.add(1);
        q.add(2);
        q.add(3);

        System.out.println("peek = " + q.peek());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());

    }

}
