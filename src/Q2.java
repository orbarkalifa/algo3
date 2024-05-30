public class Q2 {
    public int findPeak(int [][] matrix){
        if(matrix.length<3 || matrix[0].length<3 )return -1;
        int t,b,r,l;
        int [][] check = new int[matrix.length][matrix[0].length];
        for(int i=1;i< matrix.length-1;i+=2){
            for(int j=2;j<matrix[i].length;j+=2){
                t = matrix[i-1][j];
                b = matrix[i+1][j];
                r = matrix[i][j+1];
                l = matrix[i][j-1];
                if (Math.max(Math.max(Math.max(t,b),Math.max(l,r)) ,matrix[i][j]) == matrix[i][j])
                    return matrix[i][j];
                else checkSides(matrix,check,i,j);
            }
        }
        for(int i=2;i< matrix.length-1;i+=2) {
            for (int j = 1; j < matrix[i].length-1; j += 2) {
                t = matrix[i - 1][j];
                b = matrix[i + 1][j];
                r = matrix[i][j + 1];
                l = matrix[i][j - 1];
                if (Math.max(Math.max(Math.max(t, b), Math.max(l, r)), matrix[i][j]) == matrix[i][j])
                    return matrix[i][j];
                else {
                    int temp = checkSides(matrix,check,i,j);
                    if (temp != 0) return temp;
                }
            }
        }
        for(int i=1; i< matrix[0].length;i+=2){
            int temp = checkSides(matrix,check,1,i);
            if (temp != 0) return temp;
            temp = checkSides(matrix,check, matrix[0].length-2, i);
            if (temp != 0) return temp;
        }
        for(int i=3; i< matrix.length;i+=2){
            int temp = checkSides(matrix,check,i,1);
            if (temp != 0) return temp;
            temp = checkSides(matrix,check,i,matrix.length-2);
            if (temp != 0) return temp;
        }
        return -1;

    }
    public int checkSides(int[][] matrix,int[][]check,int i, int j){
        check[i - 1][j] += matrix[i][j] < matrix[i - 1][j] ? 3 : 0;
        if (check[i - 1][j] == 26) return matrix[i - 1][j];
        check[i + 1][j] += matrix[i][j] < matrix[i + 1][j] ? 5 : 0;
        if (check[i + 1][j] == 26) return matrix[i + 1][j];
        check[i][j + 1] += matrix[i][j] < matrix[i][j + 1] ? 7 : 0;
        if (check[i][j + 1] == 26) return matrix[i][j + 1];
        check[i][j - 1] += matrix[i][j] < matrix[i][j - 1] ? 11 : 0;
        if (check[i][j - 1] == 26) return matrix[i][j - 1];
        return 0;
    }
    public static void main(String[] args) {
        Q2 solution = new Q2();

        // Test Case 1: Simple 4x4 matrix
        int[][] matrix1 = {
                {10, 8, 10, 10},
                {14, 13, 12, 11},
                {15, 9, 11, 21},
                {16, 17, 19, 20}
        };
        System.out.println("Peak Element in matrix1: " + solution.findPeak(matrix1));

        // Test Case 2: Larger matrix
        int[][] matrix2 = {
                {10, 7, 8, 6},
                {5, 9, 3, 4},
                {2, 1, 10, 12},
                {11, 13, 14, 15}
        };
        System.out.println("Peak Element in matrix2: " + solution.findPeak(matrix2));

        // Test Case 3: Edge case with small matrix
        int[][] matrix3 = {
                {1, 2},
                {3, 4}
        };
        System.out.println("Peak Element in matrix3: " + solution.findPeak(matrix3));

        // Test Case 4: Matrix with only one peak
        int[][] matrix4 = {
                {1, 2, 3},
                {6, 5, 4},
                {7, 8, 9}
        };
        System.out.println("Peak Element in matrix4: " + solution.findPeak(matrix4));
    }//coment
}
