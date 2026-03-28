package leetcode;

public class Leet0036ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char value = board[row][col];
                if (value == '.') {
                    continue;
                }

                int digit = value - '1';
                int box = (row / 3) * 3 + col / 3;
                if (rows[row][digit] || cols[col][digit] || boxes[box][digit]) {
                    return false;
                }

                rows[row][digit] = true;
                cols[col][digit] = true;
                boxes[box][digit] = true;
            }
        }

        return true;
    }
}
