package leetcode;

public class Leet0064MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int cols = grid[0].length;
        int[] costs = new int[cols];
        costs[0] = grid[0][0];

        for (int col = 1; col < cols; col++) {
            costs[col] = costs[col - 1] + grid[0][col];
        }

        for (int row = 1; row < grid.length; row++) {
            costs[0] += grid[row][0];
            for (int col = 1; col < cols; col++) {
                costs[col] = Math.min(costs[col], costs[col - 1]) + grid[row][col];
            }
        }

        return costs[cols - 1];
    }
}
