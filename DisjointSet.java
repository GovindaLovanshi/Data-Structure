import java.util.*;

public class DisjointSet {

    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

        @Override
        public int comaparTo(Edge e2) {
            return this.wt - e2.wt;
        }
    }

    static int n = 7;
    static int par[] = new int[n];
    static int rank[] = new int[n];

    public static void init() {
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    public static int find(int x) {
        if (x == par[x]) {
            return x;
        }

        return par[x] = find(par[x]);
    }

    public static void union(int a, int b) {
        int parA = find(a);
        int parB = find(b);

        if (rank[parA] == rank[parB]) {
            par[parB] = parA;
            rank[parA]++;
        } else if (rank[parA] < rank[parB]) {
            par[parA] = parB;
        } else {
            par[parB] = parA;
        }
    }

    public static void createGraph(ArrayList<Edge> edges) {

        edges.add(new Edge(n, n, n));
    }

    // kruskal algorithm find mst freedy base a TC O(V + ElogE)
    public static void kruskalMst(ArrayList<Edge> edges, int V) {
        Collection.sort(edges);// log E
        int mstcost = 0;
        int count = 0;

        for (int i = 0; count < V - 1; i++) {// O(V)
            e = edges.get(i);
            // src dest wt
            int parA = find(e.src);// sorce = a
            int parB = find(e.dest);// sorce = b
            if (parA != parB) {
                // no cycle detection
                union(e.src, e.dest);
                mstcost += e.wt;
                count++;
            }

        }

        System.out.println(mstcost);
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

    public static void main(String args[]) {
        ArrayList<Edge> edges = new ArrayList<>();
        createGraph(edges);
        int V = 4;
        kruskalMst(edges, V);

        init();
        union(1, 3);
        System.out.println(find(3));
        union(2, 4);
        union(3, 6);
        union(1, 4);
        System.out.println(find(3));
        System.out.println(find(4));
        union(1, 5);
    }
}