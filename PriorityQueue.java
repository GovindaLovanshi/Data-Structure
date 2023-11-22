import java.util.*;
public class PriorityQueue{// add O(logn) remove O(logn) peek O(1)
    static class Student implements Comparable<Student> {// overriding
        String name;
        int rank;

        public Student(String name,int rank){
            this.name = name;
            this.rank = rank;
        }

        @Override
        public int compareTo(Student s2){
            return this.rank - s2.rank; // compare rank and s2
        }
    }
    public static void main(String args[]){
        //PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Student> pq = new PriorityQueue<>();


        pq.add(new Student("A", 4));
        pq.add(new Student("B", 5));
        pq.add(new Student("C", 6));
        pq.add(new Student("D", 10));
        
       // while(!pq.isEmpty()){
       //     System.out.println(pq.peek());
      //      pq.remove();
       // }

        while(!pq.isEmpty()){
            System.out.println(pq.peek().name + " -> "+ pq.peek());
            pq.remove();
        }
    }
}
