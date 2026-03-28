package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0029DivideTwoIntegersTest {
    private final Leet0029DivideTwoIntegers solution = new Leet0029DivideTwoIntegers();

    @Test
    void dividesPositiveNumbers() {
        assertEquals(3, solution.divide(10, 3));
    }

    @Test
    void dividesWithNegativeResult() {
        assertEquals(-2, solution.divide(7, -3));
    }

    @Test
    void handlesOverflowCase() {
        assertEquals(Integer.MAX_VALUE, solution.divide(Integer.MIN_VALUE, -1));
    }

    @Test
    void handlesExactDivision() {
        assertEquals(4, solution.divide(16, 4));
    }
}
