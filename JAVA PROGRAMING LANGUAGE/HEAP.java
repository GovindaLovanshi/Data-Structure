import java.util.ArrayList;

public class HEAP {
    static class heap{
        ArrayList<Integer> arr = new ArrayList<>();
        public int add (int data){
            arr.add( data);
            int x = arr.size()-1;
            int par = (r-1)/2;
            while(arr.get(x) < arr.get(par)){
               int temp = arr.get(0);
               arr.set(0 , arr.get(arr.size()-1));
               arr.set(arr.size()-1,temp);
               arr.remove(arr.size()-1);
               heapify(0);
               return data;
            }
        }
        public boolean isEmpty(){
            return arr.size() == 0;
        }
        public void heapify(int i){
            int left = 2*i+1;
            int right = 2*i+2;
            int minidx = i;

            if(left <arr.size() && arr.get(minidx) > arr.get(left)){
                minidx = left;
            }
            if(right < arr.size() && arr.get(minidx) > arr.get(right)){
                arr.get(right);
            }
            if(minidx != i){
                //swap
                int temp = arr.get(i);
                arr.set(i,arr.get(minidx));
                arr.set(minidx,temp);
                heapify(minidx);

            }
        }
    }
    public static void main(String args[]){
        heap h = new heap();
        h.add(3);
        h.add(4);
        h.add(1);
        h.add(5);
        while(!h.isEmpty()){
            System.out.println(h.peek());
            h.remove();
        }
    }
}