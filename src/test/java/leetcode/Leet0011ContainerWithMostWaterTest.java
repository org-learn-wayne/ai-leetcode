package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0011ContainerWithMostWaterTest {
    private final Leet0011ContainerWithMostWater solution = new Leet0011ContainerWithMostWater();

    @Test
    void findsStandardExample() {
        assertEquals(49, solution.maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    @Test
    void handlesTwoBars() {
        assertEquals(1, solution.maxArea(new int[] {1, 1}));
    }

    @Test
    void handlesDescendingHeights() {
        assertEquals(16, solution.maxArea(new int[] {4, 3, 2, 1, 4}));
    }

    @Test
    void handlesInternalMaximum() {
        assertEquals(20, solution.maxArea(new int[] {4, 3, 2, 1, 4, 5}));
    }
}
