package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0042TrappingRainWaterTest {
    private final Leet0042TrappingRainWater solution = new Leet0042TrappingRainWater();

    @Test
    void trapsWaterInTypicalLandscape() {
        assertEquals(6, solution.trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    @Test
    void handlesAnotherCommonExample() {
        assertEquals(9, solution.trap(new int[] {4, 2, 0, 3, 2, 5}));
    }

    @Test
    void returnsZeroForMonotonicHeights() {
        assertEquals(0, solution.trap(new int[] {1, 2, 3, 4}));
    }

    @Test
    void returnsZeroForTooShortArray() {
        assertEquals(0, solution.trap(new int[] {1, 0}));
    }
}
