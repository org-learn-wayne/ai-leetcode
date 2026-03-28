package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0053MaximumSubarrayTest {
    private final Leet0053MaximumSubarray solution = new Leet0053MaximumSubarray();

    @Test
    void findsBestMiddleSubarray() {
        assertEquals(6, solution.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    void handlesSingleValue() {
        assertEquals(1, solution.maxSubArray(new int[] {1}));
    }

    @Test
    void handlesAllNegativeValues() {
        assertEquals(-1, solution.maxSubArray(new int[] {-3, -2, -1, -5}));
    }
}
