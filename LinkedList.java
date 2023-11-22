import java.util.List;
import java.utill.*;

import org.w3c.dom.Node;

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

    // Question 1 :
    // Intersection of Two Linked Lists
    // In a system there are two singly linked list. By some programming error, the
    // end node of one
    // of the linked lists got linked to the second list, forming an inverted
    // Y-shaped list. Write a
    // program to get the point where two linked lists merge.

    static class Node {// TC m*n sc O(1)
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public Node getIntersectionNode(Node head1, Node head2) {
        while (head2 != null) {
            Node temp = head1;
            while (temp != null) {
                if (temp == head2) {
                    return head2;
                }
                temp = temp.next;
            }
            head2 = head2.next;
        }
        return null;
    }

    // Question 2 :
    // Delete N Nodes After M Nodes of a Linked List
    // We have a linked list and two integers M and N. Traverse the linked list such
    // that you retain M
    // nodes then delete next N nodes, continue the same till end of the linked
    // list. Difficulty Level:
    // Rookie.
    // Sample Input 1 : M=2 N=2 LL: 1->2->3->4->5->6->7->8
    // Sample Output 1 : 1->2->5->6
    // Sample Input 2 : M=3 N=2 LL: 1->2->3->4->5->6->7->8->9->10
    // Sample Output 2 : 1->2->3->6->7->8

    static Node push(Node head_ref, int new_data) {
        Node new_node = new Node();
        new_node.data = new_data;
        new_node.next = (head_ref);
        (head_ref) = new_node;
        return head_ref;
    }

    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.printf("%d ", temp.data);
            temp = temp.next;
        }
        System.out.printf("\n");
    }

    static void skipMdeleteN(Node head, int M, int N) {
        Node curr = head, t;
        int count;
        while (curr != null) {
            for (count = 1; count < M && curr != null; count++)
                curr = curr.next;
            if (curr == null)
                return;
            t = curr.next;
            for (count = 1; count <= N && t != null; count++) {
                Node temp = t;
                t = t.next;
            }
            curr.next = t;
            curr = t;
        }
    }

    // Question 3 :
    // Swapping Nodes in a Linked List
    // We have a linked list and two keys in it, swap nodes for two given keys.
    // Nodes should be
    // swapped by changing links. Swapping data of nodes may be expensive in many
    // situations when
    // data contains many fields. It may be assumed that all keys in the linked list
    // are distinct.
    // Sample Input 1 : 1->2->3->4, x = 2, y = 4
    // Sample Output 1 : 1->4->3->2

    // Node head;

    public void swapNodes(int x, int y) {
        if (x == y)
            return;
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x) {
            prevX = currX;
            currX = currX.next;
        }
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y) {
            prevY = currY;
            currY = currY.next;
        }
        if (currX == null || currY == null)
            return;
        if (prevX != null)
            prevX.next = currY;
        else
            head = currY;
        if (prevY != null)
            prevY.next = currX;
        else
            head = currX;
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }

    public void push(int new_data) {
        Node new_Node = new Node(new_data);
        new_Node.next = head;
        head = new_Node;
    }

    public void printList() {
        Node tNode = head;
        while (tNode != null) {
            System.out.print(tNode.data + " ");
            tNode = tNode.next;
        }
    }

    // Odd Even Linked List
    // We have a Linked List of integers, write a function to modify the linked list
    // such that all even
    // numbers appear before all the odd numbers in the modified linked list. Also,
    // keep the order of
    // even and odd numbers same.
    // Sample Input 1 : 8->12->10->5->4->1->6->NULL
    // Sample Output 1 : 8->12->10->4->6->5->1->NULL
    // Sample Input 2 : 1->3->5->7->NULL
    // Sample Output 2 : 1->3->5->7->NULL

    // void segregateEvenOdd() {
    // Node end = head;
    // Node prev = null;
    // Node curr = head;
    // while (end.next != null)
    // end = end.next;
    // Node new_end = end;
    // while (curr.data % 2 != 0 && curr != end) {
    // new_end.next = curr;
    // curr = curr.next;
    // new_end.next.next = null;
    // new_end = new_end.next;
    // }
    // if (curr.data % 2 == 0) {
    // head = curr;
    // while (curr != end) {
    // if (curr.data % 2 == 0) {
    // prev = curr;
    // curr = curr.next;
    // } else {
    // prev.next = curr.next;
    // curr.next = null;
    // new_end.next = curr;
    // new_end = curr;
    // curr = prev.next;

    // }
    // }
    // } else
    // prev = curr;
    // if (new_end != end && end.data % 2 != 0) {
    // prev.next = end.next;
    // end.next = null;
    // new_end.next = end;
    // }
    // }

    // void push(int new_data) {
    // Node new_node = new Node(new_data);
    // new_node.next = head;
    // head = new_node;
    // }

    // void printList() {
    // Node temp = head;
    // while (temp != null) {
    // System.out.print(temp.data + " ");
    // temp = temp.next;
    // }
    // System.out.println();
    // }

    // Question 5 :
    // Merge k Sorted Lists
    // We have K sorted linked lists of size N each, merge them and print the sorted
    // output.
    // Sample Input 1 : k = 2, n = 2
    // l1 = 1->3->NULL
    // l2 = 6->8->NULL
    // l3 = 9->10->NULL
    // Sample Output 1 : 1>3->6->8->9->10->NULL

    public static Node SortedMerge(Node a, Node b) {
        Node result = null;
        if (a == null)
            return b;
        else if (b == null)
            return a;
        if (a.data <= b.data) {
            result = a;
            result.next = SortedMerge(a.next, b);
        } else {
            result = b;
            result.next = SortedMerge(a, b.next);
        }
        return result;
    }

    public static Node mergeKLists(Node arr[], int last) {
        while (last != 0) {
            int i = 0, j = last;
            while (i < j) {
                arr[i] = SortedMerge(arr[i], arr[j]);
                i++;
                j--;
                if (i >= j)
                    last = j;
            }
        }
        return arr[0];
    }

    // public static void printList(Node node) {
    // while (node != null) {
    // System.out.print(node.data + " ");
    // node = node.next;
    // }
    // } // TC O(logn*n)

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
        // DoubleLL dll = new DoubleLL();
        // dll.addFirst(1);
        // dll.addFirst(2);
        // dll.addFirst(2);

        Solution list = new Solution();
        Node head1, head2;
        head1 = new Node(10);
        head2 = new Node(3);
        Node newNode = new Node(6);
        head2.next = newNode;
        newNode = new Node(9);
        head2.next.next = newNode;
        newNode = new Node(15);
        head1.next = newNode;
        head2.next.next.next = newNode;
        newNode = new Node(30);
        head1.next.next = newNode;
        head1.next.next.next = null;
        Node intersectionPoint = list.getIntersectionNode(head1, head2);
        if (intersectionPoint == null) {
            System.out.print(" No Intersection Point \n");
        } else {
            System.out.print("Intersection Point: " + intersectionPoint.data);
        }

        Node head = null;
        int M = 2, N = 3;
        head = push(head, 10);
        head = push(head, 9);
        head = push(head, 8);
        head = push(head, 7);
        head = push(head, 6);
        head = push(head, 5);
        head = push(head, 4);
        head = push(head, 3);
        head = push(head, 2);
        head = push(head, 1);
        System.out.printf("M = %d, N = %d \n" +
                "Linked list we have is :\n", M, N);
        printList(head);
        skipMdeleteN(head, M, N);
        System.out.printf("\nLinked list on deletion is :\n");
        printList(head);

        Solution llist = new Solution();
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);
        System.out.print(
                "\n Linked list before ");
        llist.printList();
        llist.swapNodes(4, 3);
        System.out.print(
                "\n Linked list after ");
        llist.printList();

        // Solution llist = new Solution();
        // llist.push(11);
        // llist.push(10);
        // llist.push(8);
        // llist.push(6);
        // llist.push(4);
        // llist.push(2);
        // llist.push(0);
        // System.out.println("Linked List");
        // llist.printList();
        // llist.segregateEvenOdd();
        // System.out.println("updated Linked List");
        // llist.printList();

        // int k = 3;
        // int n = 4;
        // Node arr[] = new Node[k];
        // arr[0] = new Node(1);
        // arr[0].next = new Node(3);
        // arr[0].next.next = new Node(5);
        // arr[0].next.next.next = new Node(7);
        // arr[1] = new Node(2);
        // arr[1].next = new Node(4);
        // arr[1].next.next = new Node(6);
        // arr[1].next.next.next = new Node(8);
        // arr[2] = new Node(0);
        // arr[2].next = new Node(9);
        // arr[2].next.next = new Node(10);
        // arr[2].next.next.next = new Node(11);
        // Node head = mergeKLists(arr, k - 1);
        // printList(head);
    }
}