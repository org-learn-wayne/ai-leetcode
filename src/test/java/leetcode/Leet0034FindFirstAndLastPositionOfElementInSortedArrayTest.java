package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Leet0034FindFirstAndLastPositionOfElementInSortedArrayTest {
    private final Leet0034FindFirstAndLastPositionOfElementInSortedArray solution =
            new Leet0034FindFirstAndLastPositionOfElementInSortedArray();

    @Test
    void findsRangeForRepeatedTarget() {
        assertArrayEquals(new int[] {3, 4}, solution.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8));
    }

    @Test
    void returnsMissingRangeWhenTargetNotPresent() {
        assertArrayEquals(new int[] {-1, -1}, solution.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6));
    }

    @Test
    void handlesEmptyArray() {
        assertArrayEquals(new int[] {-1, -1}, solution.searchRange(new int[] {}, 0));
    }

    @Test
    void handlesArrayWhereAllValuesMatch() {
        assertArrayEquals(new int[] {0, 3}, solution.searchRange(new int[] {2, 2, 2, 2}, 2));
    }
}
