import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1_B {

    public static void main(String[] args) {

        int[][][] graphs = genAllMat(3);

        for (int i = 0; i < graphs.length; i++) {
            System.out.println("Graph " + (i + 1) + ":");

            printMatrix(graphs[i]);
            System.out.println();
        }

    }

    public static int[][][] genAllMat(int n) {
        List<int[]> allEdges = new ArrayList<>();
        List<List<int[]>> allGraphs = new ArrayList<>();

        // Generate all possible edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                allEdges.add(new int[]{i, j});
            }
        }

        // Generate all subsets of the possible edges
        allGraphs.add(new ArrayList<>()); // Start with empty set

        for (int[] edge : allEdges) {
            int currentSize = allGraphs.size();
            for (int i = 0; i < currentSize; i++) {
                List<int[]> newSubset = new ArrayList<>(allGraphs.get(i));
                newSubset.add(edge);
                allGraphs.add(newSubset);
            }
        }

        // Convert to adjacency matrices
        int[][][] result = new int[allGraphs.size()][n][n];
        for (int k = 0; k < allGraphs.size(); k++) {
            int[][] matrix = new int[n][n];
            for (int[] edge : allGraphs.get(k)) {
                matrix[edge[0]][edge[1]] = 1;
                matrix[edge[1]][edge[0]] = 1;
            }
            result[k] = matrix;
        }

        return result;
    }


    // Helper function
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
