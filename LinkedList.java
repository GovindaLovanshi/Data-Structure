import java.utill.*;

public class LinkedList {
    public static class Node {
        int data;
        Node next;// reference variable

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

    }

    public static Node head;
    public static Node tail;
    public static int size;
    // public static Node prev;

    public void addFirst(int data) {// o(1) single operation
        // step 1 create a node
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        // steap 2 new node next = head
        newNode.next = head;// link

        // steap 3
        head = newNode;
    }

    public void addLast(int data) {// constant
        // staep 1
        Node newNode = new Node(data);
        size++;
        // li is empty
        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;

        tail = newNode;
    }

    public void print() {// TC O(n) linnear tc
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void addMiddle(int index, int data) {// linear
        if (index == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;// cal size
        Node temp = head;

        int i = 0;// index
        while (i < index - 1) {// i-1 --> temp
            temp = temp.next;
            i++;
        }

        // i = index-1 tem--> prev
        newNode.next = temp.next;
        temp.next = newNode;// add
    }

    public int removeFirst() {
        if (size == 0) {
            System.out.println("ll is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() {// tc O(n)
        if (size == 0) {
            System.out.println("ll is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {// delete head and tail
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        // prev i = size-2 last index = size-1
        Node prev = head; // tail
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;// tail data
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    // search for a key in linked list returrn the possition where its found if not
    // found return -1
    // sarch iterative
    public int Search(int key) {// Tc O(n)
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {// key founf
                return i;
            }
            temp = temp.next;
            i++;
        }
        // key not found
        return -1;
    }

    // search for a key in linked list returrn the possition where its found if not
    // found return -1
    // sarch recursive
    public int helper(Node head, int key) {// tc O(n) sc O

        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int idx = helper(head.next, key);
        if (idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    public int rescursiveSerch(int key) {
        return helper(head, key);
    }

    // reverse linked liist
    public void reverseLinkedlist() {// Tc O(n)
        Node prev = null;
        Node curr = tail = head;
        Node next;

        while (curr != null) {
            next = curr.next;// step 1
            curr.next = prev;// step 2
            prev = curr;// step 3
            curr = next;// step 4
        }
        head = prev;
    }

    public void deleteNthNode(int n) {
        // cal size
        int size = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }

        if (n == size) {
            head = head.next; // remove
            return;
        }

        // sz-n
        int i = 1;
        int iToFind = size - n;
        Node prev = head;
        while (i < iToFind) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;

    }

    // check if pallindrom ll
    public Node midNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;// +1
            fast = fast.next.next;// +2
        }
        return slow;// slow is my mid
    }

    public boolean pallindrom() {
        if (head == null || head.next == null) {
            return true;
        }

        // step 1 find mid
        Node midNode = midNode(head);

        // second step reverse 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev; // right half node
        Node left = head;
        // step 3 right half & left half
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static boolean isCycle() {// floyed cycle detecting
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;// +1
            fast = fast.next.next;// +2
            if (slow == fast) {// cycle exist
                return true;
            }
        }
        return false;// cycle does not exist
    }

    public static void removeCycle() {
        // detect a cycle
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;// +1
            fast = fast.next.next;// +2
            if (slow == fast) {// cycle exist
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }

        // find meeting point
        slow = head;
        Node prev = null;// last Node
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }

        // remove cycle last.next = null

        prev.next = null;
    }

    /* /zig zag linked list */

    public void ZigZag() {
        // find mid

        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node mid = slow;

        // reverse 2nd half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node left = head;
        Node right = prev;
        Node nextL, nextR;

        // merge alternative -- zig zag merge

        while (left != null & right != nul) {
            nextL = left.next;
            left.next = right;
            nextR = right.next;
            right.next = nextL;

            left = nextL;
            right = nextR;
        }
    }

    public class Node {
        Node next;
        Node prev;
        int data;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

    }

    public void addFirst() {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    // print
    public void prints() {
        Node temp = haed;
        while (temp != null) {
            System.out.println(temp.data + "<-->");
            temp = temp.next;
        }

        System.out.println();
    }

    public void reverse() {

    }

    // remove last
    public int removelast() {
        if (head == null) {
            System.out.println("DLL is empty");
            return Integer.MIN_VALUE;
        }
        if (size == 1) {
            int val = head.data;
            head = tail = null;
            size--;
            return val;

        }

        int val = head.data;
        head = head.next;
        head.prev = null;
        size--;
        return val;
    }

    public void Reverse() {
        Node curr = head;
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;// step 1
            curr.next = prev;// step 2
            curr.prev = next;// step 3

            prev = curr;
            curr = next;
        }
        haed = prev;
    }

    static Node addToEmpty(Node last, int data) {
        if (last != null) {
            return last;
        }
        Node newNode = new Node();
        newNode.data = data;
        last = newNode;
        newNode.next = last;
        return last;
    }

    static Node addFront(Node last, int data) {
        if (last == null) {
            return addToEmpty(last, data);
        }
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = last.next;
        last.next = newNode;
        return last;
    }

    static Node addEnd(Node last, int data) {
        if (last == null) {
            return addToEmpty(last, data);
        }
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = last.next;
        last.next = newNode;
        last = newNode;
        return last;
    }

    public static void main(String args[]) {
        /*
         * LinkedList ll = new LinkedList();
         * // ll.print();
         * ll.addFirst(1);
         * // ll.print();
         * ll.addFirst(2);
         * //ll.print();
         * ll.addLast(3);
         * ll.addMiddle(2, 9);
         * // ll.print();
         * ll.addLast(4);
         * ll.print();
         * //System.out.println(ll.size);
         * System.out.println(ll.Search(3));
         * System.out.println(ll.Search(10));
         */
        /*
         * head = new Node(1);
         * Node temp = new Node(2);
         * head.next = new Node(3);
         * head.next.next = new Node(4);
         * head.next.next.next = temp;
         */
        DoubleLL dll = new DoubleLL();
        dll.addFirst(1);
        dll.addFirst(2);
        dll.addFirst(2);

    }
}