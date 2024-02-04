import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node;
        int path;// edge (distance)

        public Pair(int node, int path) {
            this.node = node;
            this.path = path;
        }

        @Override
        public int CompareTo(Pair p2) {
            return this.path - p2.path;// path base sorting for my pairs ascending base
        }
    }

    public static void Dijkstra(ArrayList<Edge> Graph[], int src) {
        int dist[] = new int[Graph.length];

        for (int i = 0; i < Graph.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean visted[] = new boolean[Graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.remove();
            if (!visted[current.node]) {
                visted[current.node] = true;

                for (int i = 0; i < Graph[current.node].size(); i++) {
                    Edge e = Graph[current.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    // important update

                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;// update distance new val
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

    public static void bellmanFord(ArrayList<Edge> Graph[], int src) {
        int distance[] = new int[Graph.length];

        for (int i = 0; i < Graph.length; i++) {
            if (i != src) {
                distance[i] = Integer.MAX_VALUE;
            }
        }

        int V = Graph.length;

        for (int i = 0; i < V - 1; i++) {// all node
            for (int j = 0; j < Graph.length; j++) {// all vert find this loop edges calculate
                for (int k = 0; k < Graph[j].size(); k++) {// neg h b our
                    Edge e = Graph[j].get(k);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {// java me infinity value
                                                                                             // me positive
                        // value add hone pr value negative aa
                        // ja tee ha
                        distance[v] = distance[u] + wt;
                    }
                }
            }
        }

        // detect negative weight cycle

        for (int j = 0; j < Graph.length; j++) {// all vert find this loop edges calculate
            for (int k = 0; k < Graph[j].size(); k++) {// cal edges
                Edge e = Graph[j].get(k);// neg h b our
                // u v wt
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;
                // relaxation / update / new value
                if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {// java me infinity value me
                                                                                         // positive value
                    // add hone pr value negative aa ja tee hai
                    System.out.println("negative wt cycle exist");
                }
            }

        }

        // print
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i] + "");
        }

        System.out.println();
    }

    static class Pairs implements Comparable<Pairs> {
        int v;// vertex
        int cost;

        public Pairs(int v, int c) {
            this.v = v;// node
            this.cost = c;
        }

        @Override
        public int CompareTo(Pairs p2) {
            return this.cost - p2.cost;// ascending based on cost
        }
    }

    public static void PrimsAlgo(ArrayList<Edge> Graph[]) {
        boolean visited[] = new boolean[Graph.length];
        PriorityQueue<Pairs> pq = new PriorityQueue<Pairs>();

        pq.add(new Pairs(0, 0));
        int finalCost = 0;

        while (!pq.isEmpty()) {
            Pairs current = pq.remove();

            if (!visited[current.v]) {
                visited[current.v] = true;
                finalCost += current.cost;

                for (int i = 0; i < Graph[current.v].size(); i++) {
                    Edge e = Graph[current.v].get(i);
                    if (!visited[e.dest]) {
                        pq.add(new Pairs(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("final cost : " + finalCost);
    }

    public static void KruskalAlgorithm(ArrayList<Edge> edges, int V) {
        Collection.sort(edges);
        int mst_cost = 0;
        int count = 0;

        for (int i = 0; i < V - 1; i++) {
            Edge e = edges.get(i);
            int ParentA = find(e.src);
            int ParentB = find(e.dest);

            if (ParentA != ParentB) {
                // no cycle
                union(e.src, e.dest);
                mst_cost += e.wt;
                count++;
            }
        }

        System.out.println("Final Mst Cost : " + mst_cost);
    }

    public static void topSort(ArrayList<Edge> Graphs[], int curr, boolean visit[], Stack<Integer> s) {
        visit[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (!visit[e.dest]) {// neigbour not visited
                topSort(Graphs, e.dest, visit, s);
            }
        }

        s.push(curr);
    }

    public static boolean cycleInDirectGraph(ArrayList<Edge> Graph[], boolean visited[], int curr, int parent) {
        visited[curr] = true;

        for (int i = 0; Graph[curr].size(); i++) {
            Edge e = Graph[curr].get(i);
            if (!visited[e.dest]) {
                if (cycleInDirectGraph(Graph, visited, e.dest, curr)) {
                    return true;
                }
            } else if (visited[e.dest] && e.dest != parent) {
                return true;
            }
        }

        return false;
    }

    public static boolean cycledetectet(ArrayList<Edge> Graphs[]) {
        boolean vis[] = new boolean[Graphs.length];
        for (int i = 0; i < Graphs.length; i++) {
            if (!vis[i]) {
                if (cycleInDirectGraph(Graphs, vis, i, -1)) {
                    return true;
                    // cycle exists in the parts
                }

            }
        }
        return false;
    }

    public static boolean cycleInUndirectedGraph(ArrayList<Edge> graph[], boolean visited[], int current, int parent) {
        visited[current] = true;

        for (int i = 0; i < Graph[current].size(); i++) {
            Edge e = Graph[current].get(i);
            if (visited[e.dest] && e.dest != parent) {
                return true;
            } else if (!visited[e.dest]) {
                if (cycleInUndirectedGraph(graph, visited, e.dest, current)) {
                    return true;
                }
            }
        }
        return false;
    }

    final static int INF = 99999, V = 4;

    void floydWarshall(int graph[][]) {
        int dist[][] = new int[V][V];
        int i, j, k;

        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        printSolution(dist);
    }

    void printSolution(int dist[][]) {
        System.out.println("The Following matrix show the shortest " + "distance between every pair of vertexes");

        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }

            System.out.println();
        }
    }

    // flood fill algorithm
    public static void helper(int[][] image, int strow, int stcol, int color, boolean vis[][], int orignalcolor) {// O(m*n)
        // base case
        if (strow < 0 || stcol < 0 || strow >= image.length || stcol >= image[0].length || vis[strow][stcol]
                || image[strow][stcol] != orignalcolor) {
            return;
        }

        // left
        helper(image, strow, stcol - 1, color, vis, orignalcolor);
        // right
        helper(image, strow, stcol + 1, color, vis, orignalcolor);
        // up
        helper(image, strow - 1, stcol, color, vis, orignalcolor);
        // soen
        helper(image, strow + 1, stcol, color, vis, orignalcolor);
    }

    public static int[][] floodFillAlgo(int[][] image, int strow, int stcol, int color) {
        boolean vis[][] = new boolean[image.length][image[0].length];
        helper(image, strow, stcol, color, vis, image[sc][sc]);
        return image;

    }

    public static void main(String[] args) {
        int graph[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 }, { INF, INF, INF, 0 } };
        Google g = new Google();
        g.floydWarshall(graph);
    }
}