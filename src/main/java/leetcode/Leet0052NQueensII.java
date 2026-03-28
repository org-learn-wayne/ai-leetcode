package leetcode;

public class Leet0052NQueensII {
    public int totalNQueens(int n) {
        return backtrack(0, n, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
    }

    private int backtrack(int row, int size, boolean[] columns, boolean[] diagonals, boolean[] antiDiagonals) {
        if (row == size) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < size; col++) {
            int diagonal = row - col + size;
            int antiDiagonal = row + col;
            if (columns[col] || diagonals[diagonal] || antiDiagonals[antiDiagonal]) {
                continue;
            }

            columns[col] = true;
            diagonals[diagonal] = true;
            antiDiagonals[antiDiagonal] = true;

            count += backtrack(row + 1, size, columns, diagonals, antiDiagonals);

            columns[col] = false;
            diagonals[diagonal] = false;
            antiDiagonals[antiDiagonal] = false;
        }

        return count;
    }
}
