import java.security.interfaces.EdECKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphProblem {

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

    static class Info {
        int verteces;
        int cost;
        int stops;

        public Info(int v, int c, int s) {
            this.verteces = v;
            this.cost = c;
            this.stops = s;
        }
    }

    // cheapest flight
    public static int cheapestFlights(int n, int flight[][], int src, int dest, int k) {
        ArrayList<Edge> Graph[] = new ArrayList[n];
        createGraph(flights, Graph);

        //
        int dist[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src, 0, 0));

        //
        while (!q.isEmpty()) {
            Info curr = q.remove();
            if (curr.stops > k) {
                break;
            }
        }

        for (int i = 0; i < Graph[curr.v].size(); i++) {
            Edge e = Graph[curr.v].get(i);
            int u = e.src;
            int v = e.dest;
            int wt = e.wt;

            if (curr.cost + wt < dist[v] && curr.stops <= k) {
                // relaxation
                dist[v] = curr.cost + wt;
                q.add(new Info(v, dist[v], curr.stops + 1));
            }
        }

        // dest ka distance
        if (dist[dest] == Integer.MAX_VALUE) {// distance nhi mila to
            return -1;
        } else {
            return dist[dest];
        }

    }

    public static void createGraph(int flights[][], ArrayList<Edge>[] Graph) {
        for (int i = 0; i < Graph.lemgth; i++) {
            Graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < flights.length; i++) {
            int src = flights[i][0];
            int dest = flights[i][1];
            int wt = flights[i][2];

            Edge e = new Edge(src, dest, wt);

            Graph[src].add(e);
        }
    }

    // connecting cities with minimum cost
    static class Edges implements Comparable<Edges> {
        int dest;
        int cost;

        public Edges(int d,int c){
            this.dest = d;
            this.cost = c;
            
            @Override
            public int compareTo(Edges e2){
                return this.cost - e2.cost;// asending 
            }
        }

    }

    public static int connectingCities(int cities[][]) {
        PriorityQueue<Edges> pq = new PriorityQueue<>();
        boolean vis[] = new boolean[cities.length];

        pq.add(new Edges(0, 0));
        int finalCost = 0;

        while (!pq.isEmpty()) {
            Edges curr = pq.remove();
            if (!vis[curr.dest]) {
                vis[curr.dest] = true;
                finalCost += curr.cost;

                for (int i = 0; i < cities[curr.dest].length; i++) {
                    if (cities[curr.dest][i] != 0) {
                        pq.add(new Edges(i, cities[curr.dest][i]));
                    }
                }
            }
        }

        return finalCost;
    }

    public static void main(String args[]) {
        int n = 4;
        int flights[][] = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
        int src = 0, dest = 3, wt = 1;

        int cities[][] = { { 0, 1, 2, 3, 4, }, { 1, 0, 5, 0, 7 }, { 2, 5, 0, 6, 0 }, { 3, 0, 6, 0, 0 },
                { 4, 7, 0, 0, 0 } };
        connectingCities(cities);

    }
}
