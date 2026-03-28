package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0033SearchInRotatedSortedArrayTest {
    private final Leet0033SearchInRotatedSortedArray solution = new Leet0033SearchInRotatedSortedArray();

    @Test
    void findsTargetInRotatedArray() {
        assertEquals(4, solution.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
    }

    @Test
    void returnsMinusOneWhenTargetMissing() {
        assertEquals(-1, solution.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
    }

    @Test
    void handlesSingleElementArray() {
        assertEquals(-1, solution.search(new int[] {1}, 0));
    }

    @Test
    void findsTargetWhenArrayIsNotRotated() {
        assertEquals(2, solution.search(new int[] {1, 2, 3, 4, 5}, 3));
    }
}
