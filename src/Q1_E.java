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

        for (int i = 0; i < graphs.length; i++) {
            Q1_D.printMatrix(graphs[i]);
            System.out.println();
        }

        int[] clicks = new int[graphs.length];
        for (int i = 0; i < graphs.length; i++) {
            clicks[i] = compareAlgorithms(graphs[i]);
        }
        return clicks;
    }


    public static int compareAlgorithms(int[][] graph) {
        int n = graph.length; // Number of vertices

        int maxClique1 = Q1_D.dvirsMaxClique(graph).length;


        FindClique.n = n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                FindClique.graph[i+1][j+1] = graph[i][j]; // initialize the static graph for test in FindClique class
            }
        }

        int maxClique2 = FindClique.maxCliques(0, 1);

        System.out.println("Dvirs: " + maxClique1 + ", Original: " + maxClique2);
        // If the arrays are equal, return 1; otherwise, return 0
        return maxClique1 == maxClique2 ? 1 : 0;
    }


}
