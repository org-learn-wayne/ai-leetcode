package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0004MedianOfTwoSortedArraysTest {
    private final Leet0004MedianOfTwoSortedArrays solution =
            new Leet0004MedianOfTwoSortedArrays();

    @Test
    void findsMedianForOddCombinedLength() {
        assertEquals(2.0, solution.findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
    }

    @Test
    void findsMedianForEvenCombinedLength() {
        assertEquals(2.5, solution.findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }

    @Test
    void handlesEmptyFirstArray() {
        assertEquals(1.0, solution.findMedianSortedArrays(new int[] {}, new int[] {1}));
    }

    @Test
    void handlesEmptySecondArray() {
        assertEquals(2.0, solution.findMedianSortedArrays(new int[] {2}, new int[] {}));
    }

    @Test
    void handlesZerosAndDuplicates() {
        assertEquals(0.0, solution.findMedianSortedArrays(new int[] {0, 0}, new int[] {0, 0}));
    }
}
