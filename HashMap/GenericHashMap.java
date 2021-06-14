import java.io.*;
import java.util.*;

public class Main {

  public static class HashMap<K, V> {
    private class Node {
        K key;
        V value;
        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] bucket;
    private int size;

    private void init(int cap){
        bucket = new LinkedList[cap];
        size = 0;
        for(int i=0;i<bucket.length;i++){
            bucket[i] = new LinkedList<>();
        }
        size = 0;
    }

    HashMap(){
        init(4); 
    }

    HashMap(int cap){
        init(cap);
    }


    // Methods

    /*
        Put - Takes a key and value and set that value to that key
    */
    private int getBucketIndex(K key){
        return Math.abs(key.hashCode())%(bucket.length);
    }

    private int searchInBucket(K key,int bi){
        int di = 0;
        for(Node nn:bucket[bi]){
            if(nn.key.equals(key)==true){
                return di;
            }
            di++;
        }

        return -1;
    }

    // add key value to GenericHashMap
    public void put(K key, V value){
        // bi gives index in array
        int bi = getBucketIndex(key);
        // di id data index
        int di = searchInBucket(key,bi);
        if (di == -1){
            bucket[bi].addLast(new Node(key,value));
            size++;
        }
        else{
            bucket[bi].get(di).value = value;
        }

        // N - total no of nodes
        // n - bucket length

        int N = size;
        int n = bucket.length;
        double lambda = N*1.0/n;
        if(lambda>2.0){
            rehash();
        }
    }

    private void rehash(){
        LinkedList<Node>[] b = bucket;
        init(bucket.length*2);
        for(int i=0;i<b.length;i++){
            for(Node nn:b[i]){
                put(nn.key,nn.value);
            }
        }
    }

    // get vlaue on key 
    public V get(K key){
        int bi = getBucketIndex(key);
        int di = searchInBucket(key, bi);
        if(di == -1) return null;
        else return bucket[bi].get(di).value;
    }

    // remove key
    public V remove(K key){
        int bi = getBucketIndex(key);
        int di = searchInBucket(key, bi);
        if(di == -1){
            return null;
        }
        else{
            size--;
            return bucket[bi].remove(di).value; 
        }
    }

    // keys set
    public ArrayList<K> keyset(){
        ArrayList<K> keys = new ArrayList<>();
        for(int i = 0;i < bucket.length;i++){
            for(Node nn:bucket[i]){
                keys.add(nn.key);
            }
        }
        return keys;
    }

    // display GenericHashMap
    public void display() {
        System.out.println("Display Begins");
        for (int bi = 0; bi < bucket.length; bi++) {
          System.out.print("Bucket" + bi + " ");
          for (Node node : bucket[bi]) {
            System.out.print( node.key + "@" + node.value + " ");
          }
          System.out.println(".");
        }
        System.out.println("Display Ends");
    }

    // contains Key
    public boolean containsKey(K key){
        int bi = getBucketIndex(key);
        int di = searchInBucket(key, bi);
        return di == -1 ? false:true;
    }

    // size of GenericHashMap
    public int size(){
        return this.size;
    }
}

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    HashMap<String, Integer> map = new HashMap();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("put")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        Integer val = Integer.parseInt(parts[2]);
        map.put(key, val);
      } else if (str.startsWith("get")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.get(key));
      } else if (str.startsWith("containsKey")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.containsKey(key));
      } else if (str.startsWith("remove")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.remove(key));
      } else if (str.startsWith("size")) {
        System.out.println(map.size());
      } else if (str.startsWith("keyset")) {
        System.out.println(map.keyset());
      } else if (str.startsWith("display")) {
        map.display();
      }
      str = br.readLine();
    }
  }
}