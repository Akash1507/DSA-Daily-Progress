import java.io.*;

public class creation {
  public static class Node {
    int data;
    Node next;
    public Node(){
        
    }

     public Node(int data){
        this.data = data;
        this.next = null;
    }
  }

  public static class LinkedList {
    Node head;
    Node tail;
    int size;

    void addLast(int val) {
        
        if(this.size==0){
            Node t = new Node(val);
            head = tail = t;
            this.size++;
        }
        else{
        Node t = new Node(val);
        this.tail.next = t;
        this.tail = t;
        this.size++; 
        }
    }

    public void display(){
        Node t = this.head;
      while(t != null){
          System.out.print(t.data+" ");
          t = t.next;
      }
      System.out.println();
    }

    public void removeFirst(){
        if(this.size == 0){
            System.out.println("List is empty");
        }
        else if(this.size == 1){
            this.head = this.tail = null;
            this.size--;
        }
        else{
            Node t = this.head.next;
            this.head = t;
            this.size--;
        }
      }
      public int size(){
          return this.size;
      }
      public int getFirst() {
        if (this.size() == 0) {
          System.out.println("List is empty");
          return -1;
        }
        else {
          return this.head.data;
        }
      }
  
      public int getLast() {
        if (this.size() == 0) {
          System.out.println("List is empty");
          return -1;
        }
        else {
          return this.tail.data;
        }
      }
  
      public int getAt(int idx) {
        if (idx == 0) {
          return getFirst();
        }
        else if (idx == this.size - 1) {
          return getLast();
        }
        else if (idx >= 1 && idx < this.size - 1) {
          Node t = this.head;
          while (t.next != null && idx == 1) {
            t = t.next;
            idx--;
          }
          return t.data;
        }
        System.out.println("Invalid arguments");
        return -1;
      }

      public void addFirst(int val) {
        if(this.size==0){
            Node t = new Node();
            t.data = val;
            t.next = null;
            this.head = this.tail = t;
            this.size++;
        }
        else{
            Node t = new Node();
            t.data = val;
            t.next = this.head;
            this.head = t;
            this.size++;
        }
      }








}


  public static void testList(LinkedList list) {
    for (Node temp = list.head; temp != null; temp = temp.next) {
      System.out.println(temp.data);
    }
    System.out.println(list.size);

    if (list.size > 0) {
      System.out.println(list.tail.data);
    } 
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    LinkedList list = new LinkedList();

    String str = br.readLine();
    while(str.equals("quit") == false){
      if(str.startsWith("addLast")){
        int val = Integer.parseInt(str.split(" ")[1]);
        list.addLast(val);
      } 
      str = br.readLine();
    }

    testList(list);
  }
}