import java.security.interfaces.EdECKey;
import java.util.*;

public class Graphs {
    static class Edge {
        int src;// source
        int dest;// destinession
        int wt;// weight

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static void createGraph(ArrayList<Edge> Graphs[]) {

        for (int i = 0; i < Graphs.length; i++) {
            Graphs[i] = new ArrayList<>();
        }

        // vertexes 0
        Graphs[0].add(new Edge(0, 1, 5));

        // vertexes 1

        Graphs[1].add(new Edge(1, 0, 5));
        Graphs[1].add(new Edge(1, 2, 1));
        Graphs[1].add(new Edge(1, 3, 3));

        // vertexes 2
        Graphs[2].add(new Edge(2, 1, 1));
        Graphs[2].add(new Edge(2, 3, 1));
        Graphs[2].add(new Edge(2, 4, 4));

        // vertexes 3

        Graphs[3].add(new Edge(3, 1, 3));
        Graphs[3].add(new Edge(3, 2, 1));

        // verteses 4

        Graphs[4].add(new Edge(4, 2, 1));
    }

    public static void bfsdivided(ArrayList<Edge>[] Graphs) {
        Boolean visit[] = new Boolean[Graphs.length];

        for (int i = 0; i < Graphs.length; i++) {
            if (!visit[i]) {
                BFSUtil(Graphs, visit);
            }
        }
    }

    public static void BFSUtil(ArrayList<Edge>[] Graphs, boolean visit[]) {// o(n) O(V+E) vertexe and edge
        Queue<Integer> q = new LinkedList<>();
        q.add(0);// source

        while (!q.isEmpty()) {

            int curr = q.remove();

            // visite 3 step
            if (!visit[curr]) {
                // 1 st step
                System.out.print(curr + " ");
                visit[curr] = true;// step 2
                // find neighbours
                for (int i = 0; i < Graphs[curr].size(); i++) {
                    Edge E = Graphs[curr].get(i);
                    q.add(E.dest);
                }
            }
        }
    }

    public static void BFS(ArrayList<Edge>[] Graphs) {// o(n) O(V+E) vertexe and edge
        Queue<Integer> q = new LinkedList<>();
        Boolean visit[] = new Boolean[Graphs.length];
        q.add(0);// source

        while (!q.isEmpty()) {

            int curr = q.remove();

            // visite 3 step
            if (!visit[curr]) {
                // 1 st step
                System.out.print(curr + " ");
                visit[curr] = true;// step 2
                // find neighbours
                for (int i = 0; i < Graphs[curr].size(); i++) {
                    Edge E = Graphs[curr].get(i);
                    q.add(E.dest);
                }
            }
        }
    }

    public static void DFSDivided(ArrayList<Edge>[] Graphs) {
        boolean visit[] = new boolean[Graphs.length];
        for (int i = 0; i < Graphs.length; i++) {
            DFSUtil(Graphs, i, visit);
        }
    }

    public static void DFSUtil(ArrayList<Edge>[] Graphs, int curr, boolean visit[]) { // o(v+e)
        // visit
        System.out.print(curr + "");
        visit[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (!visit[e.dests]) {
                DFSUtil(Graphs, e.dest, visit);
            }
        }
    }

    public static void DFS(ArrayList<Edge>[] Graphs, int curr, boolean visit[]) { // o(v+e)
        // visit
        System.out.print(curr + "");
        visit[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (!visit[e.dests]) {
                DFS(Graphs, e.dest, visit);
            }
        }
    }

    // hash path source to destinatiom
    public static boolean hashPath(ArrayList<Edge>[] Graphs, int src, int dest, boolean visit[]) {// O(v+e)

        if (src == dest) {
            return true;
        }
        visit[src] = true;

        for (int i = 0; i < Graphs[src].size(); i++) {
            Edge e = Graphs[src].get(i);
            // e,dest = neighbour
            if (!visit[e.dest] && hashPath(Graphs, e.dest, dest, visit)) {
                return true;
            }
        }
        return false;
    }

    // tarjans algorith use in find bredge in graph TC O(V+E)
    public static void dfs(ArrayList<Edge> Graphs[], int curr, int parent, int dt[], int low[], boolean visit[],
            int time) {// discovery time = dt,dtlow = lowe discovery time
        visit[curr] = true;
        dt[curr] = low[curr] = ++time;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);// e.src -----to e.dest
            int neighbour = e.dest;
            // case 1
            if (neighbour == parent) {
                continue;
            } else if (!visit[neighbour]) {// case 2
                dfs(Graphs, neighbour, curr, dt, low, visit, time);
                low[curr] = Math.min(low[curr], low[neighbour]);
                // bridge conditiom
                if (dt[curr] < low[neighbour]) {
                    System.out.println("Bridge :" + curr + "------" + neighbour);
                }
            } // acse 3
            else {
                low[curr] = Math.min(low[curr], dt[curr]);
            }
        }
    }

    public static void tarjansBridge(ArrayList<Edge> Graphs[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean visite[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visite[i]) {
                dfs(Graphs, i, -1, dt, low, visite, time);
            }
        }
    }

    // Articulterlation point using dfs TC(V+e)
    public static void Dfs(ArrayList<Edge> Graphs[], int curr, int parent, int dt[], int low[], int time,
            boolean visit[], boolean ap[]) {// TC (V+E)
        visit[curr] = true;
        dt[curr] = low[curr] = time++;
        int children = 0;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            int neighbour = e.dest;

            if (parent == neighbour) {
                continue;
            } else if (visit[neighbour]) {
                low[curr] = Math.min(low[curr], dt[neighbour]);
            } else {
                Dfs(Graphs, neighbour, curr, dt, low, time, visit, ap);
                low[curr] = Math.min(low[curr], low[neighbour]);
                // check AP
                if (parent != -1 && dt[curr] <= low[neighbour]) {
                    ap[curr] = true;
                }
                children++;
            }

        }

        if (parent == -1 && children > 1) {
            ap[curr] = true;
        }
    }

    public static void getAp(ArrayList<Edge> Graphs[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean visit[] = new boolean[V];
        boolean ap[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visit[i]) {
                Dfs(Graphs, i, -1, dt, low, time, visit, ap);
            }
        }
    }

    // kosaraju algoriyhm strongly connectes graph TCO(v+e) dfs based directed
    //
    public static void dfs(ArrayList<Edge> Graphs[], int curr, boolean visit[]) {
        visit[curr] = true;
        System.out.println(curr + "");

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (!visit[e.dest]) {
                dfs(Graphs, e.dest, visit);
            }
        }

        // print all ap
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println("AP :" + i);
            }
        }
    }

    public static void topSort(ArrayList<Edge> Graphs[], int curr, boolean visit[], Stack<Integer> s) {
        visit[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (!visit[e.dest]) {
                topSort(Graphs, e.dest, visit, s);
            }
        }

        s.push(curr);
    }

    public static void kosarajuAlgo(ArrayList<Edge> Graphs[], int V) { // O(v+e)
        // step 1
        Stack<Integer> s = new Stack<>();
        boolean visit[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visit[i]) {
                topSort(Graphs, i, visit, s);
            }
        }

        // step 2 transpose graph reverse o(V+e)
        ArrayList<Edge> transpose[] = new ArrayList[V];
        for (int i = 0; i < Graphs.length; i++) {
            visit[i] = false;
            transpose[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < Graphs[i].size(); i++) {
                Edge e = Graphs[i].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src));// reverse edge
            }
        }

        // step 3 O(v+e)
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!visit[curr]) {
                System.out.print("SCC-->");
                dfs(transpose, curr, visit);// src
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int v = 5;
        ArrayList<Edge> Graphs[] = new ArrayList[v];// array of arraylist
        createGraph(Graphs);
        BFS(Graphs);
        DFS(Graphs, v, new boolean[v]);
        hashPath(Graphs, 0, 5, new boolean[v]);
        tarjansBridge(Graphs, v);

        /*
         * for (int i = 0; i < v; i++) {
         * Graphs[i] = new ArrayList<>();
         * }
         * 
         * // vertexes 0
         * Graphs[0].add(new Edge(0, 1, 5));
         * 
         * // vertexes 1
         * 
         * Graphs[1].add(new Edge(1, 0, 5));
         * Graphs[1].add(new Edge(1, 2, 1));
         * Graphs[1].add(new Edge(1, 3, 3));
         * 
         * // vertexes 2
         * Graphs[2].add(new Edge(2, 1, 1));
         * Graphs[2].add(new Edge(2, 3, 1));
         * Graphs[2].add(new Edge(2, 4, 4));
         * 
         * // vertexes 3
         * 
         * Graphs[3].add(new Edge(3, 1, 3));
         * Graphs[3].add(new Edge(3, 2, 1));
         * 
         * // verteses 4
         * 
         * Graphs[4].add(new Edge(4, 2, 1));
         * 
         * // s neighbours
         * 
         * for (int i = 0; i < Graphs[2].size(); i++) {
         * Edge e = Graphs[2].get(i); // src dest wt
         * System.out.println(e.dest);
         * ;
         * }
         */

    }
}
