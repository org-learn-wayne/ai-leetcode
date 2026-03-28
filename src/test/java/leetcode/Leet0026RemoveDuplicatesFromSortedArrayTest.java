package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0026RemoveDuplicatesFromSortedArrayTest {
    private final Leet0026RemoveDuplicatesFromSortedArray solution =
            new Leet0026RemoveDuplicatesFromSortedArray();

    @Test
    void removesDuplicatesFromTypicalInput() {
        int[] nums = {1, 1, 2};
        int length = solution.removeDuplicates(nums);

        assertEquals(2, length);
        assertArrayEquals(new int[] {1, 2}, java.util.Arrays.copyOf(nums, length));
    }

    @Test
    void handlesLongerInputWithManyDuplicates() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int length = solution.removeDuplicates(nums);

        assertEquals(5, length);
        assertArrayEquals(new int[] {0, 1, 2, 3, 4}, java.util.Arrays.copyOf(nums, length));
    }

    @Test
    void handlesEmptyArray() {
        assertEquals(0, solution.removeDuplicates(new int[] {}));
    }
}
