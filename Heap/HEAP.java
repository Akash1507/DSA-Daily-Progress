import java.io.*;
import java.util.*;

public class HEAP {

    public static void kSort(int[] arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i=0;
        // 1. add k elements
        while(i<k){
            pq.add(arr[i]);
            i++;
        }
 
        // 2. process remaining elements
        while(i<arr.length){
            pq.add(arr[i]);
            int rem = pq.remove();
            System.out.println(rem);
            i++;
        }
 
        // 3. print values
        while(pq.size()>0){
            int rem = pq.remove();
            System.out.println(rem);
        }
    }

   public static void kLargest(int[] arr,int k){
       PriorityQueue<Integer> pq = new PriorityQueue<>();
       int i=0;
       // 1. add k elements
       while(i<k){
           pq.add(arr[i]);
           i++;
       }

       // 2. process remaining elements
       while(i<arr.length){
           int rem = pq.peek();
           if(arr[i]>rem){
               pq.remove();
               pq.add(arr[i]);
           }
           i++;
       }

       // 3. print values
       while(pq.size()>0){
           int rem = pq.remove();
           System.out.println(rem);
           k--;
       }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int[] arr = new int[n];

      for (int i = 0; i < n; i++) {
         arr[i] = Integer.parseInt(br.readLine());
      }

      int k = Integer.parseInt(br.readLine());
    //   kLargest(arr,k);
      kSort(arr,k);
    }

}