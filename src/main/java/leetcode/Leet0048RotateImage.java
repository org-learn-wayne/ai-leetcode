package leetcode;

public class Leet0048RotateImage {
    public void rotate(int[][] matrix) {
        int size = matrix.length;

        for (int row = 0; row < size; row++) {
            for (int col = row + 1; col < size; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size / 2; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][size - 1 - col];
                matrix[row][size - 1 - col] = temp;
            }
        }
    }
}
