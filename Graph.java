public class Graph{
    static class Edge{
        int src;
        int dest;
        int wt;

        public Edge(int src,int dest,int wt){
            this.src = src;
            this.dest = dest;
            this.wt = wt;
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
    public static void CreateGraph( ArrayList<Edge>graph[]){
       for(int i = 0;i<graph.length;i++){
        graph[i] = new ArrayList<>();
       }

       // vertexes 0
       Graphs[0].add(new Edge(0, 1));

       // vertexes 1

       Graphs[1].add(new Edge(1, 0));
       Graphs[1].add(new Edge(1, 2));
       Graphs[1].add(new Edge(1, 3));

       // vertexes 2
       Graphs[2].add(new Edge(2, 1));
       Graphs[2].add(new Edge(2, 3));
       Graphs[2].add(new Edge(2, 4));
    }


    public static void BFS(ArrayList<Edge>graph[]){
        boolean visited[] = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();

        q.add();

        while(!q.isEmpty()){
            int current = q.remove();

            if(!visited[current]){
                System.out.print(current+" ");
                visited[current] = true;

                for(int i = 0;i<graph[current].size();i++){
                    Edge e = graph[current].get(i);
                    if(!visited[e.dest]){
                        q.add(e.dest);
                    }
                }
            }
        }
    }

    public static void BFS_Divide(ArrayList<Edge>[] graphs) {
        Boolean visited[] = new Boolean[graphs.length];

        for (int i = 0; i < graphs.length; i++) {
            if (!visited[i]) {
                BFSUtil(graphs, visited);
            }
        }
    }

    public static void BFSUtil(ArrayList<Edge>[] graphs, boolean visited[]) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);// source

        while (!q.isEmpty()) {

            int current = q.remove();

            // visited 3 step
            if (!visited[current]) {
                // 1 st step
                System.out.print(current + " ");
                visited[current] = true;// step 2
                
                for (int i = 0; i < graphs[current].size(); i++) {
                    Edge E = graphs[current].get(i);
                    q.add(E.dest);
                }
            }
        }
    }

    public static void DFS(ArrayList<Edge>graph[],int current,boolean visited[]){
        System.out.println(current+ " ");
        visited[current] = true;

        for(int i = 0;i<graph[current].size();i++){
            Edge e = graph[current].get(i);
            if(!visited[e.dest]){
                DFS(graph, e.dest, visited);
            }
        }
    }

    public static void DFSDivided(ArrayList<Edge>[] graphs) {
        boolean visited[] = new boolean[graphs.length];
        for (int i = 0; i < graphs.length; i++) {
            DFSUtil(Graphs, i, visited);
        }
    }

    public static void DFSUtil(ArrayList<Edge>[] graphs, int current, boolean visited[]) { // o(v+e)
        // visit
        System.out.print(current + "");
        visited[current] = true;

        for (int i = 0; i < graphs[current].size(); i++) {
            Edge e = graphs[current].get(i);
            if (!visited[e.dest]) {
                DFSUtil(graphs, e.dest, visited);
            }
        }
    }

    public static void DFSAllPath(ArrayList<Edge>[] Graph, boolean visited[], int current, String path, int target) {// O(V^V)
        
       if (current == target) {
        System.out.println(path);
        return;
    

      for (int i = 0; i < graph[current].size(); i++) {
          Edge e = graph[current].get(i);
          if (!visited[e.dest]) {
            visited[current] = true;// tav e rse time
            DFSAllPath(Graph, visit, e.dest, path + e.dest, target);
            visited[current] = false;// return aa ne pr false
          }
        }

    }

    public static boolean CycleDetectionUndirected(ArrayList<Edge> graph[],boolean visited[],int current,int parent){
        visited[current] = true;

        for(int i = 0;i<graph[current].size();i++){
            Edge e = graph[current].get(i);
            if(visited[e.dest] && e.dest != parent){
                return true;
            }else if(!visited[e.dest]){
                if(C=CycleDetectionUndirected(graph,visited,e.dest,current)){
                    return true;
                }
            }
        }
    }

    public static boolean isCycleUtil(ArrayList<Edge>graph[],int current,boolean visited[],boolean stack){
        stack[current] = true;
        visited[current] = true;

        for(int i=0;i<graph[current].size();i++){
            Edge e = graph[current].get(i);
            if(stack[e.dest]){
                // stack ke under hee node neigh ber hee cycle hai
                return true
            }  /*
            * else if(stack[e.dest]){
            * if(isCycleUtil(graph,e.dest,vis,stack)){
            * return true;
            * }
            * }
            */

            if(!visited[e.dest]  && cycleDetectedUtil(graph,e.dest,visited,stack)){
                return true;
            }

            stack[current] = false;// return aa te time node remove
            return false;
        }

         // cycle detection in directed graph
    }
    public static boolean isCycleDirected(ArrayList<Edge>[] graphs) {
        boolean visited[] = new boolean[graphs.length];
        boolean stack[] = new boolean[graphs.length];

        for (int i = 0; i < graphs.length; i++) {
            if (!visited[i]) {
                if (cycleDetectedUtill(Graphs, i, visited, stack)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static void PrimsAlgo(ArrayList<Edge>graph[]){
        boolean visited[] = new boolean[graph.length];
        PriorityQueue<Pairs> pq = new PriorityQueue<>();
        int finalCost = 0;
        pq.add(new Pairs(0,0));

        while(!pq.isEmpty()){
            Pairs current = pq.remove();
            if(!visited[current.v]){
                visited[current.v] = true;
                finalCost += current.cost;

                for(int i=0;i<graph[current.v].size();i++){
                    Edge e = graph[current.v].get(i);
                    if(!visited[e.dest]){
                        pq.add(new pairs(e.dest,e.wt));
                    }
                }
            }
        }

        System.out.println("Final Cost" + finalCost);
    }

    public static void krlAlgo(ArrayList<Edge> edges.,int V){
        Collection.sort(edges);
        int count = 0;
        int costOfMst = 0;

        for(int i = 0;i<V-1;i++){
            Edge e= edges.get(i);
            // source Dest

            int parA = find(e.src);
            int parB = find(e.dest);

            if(parA != parB){
                // no cycle detection.
                union(e.src,e.dest);
                costOfMst += e.wt;
                count++;
            }
        }

        System.out.Println("final Cost of MST : "+ costOfMst);
    }
    public static void topSort(ArrayList<Edge> graph[], int current, boolean visited[], Stack<Integer> s) {
        visited[current] = true;

        for (int i = 0; i < graphs[current].size(); i++) {
            Edge e = graphs[current].get(i);
            if (!visited[e.dest]) {// ne ig bo ur not visited
                topSort(graphs, e.dest, visited, s);
            }
        }

        s.push(current);
    }

    public static void dijkstraAlgo(ArrayList<Edge>graph[],int src){
        int distance[] = new int[graph.length];

        for(int i = 0;i<graph.length;i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean visited[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pairs(0,src));

        while(!pq.isEmpty()){
            Pair current = pq.remove();
            if(!visited[current.node]){
                visited[current.node] = true;

                for(int i = 0;i<graph[current.node].size();i++){
                    Edge e = graph[current.node].get(i);
                    int u = e.src;
                    int v= e.dest;
                    int wt = e.wt;

                    // important in dijkstra algo relaxation condition
                    if(distance[u]+wt <distance[v]){
                        dist[v] = dist[u] + wt;
                        pq.add(new Pairs(v,distance[v]));
                    }
                }
            }
        }

        // print of all source to vertexes to all shortest path
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i] + "");
        }

        System.out.println();
    }

    // Bell mon ford algo dp based uses of Negative wt but does not negative
    // weight cycle dynamic programming algorithm based
    // BFA not worked in for negative weight cycle

    public static void bellmanFord(ArrayList<Edge>[] graph, int src) {// T O(V*E)
        int distance[] = new int[graph.length];
        // Init il
        for (int i = 0; i < distance.length; i++) {
            if (i != src) {
                distance[i] = Integer.MAX_VALUE;
            }
        }

        int V = graph.length;
        // algo -(V)
        for (int i = 0; i < V - 1; i++) {// all node
            // edges -O(E)
            for (int j = 0; j < graph.length; j++) {// all vert find this loop edges calculate
                for (int k = 0; k < graph[j].size(); k++) {// cal edges
                    Edge e = graph[j].get(k);// neg h b our
                    // u v wt
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    // relaxation / update / new value
                    if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {// java me infinity value me positive value add hone pr value negative aa ja tee hai
                        distance[v] = distance[u] + wt;
                    }
                }

            }
        }

        // detect negative weight cycle

        for (int j = 0; j < graph.length; j++) {// all vert find this loop edges calculate
            for (int k = 0; k < graph[j].size(); k++) {// cal edges
                Edge e = graph[j].get(k);// neg h b our
                // u v wt
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;
                // relaxation / update / new value
                if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {// java me infinity value me positive value add hone pr value negative aa ja tee hai
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
    public static void main(String args[]){
        ArrayList<Edge>[] Graphs = new ArrayList[5];
        for(int i = 0;i<5;i++){
            Graphs[i] = new ArrayList<>();
        }
    }
}