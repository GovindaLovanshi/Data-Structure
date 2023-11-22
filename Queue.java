import java.nio.charset.StandardCharsets;
import java.util.*;

public class Queue {
    static class queueB { // implement queue array
        static int arr[];
        static int size;
        static int rear;
        // static int front; // enter element

        queueB(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
            // front = -1;
        }

        public static boolean isEmpty() {
            return rear == -1;
        }

        // add O(1)
        public static void add(int data) {
            if (rear == size - 1) {
                System.out.println("Queue is full");
                return;

            }

            rear = rear + 1;
            arr[rear] = data;
        }

        // remove O(n) reason sab element ko copy kr ke hataya not use array in queue //
        // using circuler queue
        public static int remov() {
            if (isEmpty()) {
                System.out.println("Que empty");
                return -1;// -1 is element not store
            }

            int front = arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear = rear - 1;
            return front;
        }

        // peek
        public static int peek() {
            if (!isEmpty()) {
                System.out.println("empty queue");
                return -1;
            }
            return arr[0];// front
        }

    }

    static class queue { // implement circuler queue array
        static int arr[];
        static int size;
        static int rear;
        static int front; // enter element

        queueB(int n){
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        public static boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        public static boolean isFull() {
            return (rear + 1) % size == front;
        }

        // add O(1)
        public static void add(int data) {
            if (isFull()) { // size -1 array bhra kini check
                System.out.println("Queue is full");
                return;

            }
            if (front == -1) {
                front = 0;
            }

            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        // remove O(1)
        public static int remove() {
            if (isEmpty()) {
                System.out.println("Que empty");
                return -1;
            }

            int result = arr[front];
            front = (front + 1) % size;
            // last element delete
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }

        // peek // o(1)
        public static int peek() {
            if (!isEmpty()) {
                System.out.println("empty queue");
                return -1;
            }
            return arr[front];
        }

    }

    // queue implement linked list Tc O(n)
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class queueLL { // implement queue LL
        static Node head = null;
        static Node tail = null;

        public static boolean isEmpty() {
            return head == null & tail == null;
        }

        // add O(1)
        public static void add(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
                return;
            }

            tail.next = newNode;
            tail = newNode;
        }

        // remove
        public static int remov() {
            if (isEmpty()) {
                System.out.println("Que empty");
                return -1;
            }

            int front = head.data;
            // single element
            if (tail == head) {
                tail = head = null;
            } else {
                head = head.next;
            }
            return front;
        }

        // peek
        public static int peek() {
            if (!isEmpty()) {
                System.out.println("empty queue");
                return -1;
            }
            return head.data;
        }
    }
    // queue using 2 stacks

    static Stack<Integer> s1 = new Stack<>();
    static Stack<Integer> s2 = new Stack<>();

    public static boolean isEmpty() {
        return s1.isEmpty();
    }

    // add O(n)
    public static void add(int data) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop()); // s1 khali nahi hota jb tak hai to s1 ke element s2 mw daal diya jaye 1st step
        }

        s1.push(data); // s1 me element daal diya

        while (!s2.isEmpty()) {
            s1.push(s2.pop()); // s2 khali nahi hota jb tak s2 e element s1 me daal diya

        }
    }

    public static int remove() {
        if (isEmpty()) {
            System.out.println("queue is Empty");
            return -1;
        }

        return s1.pop();
    }

    // peek

    public static int peek() {
        if (isEmpty()) {
            System.out.println("queue is Empty");
            return -1;
        }

        return s1.peek();
    }

    public static void main(String args[]) {
        // // Queue q = new Queue(3);
        // Queue<Integer> q = new LinkedList<>();// que is a interface or interface ke
        // Queue<Integer> Q = new ArrayDeque<>();
        // object nhi bna sakte
        // /*
        // * q.add(1);
        // * q.add(2);
        // * q.add(3);
        // * System.out.println(q.remove());
        // * q.add(4);
        // * System.out.println(q.remove());
        // * q.add(5);
        // *
        // * while(!q.isEmpty()){
        // * System.out.println(q.peek());
        // * q.remov();
        // * }
        // */

        // Queue q = new Queue();
        // q.add(1);
        // q.add(2);
        // q.add(3);

        // while(!q.isEmpty()){
        // System.out.println(q.pop());
        // q.remove();
        // }
    }
}
