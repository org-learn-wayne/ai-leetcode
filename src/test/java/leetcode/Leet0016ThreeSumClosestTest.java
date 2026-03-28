package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0016ThreeSumClosestTest {
    private final Leet0016ThreeSumClosest solution = new Leet0016ThreeSumClosest();

    @Test
    void findsClosestSumForStandardCase() {
        assertEquals(2, solution.threeSumClosest(new int[] {-1, 2, 1, -4}, 1));
    }

    @Test
    void handlesExactMatch() {
        assertEquals(0, solution.threeSumClosest(new int[] {0, 0, 0}, 1));
    }

    @Test
    void handlesNegativeTarget() {
        assertEquals(-2, solution.threeSumClosest(new int[] {-3, -2, -5, 3, -4}, -1));
    }
}
