import java.util.*;


public class Q1_E {

    public static void main(String[] args) {
        // Specify the number of vertices for generating graphs
        int n = 3;

        // Call checkDvirsClique method
        int[] result = checkDvirsClique(n);

        System.out.println(Arrays.toString(result));
    }



    static int[] checkDvirsClique(int n) {
        int[][][] graphs = Q1_B.genAllMat(n);

        int[] clicks = new int[graphs.length];
        for (int i = 0; i < graphs.length; i++) {
            clicks[i] = compareAlgorithms(graphs[i]);
        }
        return clicks;
    }


    public static int compareAlgorithms(int[][] graph) {
        int n = graph.length; // Number of vertices
        int maxClique1 = Q1_D2.dvirsMaxClique(graph).length;
        FindClique.graph = graph;
        FindClique.store = new int[n];
        FindClique.n = n;
        FindClique.d = new int[n];

        // Initialize d array based on graph connections
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    FindClique.d[i]++;
                }
            }
        }

        int maxClique2 = FindClique.maxCliques(0, 1);

        // If the arrays are equal, return 1; otherwise, return 0
        return maxClique1 == maxClique2 ? 1 : 0;
    }


}
