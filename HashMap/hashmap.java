import java.util.HashMap;

public class hashmap {

    public static void hfc(String str) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c:str.toCharArray()){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }

        char c = 'a';
        int max = 0;
        for(char ch :map.keySet()){
            if(map.get(ch)>max){
                max = map.get(ch);
                c = ch; 
            }
        }
        System.out.println(c);
    }

    public static void printCommonElement1(int[] arr1,int[] arr2) {
        HashMap<Integer,Integer> mp = new HashMap<>();
        for(int k:arr1){
            mp.put(k, 1);
        }
        for(int k:arr2){
            if(mp.containsKey(k)){
                System.out.println(k);
                mp.remove(k);
            }
        }
    }

    public static void printCommonElement2(int[] arr1,int[] arr2) {
        HashMap<Integer,Integer> mp = new HashMap<>();
        for(int k:arr1){
            if(mp.containsKey(k))
                mp.put(k, mp.get(k)+1);
            else
                mp.put(k,1);
        }
        for(int k:arr2){
            if(mp.containsKey(k))
            {
                if(mp.get(k)!=null && mp.get(k) > 0)
                    System.out.println(k);
            mp.put(k, mp.get(k)-1);
            }
        }
    }

    public static void lcs(int[] arr){
        // 1. make hashmap o presence
        HashMap<Integer,Boolean> map = new HashMap<>();
        for(int key:arr){
            map.put(key,true);
        }
        // make starting point of sequence
        for(int key:arr){
            if(map.containsKey(key-1) == true){
                map.put(key,false);
            }
        }

        // 3. get length and starting point
        int maxLength = 0;
        int starting = 0;
        for(int key:arr){
             if(map.get(key)==true){
                 int len = 1;
                 int st = key;

                 while(map.containsKey(key+1)==true){
                     len++;
                     key++;
                 }

                 if(maxLength<len){
                     maxLength = len;
                     starting = st;
                 }

                 map.put(key, false);
             }
        }

        for(int i = 0;i<maxLength;i++){
            System.out.println(starting);
            starting++;
        }

    }

    public static void func() {
        HashMap<Character,Integer> mp = new HashMap<>();
        mp.put('a',1);
        mp.put('b',2);
        mp.put('c',3);
        mp.put('d',4);
        mp.put('e',5);
        mp.put('f',6);
        mp.put('g',7);
        // System.out.println(mp);
        // System.out.println(mp.get('a'));
        // System.out.println(mp.containsKey('e'));
        // System.out.println(mp.containsKey('z'));
        // System.out.println(mp.keySet());
        // System.out.println(mp.remove('a'));
        // String x = "accbaamzodwjdsasdhdchcdhcdbhszzoreoiurioufncsnc";
        // hfc(x);
        int a[] = {1,2,3,4,5,6};
        int b[] = {2,4,5,8,9};
        int c[] = {1,10,3,12,4,22,2,11,21,5,24,25};
        // printCommonElement2(a,b);
        lcs(c);
        
    }

    public static void main(String[] args){
        func();
    }
}