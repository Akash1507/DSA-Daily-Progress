import java.util.*;

class priorityq {
    private ArrayList<Integer> data;

    public priorityq() {
        data = new ArrayList<>();
    }

    private void swap(int idx , int pi){
        int temp = data.get(idx);
        data.set(idx,data.get(pi));
        data.set(pi,temp);
    }

    private void upheapify(int idx){
        if(idx == 0){
            return;
        }
        int pi = (idx-1)/2;
         // change sign > < for min max
        if(data.get(idx) < data.get(pi)){
            swap(idx,pi);
            upheapify(pi);
        }
    }

    public void add(int val){
        data.add(val);
        upheapify(data.size()-1);
    }

    private void downheapify(int idx){
        int minIdx = idx;

        int li = (2*idx)+1;
        int ri = (2*idx)+2;
        // change sign > < for min max
        if(li < data.size() &&  data.get(minIdx) > data.get(li)){
            minIdx = li;
        }
        if(ri < data.size() &&  data.get(minIdx) > data.get(ri)){
            minIdx = ri;
        }
        
        if(minIdx != idx){
            swap(idx,minIdx);
            downheapify(minIdx);
        }
    }

    public int remove() {
        if(data.size()==0){
            System.out.println("Queue underflow");
            return -1;
        }
        swap(0,data.size()-1);
        int val = data.remove(data.size()-1);

        downheapify(0);
        return val; 
    }

    public int peek(){
        if(data.size()==0){
            System.out.println("Queue underflow");
            return -1;
        }
        return data.get(0);
    }

    public int size(){
        return data.size();
    }

    public void display() {
        System.out.println(data);
    }
}

public class PQ {

    public static void func() {
        priorityq pq = new priorityq();
        pq.add(15);
        pq.add(25);
        pq.add(15);
        pq.add(10);
        pq.add(15);
        pq.add(5);
        pq.add(100);

        while(pq.size()>0){
            int val = pq.peek();
            pq.remove();
            System.out.println(val);
        }
        
    }
    public static void main(String[] args) {
        func();
    }
}