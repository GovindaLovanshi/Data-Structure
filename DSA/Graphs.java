import java.sql.Time;
import java.util.*;

public class Graphs {
    // Question 1 :
    // Mother Vertex
    // We have a Directed Graph, find a Mother Vertex in the Graph (if present). A
    // Mother Vertex is a
    // vertex through which we can reach all the other vertices of the Graph.

    // TC O(v+e) sc O(v+e)
    static void addEdge(int u, int v, ArrayList<ArrayList<Integer>> adj) {
        adj.get(u).add(v);
    }

    static void DFDUtil(ArrayList<ArrayList<Integer>> g, int v, boolean vis[]) {
        vis[v] = true;

        for (int x : g.get(v)) {
            if (!vis[x]) {
                DFDUtil(g, x, vis);
            }
        }
    }

    static int motherVertex(ArrayList<ArrayList<Integer>> g, int V) {
        boolean[] vis = new boolean[V];
        int v = -1;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                DFDUtil(g, i, vis);
                v = i;
            }
        }

        boolean[] check = new boolean[V];
        DFDUtil(g, v, check);

        for (boolean val : check) {
            if (!val) {
                return -1;
            }
        }

        return v;
    }

    // Question 2 :
    // Union-Find
    // Here implement Union-Find Algorithm to check whether an undirected graph
    // contains cycle or
    // not.

    // TC O(v+e) Sc(1)

    class Solution {
        int V, E;
        Edge edge[];

        class Edge {
            int src;
            int dest;
        }

        Solution(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[E];
            for (int i = 0; i < e; i++) {
                edge[i] = new Edge();
            }
        }

        int find(int parent[], int i) {
            if (parent[i] == i) {
                return i;
            }

            return find(parent, parent[i]);
        }

        void Union(int parent[], int x, int y) {
            parent[x] = y;
        }

        int isCycle(Solution graph) {
            int parent[] = new int[graph.V];

            for (int i = 0; i < graph.V; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < graph.E; i++) {
                int x = graph.find(parent, graph.edge[i].src);
                int y = graph.find(parent, graph.edge[i].dest);

                if (x == y) {
                    return 1;
                }

                graph.Union(parent, x, y);
            }

            return 0;
        }
    }

    // Find whether it is possible to finish all tasks or not
    // luck33565@gmail.com// There are a total of n tasks you have to pick, labelled
    // from 0 to n-1. Some
    // tasks may have
    // prerequisites, for example to pick task 0 you have to first pick task 1,
    // which is expressed as a
    // pair: [0, 1] Given the total number of tasks and a list of prerequisite
    // pairs, is it possible for you
    // to finish all tasks?.
    // Sample Input 1 : [[1, 0], [0, 1]]
    // Sample Output 1 : false
    // Sample Input 2 : [[1, 0]]
    // Sample Output 2 : true

    // Time Complexity : o(n)
    // Space Complexity: o(n)

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static ArrayList<ArrayList<Integer>>

    make_graph(int numTask,Vector<Pair>prequistes){
        ArrayList<ArrayList<Integer>>graph = new ArrayList<<ArrayList<Integer>>(numTask);

        for(int i=0;i<numTask;i++){
            graph.add(new ArrayList<Integer>());
        }

        for(pair pre:prequistes){
            graph.get(pre.second).add(pre.first);
        }

        return graph;
    }

    static int[] compute_indegree(ArrayList<ArrayList<Integer>> graph) {
        int degress[] = new int[graph.size()];

        for (ArrayList<Integer> neighbour : graph) {
            for (int neigh : neighbour) {
                degress[neigh]++;
            }
        }
        return degress;
    }

    static boolean canFinish(int numTask, Vector<Pair> prereQuisties) {
        ArrayList<ArrayList<Integer>> graph = make_graph(numTask, prereQuisties);
        int degress[] = compute_indegree(graph);

        for (int i = 0; i < numTask; i++) {
            int j = 0;
            for (; j < numTask; j++) {
                if (degress[j] == 0) {
                    break;
                }
            }

            if (j == numTask) {
                return false;
            }
            degress[j] = -1;
            for (int neigh : graph.get(j)) {
                degress[neigh]--;
            }
        }

        return true;

    }

    // Question 4 :
    // Alien Dictionary
    // We have a sorted dictionary of an alien language having N words and k
    // starting alphabets of
    // standard dictionary. Find the order of characters in the alien language. Many
    // orders may be
    // possible for a particular test case, thus you may return any valid order and
    // output will be 1 if
    // the order of string returned by the function is correct else 0 denoting
    // incorrect string returned.
    // Sample Input 1 :
    // N = 3, K = 3
    // dict = {"caa","aaa","aab"}
    // Sample Output 1 : 1
    // Sample Input 2 :
    // N = 5, K = 4
    // dict = {"baa","abcd","abca","cab","cad"}
    // Sample Output 2 : 1

    // TC O(V+E) Sc O(V+E)

    public static alienOrder(String words[]){
        Map<Character,Set<Character>>map = new HashMap<>();
        Map<Character,Integer>degree = new HashMap<>();
        String result = "";
        if(words == null || words.length == 0){
            return;
        }

        for(String s: words){
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }

        for(int i=0;i<words.length;i++){
            String curr = words[i];
            String next = words[i+1];
            int min = Math.min(curr.length(), next.length());
            for(int j=0;j<min;j++){
                char c1 = curr.charAt(j);
                char c2 = next.charAt(j);
                if(c1 != c2){
                    Set<Character> set = map.getOrDefault(c1, new HashSet<>());
                    if(!set.contains(c2)){
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2)+1);//update c2 c1 <c2
                    }
                    break;
                }
            }
        }

        LinkedList<Character>q = new LinkedList<>();
        for(char c:degree.keySet()){
            if(degree.get(c) == 0){
                q.add(c);
            }
        }

        while(!q.isEmpty()){
            char c = q.poll();
            result += c;
            if(map.containsKey(c)){
                for(char next:map.get(c)){
                    degree.put(next,degree.get(next)-1);
                    if(degree.get(next) == 0){
                        q.offer(next);
                    }
                }
            }
        }

        return result.length() == degree.size()?result:"";
    }

    // Question 5 :
    // Find number of closed islands
    // We have a binary matrix mat[][] of dimensions NxM such that 1 denotes land
    // and 0 denotes
    // water. Find the number of closed islands in the given matrix.
    // A closed island is known as the group of 1s that is surrounded by only 0s on
    // all the four sides
    // (excluding diagonals). If any 1 is at the edges of the given matrix then it
    // is not considered as
    // the part of the connected island as it is not surrounded by all 0's.
    // Sample Input 1
    // N = 3, M = 3
    // mat[][] = {{1, 0, 0},
    // {0, 1, 0},
    // {0, 0, 1}}
    // Sample Output 1 : 1
    // Sample Input 2 :
    // N = 5, M = 8
    // mat[][] = {{0, 0, 0, 0, 0, 0, 0, 1},
    // {0, 1, 1, 1, 1, 0, 0, 1},
    // {0, 1, 0, 1, 0, 0, 0, 1},
    // {0, 1, 1, 1, 1, 0, 1, 0},
    // {0, 0, 0, 0, 0, 0, 0, 1}}
    // Sample Output 2 : 2

    static void dfs(int[][] matrix, boolean[][] visited, int x, int y, int n, int m, boolean hasCornerCell) {
        if (x < 0 || y < 0 || x >= n || matrix[x][y] == 0) {
            return;
        }

        if (x == 0 || y == 0 ||
                x == n - 1 || y == m - 1) {
            if (matrix[x][y] == 1) {
                hasCornerCell = true;
            }
        }

        visited[x][y] = true;
        dfs(matrix, visited, x + 1, y, n, m, hasCornerCell);
        dfs(matrix, visited, x, y + 1, n, m, hasCornerCell);
        dfs(matrix, visited, x - 1, y, n, m, hasCornerCell);
        dfs(matrix, visited, x, y - 1, n, m, hasCornerCell);

    }

    static int countClosedIsland(int[][] matrix, int n, int m) {
        boolean visited[][] = new boolean[n][m];
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i != 0 && j != 0 && i != n - 1 && j != m - 1) && matrix[i][j] == 1 && visited[i][j] == false) {
                    boolean hasCornerCell = false;
                    dfs(matrix, visited, i, j, n, m, hasCornerCell);
                    if (!hasCornerCell) {
                        result = result + 1;
                    }
                }
            }
        }

        return result;
    }

    // there are a total of n task you have to pick to labbe,d from 0 to n-1 some
    // task may have prerequisites for example to pick task 0 you have to first
    // picktask

    // TC O(V+E) order of task using topological

    public static boolean isCycle(ArrayList<ArrayList<Integer>> graph, int curr, boolean vis[], boolean stack[]) {
        vis[curr] = true;
        stack[curr] = true;

        for (int i = 0; i < graph.get(curr).size(); i++) {
            int neigh = graph.get(curr).get(i);
            if (stack[neigh]) {
                return true;// cycle exist
            } else if (!vis[neigh]) {
                if (isCycle(graph, curr, vis, stack)) {
                    return true;
                }
            }
        }

        stack[curr] = false;
        return false;
    }

    public static boolean Task(int task, int[][] prerequisite) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < task; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisite.length; i++) {
            int src = prerequisite[i][1];
            int dest = prerequisite[i][0];
            graph.get(src).add(dest);
        }

        boolean vis[] = new boolean[task];
        boolean stack[] = new boolean[task];

        for (int i = 0; i < task; i++) {
            if (!vis[i]) {
                if (isCycle(graph, i, vis, stack)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int Solution(String s) {
        int n = s.length();
        int left[] = new int[n - 1];
        int right[] = new int[n - 1];

        left[0] = s.charAt(0) != '>' ? 1 : 0;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) != '>') {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
        }

        right[n - 2] = s.charAt(n - 1) != '<' ? 1 : 0;
        for (int i = n - 3; i >= 0; i--) {
            if (s.charAt(i + 1) != '<') {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 0;
            }
        }

        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int length = 2 * Math.min(left[i], right[i]);
            if (length > ans) {
                ans = length;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        // int V = 7;
        // int E = 8;
        // ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        // for (int i = 0; i < V; i++) {
        // adj.add(new ArrayList<Integer>());
        // }
        // addEdge(0, 1, adj);
        // addEdge(0, 2, adj);
        // addEdge(1, 3, adj);
        // addEdge(4, 1, adj);
        // addEdge(6, 4, adj);
        // addEdge(5, 6, adj);
        // addEdge(5, 2, adj);
        // addEdge(6, 0, adj);
        // System.out.println("The mother vertex is " + motherVertex(adj, V));
        // int V = 3, E = 3;
        // Solution graph = new Solution(V, E);
        // graph.edge[0].src = 0;
        // graph.edge[0].dest = 1;
        // graph.edge[1].src = 1;
        // graph.edge[1].dest = 2;
        // graph.edge[2].src = 0;
        // graph.edge[2].dest = 2;
        // if (graph.isCycle(graph) == 1) {
        // System.out.println("Graph contains cycle");
        // } else {
        // System.out.println("Graph doesn't contain cycle");
        // }

        // int numTasks = 4;
        // Vector<pair> prerequisites = new Vector<pair>();
        // prerequisites.add(new pair(1, 0));
        // prerequisites.add(new pair(2, 1));
        // prerequisites.add(new pair(3, 2));
        // if (canFinish(numTasks, prerequisites)) {
        // System.out.println("Possible to finish all tasks");
        // } else {
        // System.out.println("Impossible to finish all tasks");
        // }

        // int N = 5, M = 8;
        // int[][] matrix = { { 0, 0, 0, 0, 0, 0, 0, 1 },
        // { 0, 1, 1, 1, 1, 0, 0, 1 },
        // { 0, 1, 0, 1, 0, 0, 0, 1 },
        // { 0, 1, 1, 1, 1, 0, 1, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 1 } };
        // System.out.print(countClosedIsland(matrix, N, M));

        // int task = 2;
        // int prerequisite[][] = { { 1, 0 }, { 0, 1 } };
        // System.out.println(Task(task, prerequisite));
        String s = "<<>>";
        System.out.println(Solution(s));
    }
}
