import java.util.*;


public class Main {
    public static Scanner sc;
    static{
        sc = new Scanner(System.in);
    }

    static class GT {
        static class Node{
            int data;
            ArrayList<Node> children;

            Node(int data){
                this.data = data;
                this.children = new ArrayList<>();
            }
        }

        static Node creation(Integer[] arr){
            Stack<Node> st = new Stack<>();
            Node root = null;
            for(int i=0;i<arr.length;i++){
                if(root == null){
                    root = new Node(arr[i]);
                    st.push(root);
                }
                else {
                    if(arr[i] == null){
                        st.pop();
                    }
                    else{
                        Node nn = new Node(arr[i]);
                        st.peek().children.add(nn);
                        st.push(nn);
                    }
                }
            }
            return root;
        }

        static void display(Node root){
            System.out.print("["+root.data+"]"+" --> ");
            for(Node child:root.children){
                System.out.print(child.data+" ");
            }
            System.out.println(".");
            for(Node child:root.children){
                display(child);
            }
        }

        static int size(Node node){
            int s = 1;
            for(Node child:node.children){
                s+=size(child);
            }
            return s;
        }  

        static int max(Node node){
            int max = node.data;
            for(Node child:node.children){
                max = Math.max(max,max(child));
            }
            return max;
        }

        static int min(Node node){
            int min = node.data;
            for(Node child:node.children){
                min = Math.min(min,min(child));
            }
            return min;
        }

        static int height(Node node){
            int cheight = 0;
            for(Node child:node.children){
                cheight = Math.max(cheight,height(child));
            }

            return cheight+1;
        }

        static void traversal(Node node){
            System.out.println("Node Pre "+node.data);

            for(Node child:node.children){
                System.out.println("Edge Pre "+node.data+ "--"+child.data);
                traversal(child);
                System.out.println("Edge Post "+node.data+ "--"+child.data);
            }
            System.out.println("Node Post "+node.data);
        }

        static void levelOrder(Node node){
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);
            while(q.size()>0){
                Node nn = q.remove();
                System.out.println(nn.data);
                for(Node child:nn.children){
                    q.add(child);
                }
            }
        }

        static void levelOrderWise1(Node node){
            Queue<Node> mq = new ArrayDeque<>();
            Queue<Node> cq = new ArrayDeque<>();
            mq.add(node);

            while(mq.size()>0){
                Node nn = mq.remove();
                System.out.print(nn.data+" ");
                for(Node child:nn.children){
                    cq.add(child);
                }
                if(mq.size()==0)
                 {   
                    Queue<Node> temp = mq;
                    mq = cq;
                    cq = temp;
                    System.out.println();
                 }
            }

        }

        static void levelOrderWise2(Node node){
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            q.add(null);
            while(q.size()>0){
                Node nn = q.remove();
                if(nn == null){
                    System.out.println();
                    if(q.size()>0){
                        q.add(null);
                    }
                }
                else{
                    System.out.print(nn.data+" ");
                    for(Node child:nn.children){
                        q.add(child);
                    }
                }
            }
        }

        static void levelOrderWise3(Node node){
            Queue<Node> q = new ArrayDeque<>();
            q.add(node);
            while(q.size()>0){
                int size = q.size();
                while(size--> 0){
                    Node nn = q.remove();
                    System.out.print(nn.data+" ");
                    for(Node child:nn.children){
                        q.add(child);
                    }
                }
                System.out.println();
            }
        }

        static void zigzag(Node node){

            Stack<Node> ms = new Stack<>();
            Stack<Node> cs = new Stack<>();
            ms.push(node);
            int level = 0;

            while(ms.size()>0){
                Node nn = ms.pop();
                System.out.print(nn.data+" ");
                if(level%2==0){
                    for(int i=0;i<nn.children.size();i++){
                        cs.push(nn.children.get(i));
                    }
                } else{
                    for(int i=nn.children.size()-1;i>=0;i--){
                        cs.push(nn.children.get(i));
                    } 
                }
                
                if(ms.size()==0){
                    Stack<Node> temp = cs;
                    cs = ms;
                    ms = temp;
                    level++;
                    System.out.println();
                }
                
            }
        }

        static void reverse(ArrayList<Node> arr){
            int i = 0;
            int j = arr.size()-1;
            while(i<j){
                Node temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j,temp);
                i++;
                j--;
            }
        }

        static void mirror(Node node){
            for(Node child:node.children){
                mirror(child);
            }
            reverse(node.children);
            
        }

        static void remove(Node node){

            for(int i=node.children.size()-1;i>=0;i--){
                if(node.children.get(i).children.size()==0){
                    node.children.remove(i);
                }
            }
            for(Node child:node.children){
                remove(child);
            }
        }

        static void removeOptimised(Node node){
            ArrayList<Node> al = new ArrayList<>();
            for(Node child:node.children){
                if(child.children.size()!=0){
                    al.add(child);
                }
            }
            node.children = al;
            for(Node child:node.children){
                removeOptimised(child);
            }
        }

        static boolean find(Node node,int data){
            if(node.data == data){
                return true;
            }
            boolean res = false;
            for(Node child:node.children){
                res = find(child,data);
                if(res == true) return true;
            }
            return res;
        } 

        static ArrayList<Integer> nodeToRootPath(Node node,int data){
            if(node.data == data){
                ArrayList<Integer> res = new ArrayList<>();
                res.add(node.data);
                return res;
            }
            for(Node child:node.children){
                ArrayList<Integer> rres = nodeToRootPath(child,data);
                if(rres.size()>0){
                    rres.add(node.data);
                    return rres;
                }
            }
            return new ArrayList<>();
        }

        public static void solve() {
            Integer[] arr = {10,20,50,null,60,null,null,30,70,null,80,110,null,
                            120,null,null,90,null,null,40,100,null,null,null};
            Node root = creation(arr);
            // display(root);
            // System.out.println(size(root));
            // System.out.println(max(root));
            // System.out.println(min(root));
            // System.out.println(height(root));
            // traversal(root);
            // levelOrder(root);
            // levelOrderWise2(root);
            // levelOrderWise3(root);
            // zigzag(root);
            // display(root);
            // mirror(root);
            // display(root);
            // display(root);
            // removeOptimised(root);
            // display(root);
            System.out.println(find(root,130));
            System.out.println(nodeToRootPath(root,110));
        }

    }

    public static void main(String[] args){
        GT.solve();
    }
}