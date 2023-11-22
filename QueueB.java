import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;

public class QueueB {
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

    public static void main(String args[]) {

        // Stack s = new Stack();
        // s.push(1);
        // s.push(2);
        // s.pop(3);

        // while (!s.isEmpty()) {
        // System.out.println(s.peek());
        // s.pop();
        // }

        // String str = "aabccbx";

        // Queue<Integer> q = new LinkedList<>();
        // q.add(1);
        // q.add(2);
        // q.add(3);
        // q.add(4);
        // q.add(5);
        // q.add(6);
        // q.add(7);
        // q.add(8);
        // q.add(9);
        // q.add(10);

        // interLeave(q);

        // Queue<Integer> Q = new LinkedList<>();
        // Q.add(1);
        // Q.add(2);
        // Q.add(3);
        // Q.add(4);
        // Q.add(5);

        // qreversal(Q);
        // prit q
        // while(q.isEmpty()){
        // System.out.print(q.remove() + " ");
        // }
        // System.out.println();

        // // print q
        // while (q.isEmpty()) {
        // System.out.print(Q.remove() + " ");
        // }
        // System.out.println();

    }
}
