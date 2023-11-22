import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graphssecond {
    // cycle detection
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static boolean cycledetecte(ArrayList<Edge> Graphs[]) {
        boolean vis[] = new boolean[Graphs.length];
        for (int i = 0; i < Graphs.length; i++) {
            if (!vis[i]) {
                if (cycleDetectedUtill(Graphs, vis, i, -1)) {
                    return true;
                    // cycle exists in the parts
                }

            }
        }
        return false;
    }

    public static boolean cycleDetectedUtill(ArrayList<Edge> Graphs[], int vis[], int curr, int parent) {
        vis[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            // case 3
            if (!vis[e.dest]) {
                if (cycleDetectedUtill(Graphs, vis, curr, parent)) {
                    return true;
                }
            } // case 1
            else if (vis[e.dest] && e.dest != parent) {
                return true;
            } // else do nothing contnue
        }

        return false;

    }

    // biapartite graph colors algo applied
    public static boolean isBiapartite(ArrayList<Edge>[] Graphs) {// Tc O(V+E) graph doesn t have cycle = biapartite
        // graph = even cyccle odd cycle false
        int coler[] = new int[Graphs.length];
        for (int i = 0; i < coler.length; i++) {
            coler[i] = -1;// no color
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < Graphs.length; i++) {
            if (coler[i] == -1) {
                // bfs
                q.add(i);
                col[i] = 0;// yellow
                while (!q.isEmpty()) {
                    int curr = q.remove();
                    for (int j = 0; j < Graphs[curr].size(); j++) {
                        Edge e = Graphs[curr].get(j);// e.dest
                        // case 1
                        if (coler[e.dest] == -1) {
                            int nextcol = coler[curr] == 0 ? 1 : 0;
                            coler[e.dest] = nextcol;
                            q.add(e.dest);
                        } else if (coler[e.dest] == coler[curr]) {
                            return false;// not bioaprtite
                        }
                    }
                }
            }
        }

        return true;
    }

    // cycle detection in directed graph

    public static boolean isCycle(ArrayList<Edge>[] Graphs) {
        boolean visit[] = new boolean[Graphs.length];
        boolean stack[] = new boolean[Graphs.length];

        for (int i = 0; i < Graphs.length; i++) {
            if (!vis[i]) {
                if (cycleDetectedUtill(Graphs, i, visit, stack)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isCycleUtil(ArrayList<Edge>[] Graphs, int curr, boolean visit[], boolean stack[]) {
        visit[curr] = true;
        stack[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (stack[e.dest]) { // stack ke andar neigh bour hai cycle hai
                // cycle
                return true;
            }

            if (!visit[e.dest] && cycleDetectedUtill(Graphs, e.dest, visit, stack)) {
                return true;
            }
        }

        stack[curr] = false;
        return false;

    }

    // topological sorting acyclic O(v+e) dfs

    public static void topologySort(ArrayList<Edge>[] Graphs) {
        boolean visit[] = new boolean[Graphs.length];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < Graphs.length; i++) {
            if (!visit[i]) {
                topoSortUtll(Graphs, i, visit, s);// modified dfs
            }
        }

        // stack element i remove
        while (!s.isEmpty()) {
            System.out.println(s.pop() + "");
        }
    }

    public static void topoSortUtll(ArrayList<Edge>[] Graphs, int curr, boolean visit[], Stack<Integer> s) {
        visit[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (!visit[e.dest]) {
                topoSortUtll(Graphs, e.dest, visit, s);
            }
        }

        s.push(curr);// first element push

    }

    // topo sort bfs Kahns algorithm in degree out degree

    public static void calIndegree(ArrayList<Edge>[] Graphs, int indeg[]) {
        for (int i = 0; i < Graphs.length; i++) {
            int vertexes = i;
            for (int j = 0; j < Graphs[vertexes].size(); j++) {
                Edge e = Graphs[vertexes].get(i);
                indeg[e.dest]++;
            }
        }
    }

    public static void topSortBFS(ArrayList<Edge>[] Graphs) {
        int indeg[] = new int[Graphs.length];
        calIndegree(Graphs, indeg);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        // bfs
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + "");// toplogocal sort print

            for (int i = 0; i < Graphs[curr].size(); i++) {
                Edge e = Graphs[curr].get(i);
                indeg[e.dest]--;
                if (indeg[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
        }

        System.out.println();
    }

    public static void createGraph(ArrayList<Edge> Graphs[]) {

        for (int i = 0; i < Graphs.length; i++) {
            Graphs[i] = new ArrayList<>();
        }

        // vertexes 0
        /*
         * Graphs[0].add(new Edge(0, 1));
         * 
         * // vertexes 1
         * 
         * Graphs[1].add(new Edge(1, 0));
         * Graphs[1].add(new Edge(1, 2));
         * Graphs[1].add(new Edge(1, 3));
         * 
         * // vertexes 2
         * Graphs[2].add(new Edge(2, 1));
         * Graphs[2].add(new Edge(2, 3));
         * Graphs[2].add(new Edge(2, 4));
         */

    }

    public static void main(String[] args) {
        int v = 5;
        ArrayList<Edge> Graphs[] = new ArrayList[v];
        createGraph(Graphs);
        System.out.println(cycledetecte(Graphs));
        topologySort(Graphs);

    }
}
