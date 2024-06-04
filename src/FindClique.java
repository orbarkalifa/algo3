// Java implementation of the approach
import java.util.*;
class FindClique {
    static int MAX = 100, n;
    // Stores the vertices
    static int []store = new int[MAX];
    // Graph
    static int [][]graph = new int[MAX][MAX];
    // Degree of the vertices
    static int []d = new int[MAX];
    // Function to check if the given set of  vertices in store array is a clique or not
    static boolean is_clique(int b) {
        // Run a loop for all set of edges
        for (int i = 1; i < b; i++)
        {
            for (int j = i + 1; j < b; j++)
                // If any edge is missing
                if (graph[store[i]][store[j]] == 0)
                    return false;
        }
        return true;
    }
    // Function to find all the sizes of maximal cliques
    static int maxCliques(int i, int l) {
        // Maximal clique size
        int max_ = 0;
        // Check if any vertices from i+1 can be inserted
        for (int j = i + 1; j <= n; j++) {
            // Add the vertex to store
            store[l] = j;
            // If the graph is not a clique of size k then it cannot be a clique by adding another edge
            if (is_clique(l + 1))
            {
                // Update max
                max_ = Math.max(max_, l);
                // Check if another edge can be added
                max_ = Math.max(max_, maxCliques(j, l + 1));
            }
        }
        return max_;
    }
    // Driver code
    public static void main(String[] args) {
        int [][]edges = { { 1, 3 }, { 2, 3 }, { 3, 1 },
                { 3, 2 } };
        int size = edges.length;
        n = 4;
        for (int i = 0; i < size; i++) {
            graph[edges[i][0]][edges[i][1]] = 1;
            graph[edges[i][1]][edges[i][0]] = 1;
            d[edges[i][0]]++;
            d[edges[i][1]]++;
        }
        System.out.print(maxCliques(0, 1));
    }
}