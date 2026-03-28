package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0070ClimbingStairsTest {
    private final Leet0070ClimbingStairs solution = new Leet0070ClimbingStairs();

    @Test
    void handlesSmallInput() {
        assertEquals(2, solution.climbStairs(2));
    }

    @Test
    void handlesThreeSteps() {
        assertEquals(3, solution.climbStairs(3));
    }

    @Test
    void handlesLargerInput() {
        assertEquals(89, solution.climbStairs(10));
    }
}
