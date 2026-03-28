package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0063UniquePathsIITest {
    private final Leet0063UniquePathsII solution = new Leet0063UniquePathsII();

    @Test
    void countsPathsAroundObstacle() {
        assertEquals(2, solution.uniquePathsWithObstacles(new int[][] {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }));
    }

    @Test
    void handlesBlockedStart() {
        assertEquals(0, solution.uniquePathsWithObstacles(new int[][] {{1}}));
    }

    @Test
    void handlesSingleCellWithoutObstacle() {
        assertEquals(1, solution.uniquePathsWithObstacles(new int[][] {{0}}));
    }
}
