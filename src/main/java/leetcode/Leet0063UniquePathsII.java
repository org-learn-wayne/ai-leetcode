package leetcode;

public class Leet0063UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int cols = obstacleGrid[0].length;
        int[] paths = new int[cols];
        paths[0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int[] row : obstacleGrid) {
            for (int col = 0; col < cols; col++) {
                if (row[col] == 1) {
                    paths[col] = 0;
                } else if (col > 0) {
                    paths[col] += paths[col - 1];
                }
            }
        }

        return paths[cols - 1];
    }
}
