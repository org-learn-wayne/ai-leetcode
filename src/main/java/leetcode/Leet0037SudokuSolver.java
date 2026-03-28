package leetcode;

public class Leet0037SudokuSolver {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    continue;
                }

                for (char digit = '1'; digit <= '9'; digit++) {
                    if (!isValid(board, row, col, digit)) {
                        continue;
                    }

                    board[row][col] = digit;
                    if (solve(board)) {
                        return true;
                    }
                    board[row][col] = '.';
                }

                return false;
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char digit) {
        for (int index = 0; index < 9; index++) {
            if (board[row][index] == digit || board[index][col] == digit) {
                return false;
            }
        }

        int boxRow = row / 3 * 3;
        int boxCol = col / 3 * 3;
        for (int currentRow = boxRow; currentRow < boxRow + 3; currentRow++) {
            for (int currentCol = boxCol; currentCol < boxCol + 3; currentCol++) {
                if (board[currentRow][currentCol] == digit) {
                    return false;
                }
            }
        }

        return true;
    }
}
