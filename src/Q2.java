class Q2 {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8}
        };

        System.out.println(findPeak(matrix, 0, matrix[0].length));
    }

    public static int findPeak(int[][] matrix, int start, int end) {
        if (end - start <= 1) return -1;

        int mid = (start + end) / 2;
        int maxIndex = findMaxInCol(matrix, mid);

        if (isPeak(matrix, maxIndex, mid)) return matrix[maxIndex][mid];

        int peakLeft = findPeak(matrix, start, mid);
        int peakRight = findPeak(matrix, mid, end);

        return Math.max(peakLeft, peakRight);
    }

    private static boolean isPeak(int[][] matrix, int i, int j) {
        // A peak must have 4 smaller neighbors, so it cannot be on the border
        if (i <= 0 || i >= matrix.length - 1 || j <= 0 || j >= matrix[0].length - 1) {
            return false;
        }

        // Check if the potential peak has four smaller neighbors
        return matrix[i][j] > matrix[i - 1][j] &&
                matrix[i][j] > matrix[i + 1][j] &&
                matrix[i][j] > matrix[i][j - 1] &&
                matrix[i][j] > matrix[i][j + 1];
    }

    private static int findMaxInCol(int[][] matrix, int mid) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][mid] > max) {
                max = matrix[i][mid];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
