import java.util.*;

public class Stack {
    static class stack {
        static ArrayList<Integer> list = new ArrayList<>();

        public static boolean isEmpty() {
            return list.size() == 0;
        }

        // push
        public static void push(int data) {
            list.add(data);
        }

        // pop
        public static int pop() {
            if (isEmpty()) {
                return -1;
            }
            int top = list.get(list.size() - 1);
            list.remove(list.size() - 1);// lasty index ki value remove
            return top;
        }

        // top
        public static int peek() {
            if (isEmpty()) {
                return -1;
            }
            return list.get(list.size() - 1);// top
        }
    }

    static class Node {
        Node next;// next pointer
        int data;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class StackB {
        static Node head = null;

        public static boolean isEmpty() {
            return head == null;
        }

        // push
        public static void Push(int data) {
            Node newNode = new Node(data);

            if (isEmpty()) {
                return;
            }

            newNode.next = head;
            head = newNode;
        }

        // pop
        public static int pop() {
            if (isEmpty()) {
                return -1;
            }

            int top = head.data;
            head = head.next;
            return top;
        }

        // peek
        public static int Peek() {
            if (isEmpty()) {
                return -1;
            }

            return head.data;
        }
    }

    // push at the bottom

    public static void pushAtBottom(Stack<Integer> s, int data) {// O(n) tc (1)
        if (isEmpty()) {
            s.push(data);
            return;
        }

        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }
    // reverse a string
    // "abc" ----> cba

    public static String reverseString(String Str) {
        Stack<Character> s = new Stack<>();
        int idx = 0;

        while (idx < Str.length()) {// traverse a string
            s.push(Str.charAt(idx));// push inn stack
            idx++;
        }

        StringBuilder result = new StringBuilder("");

        while (!s.isEmpty()) {// store in reverse order
            char curr = s.pop();
            result.append(curr);// add in last
        }

        return result.toString();
    }

    // reverse stack o(n)

    public static void reverseStack(Stack<Integer> s) {
        if (isEmpty()) {
            return;
        }

        int top = s.pop();// stack se bahar value
        reverseStack(s);
        pushAtBottom(s, data);
        // s.push(top);
    }

    public static void printStack(Stack<Integer> s) {
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    // STOCK SPAN PROBLEM ---> ASK COMPANAY MICROSOFT SAMSUNG FLIPCART ADOBE AMAZON
    // SPAN --> MAX NO OF CONSECUTIVE DAYS FOR WHICH --> PRICR <= TODAY PRICE
    // spam = index - prevhigh
    public static void StocksProblem(int Stocks[], int Span[]) {
        Stack<Integer> s = new Stack<>();
        Span[0] = 1;
        s.push(0);

        for (int i = 1; i < Stocks.length; i++) {
            int currprice = Stocks[i];

            while (!s.isEmpty() && currprice > Stocks[s.peek()]) {
                s.pop();
            }

            if (!s.isEmpty()) {
                Span[i] = i + 1;
            } else {
                int prevhigh = s.peek();
                Span[i] = i - prevhigh;
            }

            s.push(i);
        }

    }

    // valid paranthheses
    public static boolean isValid(String str) { // tc O(n)
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // opening
            if (ch == '(' && ch == '[' && ch == '{') {
                s.push(ch);
            } else {
                if (!s.isEmpty()) {
                    return false;
                }
                // clossing
                if ((s.peek() == '(' && ch == ')') // ()
                        || (s.peeK() == '[' && ch == ']') //
                        || (s.peek() == '{' && ch == '}')) {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        if (!s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // duplicate parantheses
    // max area in histrigram
    // given in a array of integer height representing the histrogram bar height
    // where the width of eachh bar is 1 return the area of largest recangle in the
    // histrogram
    // height[2,1,5,6,2,3] ans = 10 important asked company microsoft paytm facebook
    public static void maxareaHistrogram(int arr[]) {// tc O(n)
        int maxArea = 0;
        int nxtsmlerright[] = new int[arr.length];
        int nxtsmlerleft[] = new int[arr.length];
        // next smaller right O(n)
        Stack<Integer> s = new Stack<Integer>();
        for (int i = arr.lengt - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }
            if (!s.isEmpty()) {
                nxtsmlerright[i] = arr.length;// -1
            } else {// top
                nxtsmlerright[i] = s.peek();
            }
            s.push(i);
        }

        // next smaller left O(n)
        s = new Stack<Integer>(); // empty stack
        for (int i = 0; i < arr.lemgth; i++) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }
            if (!s.isEmpty()) {
                nxtsmlerleft[i] = -1;// -1
            } else {// top
                nxtsmlerleft[i] = s.peek();
            }
            s.push(i);
        }

        // current area: width j-i-1 = nxt right[i] - nxt keft[i]-1 O(n)
        for (int i = 0; i < arr.lengt; i++) {
            int height = arr[i];
            int widthh = nxtsmlerright[i] - nxtsmlerleft[i] - 1;
            int currarea = height * widthh;
            maxarea = Math.max(currarea, maxArea);
        }
        System.out.println("max area of istrogram :" + maxArea);
    }

    public static void main(String args[]) {

        // implement stack in arraylist
        /*
         * Stack s = new stack();
         * 
         * // JCF
         * //Stack<Integer> s = new Stack<>();
         * s.push(1);
         * s.push(2);
         * s.push(3);
         * 
         * while(!s.isEmpty()){
         * System.out.println(s.peek());
         * s.pop();
         * }
         */

        // stack implement in linkedlist
        /*
         * Stack<Integer> s = new Stack<>();
         * 
         * s.push(1);
         * s.push(2);
         * s.push(3);
         * 
         * while(!s.isEmpty()){
         * System.out.println(s.pop());
         * }
         */

        // String Str = "abc";
        // System.out.println( reverseString(Str));
        /*
         * Stack<Integer> s = new Stack<>();
         * s.push(1);
         * s.push(2);
         * s.push(3);
         * 
         * reverseStack(s);
         * printStack(s);
         */

        // int Stocks[] = {100,80,60,70,60,85,100};
        /*
         * int Span[] = new int[Stocks.length];
         * StocksProblem(Stocks, Span);
         * 
         * 
         * for(int i=0; i<Span.length; i++){
         * System.out.println(Span[i]+ "");
         * }
         */

        // next greater element
        // the next greater element some elementx in array is the first greater element
        // is to the right
        // of x in the same array ---> Important
        /*
         * int arr[] = {6,8,0,1,3};
         * Stack<Integer> s = new Stack<>();
         * int nxtgreater[] = new int [arr.length];
         * 
         * for(int i=arr.length-1; i>=0; i--){// next greater left for(int i=0;
         * i<arr.length; i++) next smaller left for(int i=0; i<arr.length; i++)
         * arr[s.peek()] >= arr[i]
         * // 1 while
         * while(!s.isEmpty() && arr[s.peek()] <= arr[i]){//index store --> next smaller
         * rigt arr[s.peek()] >= arr[i] -->
         * s.pop();
         * }
         * 
         * // 2 if else
         * if(!s.isEmpty()){
         * nxtgreater[i] = -1;
         * 
         * }else{
         * nxtgreater[i] = arr[s.peek()];
         * }
         * 
         * // 3 pushh
         * s.push();
         * }
         * 
         * for(int i=0; i<nxtgreater.length; i++){
         * System.out.println(nxtgreater[i]+" ");
         * }
         * System.out.println();
         */

        // String str = "({[]})";
        int arr[] = { 2, 1, 5, 6, 2, 3 };// height of hhistrogram
        maxareaHistrogram(arr);

    }

}
