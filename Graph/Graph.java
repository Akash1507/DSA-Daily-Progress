import java.util.*;

public class Graph {
    
    static class Edge {
        int src;
        int nbr;
        int wt;

        public Edge(int src,int nbr,int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph,int src,int nbr,int wt) {
        graph[src].add(new Edge(src,nbr,wt));
        graph[nbr].add(new Edge(nbr,src,wt));
    }

    public static boolean haspath(ArrayList<Edge>[] graph,int sr,int dst, int[] visited){
        if(sr == dst)
        return true;
        visited[sr] = 1;
        for(Edge e:graph[sr]){
            int nbr = e.nbr;
            if(visited[nbr]==0)
            {
                boolean res = haspath(graph, nbr, dst, visited);
                if(res == true)
                return true;
            }
        }


        return false;

    }

    public static void display(ArrayList<Edge>[] graph) {
        for(int i=0;i<graph.length;i++){
            System.out.print("["+i+"] ->");
            for(int j = 0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                System.out.print("["+e.src+"-"+e.nbr+"@"+e.wt+"]");
            }
            System.out.println();
        }
        
    }

    public static void printAllPaths(ArrayList<Edge>[] graph,int src,int dst,boolean[] vis,String psf,int wsf){
        if(src == dst)
        {
            psf+=src;
            System.out.println(psf);
            return;
        }
        vis[src] = true;
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(vis[nbr]==false)
            {
                printAllPaths(graph, nbr, dst, vis, psf+src, wsf);
            }
        }
        vis[src] = false;
    }


    public static void func() {
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,1,10);
        addEdge(graph,0,3,40);
        addEdge(graph,1,2,10);
        addEdge(graph,2,3,10);
        addEdge(graph,3,4,2);
        addEdge(graph,4,5,3);
        addEdge(graph,4,6,8);
        addEdge(graph,5,6,3);

        display(graph);
        boolean[] visited = new boolean[n];
        // System.out.println(haspath(graph,0,6,visited));
        printAllPaths(graph,0,6,visited,"",0);
    }

    public static void main(String[] args) {
        func();
    }
}