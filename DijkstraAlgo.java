import java.security.interfaces.EdECKey;
import java.util.*;

public class DijkstraAlgo {// greedi based
    public class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void CreateGraph(ArrayList<Edge>[] Graph) {// weigthted graph undirected / directed
        for (int i = 0; i < Graph.length; i++) {
            Graph[i] = new ArrayList<>();
        }

        Graph[0].add(new Edge(0, 1, 2));
        Graph[0].add(new Edge(0, 2, 4));

    }

    static class Pair implements Comparable<Pair> {
        int node;
        int path;

        public Pair(int node, int path) {
            this.node = node;
            this.path = path;
        }

        @Override
        public int CompareTo(Pair p2) {
            return this.path - p2.path;// path base sorting for my pairs
        }
    }

    public static void dijkstra(ArrayList<Edge>[] Graph, int src) {// TC O(V+Elogv) using priority queue
        int dist[] = new int[Graph.length];

        for (int i = 0; i < Graph.length; i++) {
            if (src != i) {
                dist[i] = Integer.MAX_VALUE;// +infinitiy
            }
        }
        boolean visit[] = new boolean[Graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(src, 0));

        // bfs loop
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!visit[curr.n]) {
                visit[curr.n] = true;
                // neighbours
                for (int i = 0; i < Graph[curr.n].size(); i++) {
                    Edge e = Graph[curr.n].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;// updated distance new val
                        pq.add(new Pair(v, dist[v]));
                    }

                }
            }
        }

        // print of all source to vertexes to all shortest path
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + "");
        }

        System.out.println();
    }

    // BEllmon ford algo dp based uses of negativve wt but does not negative
    // weightes cycle
    public static void bellmanFord(ArrayList<Edge>[] Graph, int src) {// T O(V*E)
        int dist[] = new int[Graph.length];
        // intilize
        for (int i = 0; i < dist.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        int V = Graph.length;
        // algo -(V)
        for (int i = 0; i < V - 1; i++) {
            // edges -O(E)
            for (int j = 0; j < Graph.length; j++) {// all vertexex find this loop edges cal
                for (int k = 0; k < Graph[j].size(); k++) {// cal edges
                    Edge e = Graph[j].get(k);// neghbour
                    // u v wt
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    // relaxation / updation / new value
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }

            }
        }

        // print
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + "");
        }

        System.out.println();
    }

    // Prims algo MST Set

    static class Pairs implements Comparable<Pairs> {
        int v;// vertex
        int cost;

        public Pairs(int v, int c) {
            this.v = v;
            this.cost = c;
        }

        @Override
        public int CompareTo(Pairs p2) {
            return this.cost - p2.cost;// assending
        }
    }

    public static void PrimsAlgo(ArrayList<Edge> Graph[]) {
        boolean visit[] = new boolean[Graph.length];
        PriorityQueue<Pairs> pq = new PriorityQueue<>();
        pq.add(new Pairs(0, 0));
        int finalCost = 0; // MST cost/weighted total minimum weighted

        while (!pq.isEmpty()) {
            Pairs curr = pq.remove();
            if (!visit[curr.v]) {
                visit[curr.v] = true;
                finalCost += curr.cost;

                for (int i = 0; i < Graph[curr.v].size(); i++) {
                    Edge e = Graph[curr.v].get(i);
                    pq.add(new Pairs(e.dest, e.wt));
                }
            }
        }

        System.out.println("final(min) cost of Mst =" + finalCost);
    }

    public static void main(String args[]) {
        int V = 6;
        ArrayList<Edge> Graph[] = new ArrayList[V];
        CreateGraph(Graph);

        int src = 0;

        dijkstra(Graph, src);
        bellmanFord(Graph, 0);

    }
}