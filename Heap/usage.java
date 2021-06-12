import java.util.*;

public class usage {

    public static void demo(){
        // default it is min priority
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(20);
        pq.add(9);
        System.out.println("peek : "+pq.peek());
        pq.add(7);
        pq.add(11);
        System.out.println("peek : "+pq.peek());
        while(pq.size()>0){
            int rem = pq.remove();
            System.out.println(rem);
        }

        System.out.println("Max Heap");

        // default it is max priority
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        pq1.add(10);
        pq1.add(20);
        pq1.add(9);
        System.out.println("peek : "+pq1.peek());
        pq1.add(7);
        pq1.add(11);
        System.out.println("peek : "+pq1.peek());
        while(pq1.size()>0){
            int rem = pq1.remove();
            System.out.println(rem);
        }
    }


    public static void main(String[] args) {
        demo();
    }
    
}