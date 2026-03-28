package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet0051NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(0, board, new boolean[n], new boolean[2 * n], new boolean[2 * n], solutions);
        return solutions;
    }

    private void backtrack(int row, char[][] board, boolean[] columns,
                           boolean[] diagonals, boolean[] antiDiagonals,
                           List<List<String>> solutions) {
        int size = board.length;
        if (row == size) {
            List<String> solution = new ArrayList<>(size);
            for (char[] boardRow : board) {
                solution.add(new String(boardRow));
            }
            solutions.add(solution);
            return;
        }

        for (int col = 0; col < size; col++) {
            int diagonal = row - col + size;
            int antiDiagonal = row + col;
            if (columns[col] || diagonals[diagonal] || antiDiagonals[antiDiagonal]) {
                continue;
            }

            columns[col] = true;
            diagonals[diagonal] = true;
            antiDiagonals[antiDiagonal] = true;
            board[row][col] = 'Q';

            backtrack(row + 1, board, columns, diagonals, antiDiagonals, solutions);

            board[row][col] = '.';
            columns[col] = false;
            diagonals[diagonal] = false;
            antiDiagonals[antiDiagonal] = false;
        }
    }
}
