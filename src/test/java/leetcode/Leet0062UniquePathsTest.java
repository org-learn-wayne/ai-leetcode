package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0062UniquePathsTest {
    private final Leet0062UniquePaths solution = new Leet0062UniquePaths();

    @Test
    void countsTypicalGridPaths() {
        assertEquals(28, solution.uniquePaths(3, 7));
    }

    @Test
    void handlesSmallSquareGrid() {
        assertEquals(6, solution.uniquePaths(3, 3));
    }

    @Test
    void handlesSingleRow() {
        assertEquals(1, solution.uniquePaths(1, 5));
    }
}
