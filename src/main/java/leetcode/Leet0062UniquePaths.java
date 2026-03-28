package leetcode;

public class Leet0062UniquePaths {
    public int uniquePaths(int m, int n) {
        int[] paths = new int[n];
        for (int col = 0; col < n; col++) {
            paths[col] = 1;
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                paths[col] += paths[col - 1];
            }
        }

        return paths[n - 1];
    }
}
