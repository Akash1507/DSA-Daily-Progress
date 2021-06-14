import java.io.*;
import java.util.*;


public class HashMap {
    class Node {
        String key;
        int value;
        Node(String key, int value){
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
    private int getBucketIndex(String key){
        return Math.abs(key.hashCode())%(bucket.length);
    }

    private int searchInBucket(String key,int bi){
        int di = 0;
        for(Node nn:bucket[bi]){
            if(nn.key ==key){
                return di;
            }
            di++;
        }

        return -1;
    }

    // add key value to hashmap
    public void put(String key, int value){
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
    public int get(String key){
        int bi = getBucketIndex(key);
        int di = searchInBucket(key, bi);
        if(di == -1) return -1;
        else return bucket[bi].get(di).value;
    }

    // remove key
    public int remove(String key){
        int bi = getBucketIndex(key);
        int di = searchInBucket(key, bi);
        if(di == -1){
            return -1;
        }
        else{
            size--;
            return bucket[bi].remove(di).value; 
        }
    }

    // keys set
    public ArrayList<String> keySet(){
        ArrayList<String> keys = new ArrayList<>();
        for(int i = 0;i < bucket.length;i++){
            for(Node nn:bucket[i]){
                keys.add(nn.key);
            }
        }
        return keys;
    }

    // display hashmap
    public void display(){
        ArrayList<String> keys = new ArrayList<>();
        for(int i = 0;i < bucket.length;i++){
            for(Node nn:bucket[i]){
                System.out.println(nn.key + " : "+ nn.value);
            }
        }
        System.out.println();
    }

    // contains Key
    public boolean containsKey(String key){
        int bi = getBucketIndex(key);
        int di = searchInBucket(key, bi);
        return di == -1 ? false:true;
    }

    // size of hashmap
    public int size(){
        return this.size;
    }
}