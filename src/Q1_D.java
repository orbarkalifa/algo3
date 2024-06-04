import java.util.Arrays;

public class Q1_D {

    public static void main(String[] args) {
        // Example graph as an adjacency matrix
        int[][] graph = {
                {0, 0, 1},
                {0, 0, 1},
                {1, 1, 0}
        };

        System.out.println("Original graph:");
        printMatrix(graph);

        int[] maxClique = dvirsMaxClique(graph);

        System.out.println("\nMaximum clique vertices:");
        System.out.println(Arrays.toString(maxClique));
    }

    static int[] dvirsMaxClique(int[][] G) {
        int n = G.length;
        Vertex[] values = new Vertex[n];

        // Calculate binary to decimal values for each vertex (row)
        for (int i = 0; i < n; i++) {
            values[i] = new Vertex(rowToBinary(G, i), i);
        }

        // Sort vertices based on their binary values in descending order
        Arrays.sort(values, (v1, v2) -> Integer.compare(v2.value, v1.value));

        // Create a new matrix with columns sorted based on the sorted vertex indices
        int[][] sortedColumnsG = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sortedColumnsG[j][i] = G[j][values[i].index];
            }
        }

        // Create a new matrix with rows sorted based on the sorted vertex indices
        int[][] sortedG = new int[n][n];
        for (int i = 0; i < n; i++) {
            sortedG[i] = sortedColumnsG[values[i].index];
        }

        // Set the diagonal elements to 1
        for (int i = 0; i < n; i++) {
            sortedG[i][i] = 1;
        }

        return maxSquare(sortedG, values);
    }

    private static int[] maxSquare(int[][] g, Vertex[] values) {
        int max=0;
        for (int i=0;i<g.length;i++)
            if (g[i][0]==0)
                break;
            else max++;
        int maxR=max;
        for(int i=1;i< maxR;i++) {
            for (int j = 0; j < maxR; j++) {
                if(g[j][i]==0){
                    maxR = Math.max(i,j);
                }
            }
        }
        int[] result = new int[maxR];
        for (int i = 0; i < maxR; i++) {
            result[i] = values[i].index+1; // + 1 is added to name the vertices 1,2,3...
        }
        return result;
    }

    private static int rowToBinary(int[][] g, int i) {
        int decimalValue = 0;
        for (int j = 0; j < g[i].length; j++) {
            decimalValue += g[i][j] * (1 << j); // 1 << j is equivalent to 2^j
        }
        return decimalValue;
    }


    // Helper functions
    public static void printMatrix(int[][] g) {
        for (int[] row : g) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private static class Vertex {
        int value, index;

        public Vertex(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
