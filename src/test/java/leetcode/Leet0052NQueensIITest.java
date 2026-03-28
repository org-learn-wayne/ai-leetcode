package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0052NQueensIITest {
    private final Leet0052NQueensII solution = new Leet0052NQueensII();

    @Test
    void countsSolutionsForFourQueens() {
        assertEquals(2, solution.totalNQueens(4));
    }

    @Test
    void countsSingleBoardSolution() {
        assertEquals(1, solution.totalNQueens(1));
    }

    @Test
    void handlesBoardWithNoSolutions() {
        assertEquals(0, solution.totalNQueens(3));
    }
}
