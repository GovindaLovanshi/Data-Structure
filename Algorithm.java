import java.util.*;

public class Algorithm {
    // 1 Bubble Sort O( n2)

    public static void Bubblesort(int arr[]) {
        for (int turn = 0; turn < arr.length - 1; turn++) {
            boolean swaped = false;

            for (int j = 0; j < arr.length - 1 - turn; j++) {

                if (arr[j] > arr[j + 1]) {
                    // swap

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }

            if (swaped == false) {
                break;
            }
        }
    }

    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    // selection sort TCO(N2)

    public static void selectionsort(int arr[]) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minpos = i;// current

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[minpos] > arr[j]) {
                    minpos = j;// update
                }
            }

            // swap
            int temp = arr[minpos];
            arr[minpos] = arr[i];
            arr[i] = temp;
        }
    }

    public static void Insertionsort(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            int prev = i - 1;

            // finding out the coorrect possition

            while (prev >= 0 && arr[prev] > curr) { // chnage condition desending
                arr[prev + 1] = arr[prev];
                prev--;
            }
            // insertion
            arr[prev + 1] = curr;

        }
    }

    public static void countingsort(int arr[]) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            largest = Math.max(largest, arr[i]);

        }
        int count[] = new int[largest + 1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        // sorting
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[j] = i;
                j++;
                count[i]--;
            }
        }
    }

    // binary search O(logn)

    public static int BinarySearch(int number[], int key) {
        int start = 0, end = number.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (number[mid] == key) {
                return mid;
            }
            if (number[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;

    }

    public static void mergeSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }

        // kaam
        int mid = si + (ei - si) / 2; // (si +ei)/2
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        merge(arr, si, mid, ei);

    }

    public static void merge(int arr[], int si, int mid, int ei) {
        int temp[] = new int[ei - si + 1];
        int i = si; // iterater for left part
        int j = mid; // iterater for right part
        int k = 0;
        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        // left part
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // right part
        while (j <= ei) {
            temp[k++] = arr[j++];
        }

        // copy temp to orignal arr
        for (k = 0, i = si; k < temp.length; k++, i++) {
            arr[i] = temp[k];
        }

    }

    public static void print(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void QuickSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }

        int pIdx = partitaion(arr, si, ei);
        QuickSort(arr, si, pIdx - 1); // left
        QuickSort(arr, pIdx + 1, ei); // right

    }

    public static int partitaion(int arr[], int si, int ei) {
        int pivot = arr[ei];
        int i = si - 1; // to make plase for els small place

        for (int j = si; j < ei; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;

            }

        }

        i++;
        int temp = pivot;
        arr[ei] = arr[i];
        arr[i] = temp;
        return i;
    }

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

    public static void dijkstra(ArrayList<Edge>[] Graph, int src) {// TC O(V+ ElogV) using priority queue greedy based
        int dist[] = new int[Graph.length];

        for (int i = 0; i < Graph.length; i++) {
            if (i != src) {// not visit
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean visit[] = new boolean[Graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(src, 0));

        // bfs loop
        while (!pq.isEmpty()) {
            Pair current = pq.remove();
            if (!visit[current.node]) {// node is not visited 
                visit[current.node] = true;
                // neigh bours
                for (int i = 0; i < Graph[current.node].size(); i++) {
                    Edge e = Graph[current.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    // important in dijkstra algo 
                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;// updated distance new val relaxation 
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

    // Bell mon ford algo dp based uses of Negative wt but does not negative
    // weight cycle dynamic programming algorithm based
    // BFA not worked in for negative weight cycle
    public static void bellmanFord(ArrayList<Edge>[] Graph, int src) {// T O(V*E)
        int dist[] = new int[Graph.length];
        // Init il
        for (int i = 0; i < dist.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        int V = Graph.length;
        // algo -(V)
        for (int i = 0; i < V - 1; i++) {// all node
            // edges -O(E)
            for (int j = 0; j < Graph.length; j++) {// all vert find this loop edges calculate
                for (int k = 0; k < Graph[j].size(); k++) {// cal edges
                    Edge e = Graph[j].get(k);// neg h  b our
                    // u v wt
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    // relaxation / update  /  new value
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {// java me infinity value me positive value add hone pr value negative aa ja tee hai
                        dist[v] = dist[u] + wt;
                    }
                }

            }
        }

        // detect negative weight cycle

        for (int j = 0; j < Graph.length; j++) {// all vert find this loop edges calculate
            for (int k = 0; k < Graph[j].size(); k++) {// cal edges
                Edge e = Graph[j].get(k);// neg h  b our
                // u v wt
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;
                // relaxation / update  /  new value
                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {// java me infinity value me positive value add hone pr value negative aa ja tee hai
                    System.out.println("negative wt cycle exist");
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
            this.v = v;// node
            this.cost = c;
        }

        @Override
        public int CompareTo(Pairs p2) {
            return this.cost - p2.cost;// ascending based on cost
        }
    }

    public static void PrimsAlgo(ArrayList<Edge> Graph[]) {// TC O(E logE)
       // ArrayList<Edge>edge = new ArrayList<>(); cal edges 
        boolean visit[] = new boolean[Graph.length];// mst set (iamgine)
        PriorityQueue<Pairs> pq = new PriorityQueue<>();// non mst  set  (iamgine) worst case E  ] [(E logE)
        pq.add(new Pairs(0, 0));
        int finalCost = 0; // MST cost/weighted total minimum weighted

        while (!pq.isEmpty()) {
            Pairs curr = pq.remove();
            if (!visit[curr.v]) {
                visit[curr.v] = true;
                finalCost += curr.cost;

                for (int i = 0; i < Graph[curr.v].size(); i++) {//neg bor
                    Edge e = Graph[curr.v].get(i);
                    if(!visit[e.dest]){
                        pq.add(new Pairs(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("final(min) cost of Mst =" + finalCost);
    }

    private void heapify(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int minIdx = i; // root

        if (left < arr.size() && arr.get(minIdx) > arr.get(left)) {// max heap => > to <
            minIdx = left;
        }

        if (right < arr.size() && arr.get(minIdx) > arr.get(right)) {// max heap => > to <
            minIdx = right;
        }

        if (minIdx != i) {
            // swap
            int temp = arr.get(i);
            arr.set(i, arr.get(minIdx));
            arr.set(minIdx, temp);

            heapify(minIdx);

        }
    }

    public in remove() {// O(nlogn)

        int data = arr.get(0);

        // step 1 swap first and last aessa krnr pr min heap bigad jati h is heapify
        // function ka use krte h
        int temp = arr.get(data);
        arr.set(0, arr.get(arr.size() - 1));
        arr.set(arr.size() - 1, temp);

        // atep 2 delete last
        arr.remove(arr.size() - 1);

        // satep 3 heapify
        heapify(0);
        return data;

    }

    public boolean isEmpty() {
        return arr.size() == 0;
    }

    // heap sort => max heap --> assending min heap --> desecending order

    public static void heapifys(int arr[], int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int maxIdx = i; // root

        if (left < size && arr[left] > arr[maxIdx]) { // desending order > to <
            minIdx = left;
        }

        if (right < size && arr[right] > arr[maxIdx]) { // desending order > to <
            minIdx = right;
        }

        if (maxIdx != i) {
            // swap

            int temp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = temp;

            heapifys(arr, maxIdx, size);
        }

    }

    public static void heapSort(int arr[]) {// O(nlogn)
        // step - build maxheap
        int b = arr.length;
        for (int i = n / 2; i >= 0; i--) {// O(nlogn)
            heapifys(arr, i, n);
        }

        // 2ns step

        for (int i = n - 1; i > 0; i--) {// O(nlogn)
            // swap(largest first with last)
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapifys(arr, 0, i);
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
    public staticvoid DFSAllPath(ArrayList<Edge>[]Graph,boolean visit[],int curr,String path,int target){// TC O(V^V) very bad not use large graph 
        if(curr == target){
            System.out.println(path);
            return;
        }

        for(int i=0;i<Graph[curr].size();i++){
            Edge e = Graph[curr].get(i);
            if(!visit[e.dest]){
                visit[curr] = true;// taverse time
                DFSAllPath(Graph,visit,e.dest,path+e.dest,target);
                visit[curr] = false;// return aane pr false
            }
        }

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

        while (!q.isEmpty()) {// q empty nahi hoti hai

            int curr = q.remove();// 

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

    public static void DFS(ArrayList<Edge>[] Graphs, int curr, boolean visit[]) { // o(v+e)  //keep going to the first 1st neighbours
        // visit
        System.out.print(curr + "");
        visit[curr] = true;

        for (int i = 0; i < Graphs[curr].size(); i++) {
            Edge e = Graphs[curr].get(i);
            if (!visit[e.dests]) {// neghbour not visited  
                DFS(Graphs, e.dest, visit);// e.dest is neghbours
            }
        }
    }

    // kruskal algorithm find mst greedy base a TC O(V + ElogE)
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

    public static void kosarajuAlgo(ArrayList<Edge> Graphs[], int V) { // O(v+e)
        // step 1 topo sort arder store V + E 
        Stack<Integer> s = new Stack<>();
        boolean visit[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visit[i]) {
                topSort(Graphs, i, visit, s);
            }
        }

        // step 2 transpose graph reverse o(V+e) reverse edges direction
        ArrayList<Edge> transpose[] = new ArrayList[V];// transpose graph 
        for (int i = 0; i < Graphs.length; i++) {// initilise 
            visit[i] = false;// re use initilize 
            transpose[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < Graphs[i].size(); i++) {
                Edge e = Graphs[i].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src));// reverse edge e.dest add new aedge
            }
        }

        // step 3 O(v+e) e.dest add new aedge
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!visit[curr]) {
                System.out.print("STRONGEST CONNECTED COMPONENT-->");
                dfs(transpose, curr, visit);// src
                System.out.println();
            }
        }
    }

    public static void main(String args[]) {

    }
}
