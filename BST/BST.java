import java.io.*;
import java.util.*;

public class BST {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

public static int size(Node node) {
    if(node==null)
    return 0;
  return 1+size(node.left)+size(node.right);
}

public static int sum(Node node) {
    if(node==null)
    return 0;
  return node.data+sum(node.left)+sum(node.right);
}

public static int max(Node node) {
    if(node==null)
    return Integer.MIN_VALUE;
  return Math.max(node.data,Math.max(max(node.left),max(node.right)));
}

public static int min(Node node) {
    if(node==null)
    return Integer.MAX_VALUE;
  return Math.min(node.data,Math.min(min(node.left),min(node.right)));
}

public static boolean find(Node node, int data){
  if(node == null)
  return false;
  return data == node.data || find(node.left,data) || find(node.right,data);
} 

public static void printTarget(Node node, Node root,int target){
    if(node == null) return;
    int n1 = node.data;
    int n2 = target - n1;
    
    printTarget(node.left,root,target);
    if(n1<n2 && find(root,n2) == true){
        System.out.println(n1 +" "+n2);
    }
    printTarget(node.right,root,target);
}

public static void inFiller(Node node, ArrayList<Integer> list){
    if( node == null) return;
    inFiller(node.left, list);
    list.add(node.data);
    inFiller(node.right, list);
}

public static void printTarget1(Node node,int target){
    ArrayList<Integer> list = new ArrayList<>();
    inFiller(node,list);

    int left = 0;
    int right = list.size() - 1;

    while(left < right){
        int n1 = list.get(left);
        int n2 = list.get(right);
        if(n1+n2 > target){
            right--;
        }
        else if(n1+n2 < target){
            left++;
        }
        else{
          System.out.println(n1+" "+n2);
          left++;
          right--;  
        }
    }
    
}

public static int inOrderIter(Stack<Pair> st){
    while(st.size()>0){
        Pair p = st.peek();
        if(p.state==0){
            if(p.node.left != null)
            st.push(new Pair(p.node.left,0));
            p.state++;
        }
        else if(p.state == 1){
            if(p.node.right != null)
            st.push(new Pair(p.node.right,0));
            p.state++;
            return p.node.data;
        }
        else{
            st.pop();
        }
    }
    return -1;
}

public static int revInOrderIter(Stack<Pair> st){
    while(st.size()>0){
        Pair p = st.peek();
        if(p.state==0){
            if(p.node.right != null)
            st.push(new Pair(p.node.right,0));
            p.state++;
        }
        else if(p.state == 1){
            if(p.node.left != null)
            st.push(new Pair(p.node.left,0));
            p.state++;
            return p.node.data;
        }
        else{
            st.pop();
        }
    }
    return -1;
}


public static void printTarget2(Node node,int target){
    Stack<Pair> ls = new Stack<>();
    Stack<Pair> rs = new Stack<>();
    ls.push(new Pair(node,0));
    rs.push(new Pair(node,0));
    int left = inOrderIter(ls);
    int right = revInOrderIter(rs);

    while(left < right){
        int sum = left + right;
        if(sum > target){
            right = revInOrderIter(rs);
        }
        else if(sum < target){
            left = inOrderIter(ls);
        }
        else{
          System.out.println(left+" "+right);
          left = inOrderIter(ls);
          right = revInOrderIter(rs); 
        }
    }
}

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    // write your code here
    printTarget2(root,data);
  }

}