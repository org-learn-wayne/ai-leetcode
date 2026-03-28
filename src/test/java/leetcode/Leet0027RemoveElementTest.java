package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0027RemoveElementTest {
    private final Leet0027RemoveElement solution = new Leet0027RemoveElement();

    @Test
    void removesMatchingValues() {
        int[] nums = {3, 2, 2, 3};
        int length = solution.removeElement(nums, 3);

        assertEquals(2, length);
        assertArrayEquals(new int[] {2, 2}, java.util.Arrays.copyOf(nums, length));
    }

    @Test
    void handlesMixedOccurrences() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int length = solution.removeElement(nums, 2);

        assertEquals(5, length);
        assertArrayEquals(new int[] {0, 1, 3, 0, 4}, java.util.Arrays.copyOf(nums, length));
    }

    @Test
    void leavesArrayUntouchedWhenValueMissing() {
        int[] nums = {1, 2, 3};
        int length = solution.removeElement(nums, 4);

        assertEquals(3, length);
        assertArrayEquals(new int[] {1, 2, 3}, java.util.Arrays.copyOf(nums, length));
    }
}
