package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0064MinimumPathSumTest {
    private final Leet0064MinimumPathSum solution = new Leet0064MinimumPathSum();

    @Test
    void findsMinimumPathInTypicalGrid() {
        assertEquals(7, solution.minPathSum(new int[][] {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }

    @Test
    void handlesRectangularGrid() {
        assertEquals(12, solution.minPathSum(new int[][] {
                {1, 2, 3},
                {4, 5, 6}
        }));
    }

    @Test
    void handlesSingleCell() {
        assertEquals(5, solution.minPathSum(new int[][] {{5}}));
    }
}
