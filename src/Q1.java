import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of points in the graph");
        int n = input.nextInt();
        int [] clicks = checkDvirsClique(n);
        for(int click:clicks){
            System.out.println(click);
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

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    static int[] calcSumOfColums(int[][] G) {
        int[] sumOf1 = new int[G.length];
        int counter = G.length - 1;
        while (counter >= 0) {
            //adds to culom
            for (int i = 0; i < G.length; i++) {
                sumOf1[i]*=10;
                sumOf1[i] = sumOf1[i] + G[counter][i];

            }
            counter--;
        }
        return sumOf1;
    }

    static int[] findIndexesPlace(int[] ints) {
        int counter = 0;
        int[] indexes = new int[ints.length];
        int[] temp = ints.clone();
        Arrays.sort(temp);


        for (int i = ints.length - 1; i > 0; i--) {
            for (int anInt : ints) {
                if (anInt == temp[i]) {
                    indexes[counter] = i;
                    counter++;
                    break;
                }
            }

        }
        return indexes;
    }

    static int[][] createNewGraph(int[][] G, int[] indexes) {
        int[][] newG = new int[G.length][G.length];
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G.length; j++) {
                newG[i][j] = G[indexes[i]][indexes[j]];
                if (i == j) newG[i][j] = 1;
            }
        }
        return newG;
    }

    static int findMaxClick(int[][] G) {
        int max = 0;
        for (int[] firsColum : G) {
            if (firsColum[0] == 0)
                break;
            else max++;
        }
        int maxR = max;
        for (int i = 1; i < maxR; i++) {
            for (int j = 0; j < maxR; j++) {
                if (G[j][i] == 0) {
                    maxR = Math.max(i, j);
                }

            }
        }

        return maxR;
    }

    static int dvirsMaxClique(int[][] G) {

        int[] sumOf1 = calcSumOfColums(G);
        int[] indexes = findIndexesPlace(sumOf1);
        int[][] newG = createNewGraph(G, indexes);
        return findMaxClick(newG);

    }
    static int [] checkDvirsClique(int n){
        int[][][] graphs = genAllMat(n);
        int [] clicks = new int[graphs.length];
        for (int i = 0; i < graphs.length; i++){
            clicks[i] = dvirsMaxClique(graphs[i]);
        }
        return clicks;
    }


}
