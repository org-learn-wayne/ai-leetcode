package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0041FirstMissingPositiveTest {
    private final Leet0041FirstMissingPositive solution = new Leet0041FirstMissingPositive();

    @Test
    void findsMissingPositiveInMixedArray() {
        assertEquals(3, solution.firstMissingPositive(new int[] {1, 2, 0}));
    }

    @Test
    void skipsNegativesAndDuplicates() {
        assertEquals(2, solution.firstMissingPositive(new int[] {3, 4, -1, 1}));
    }

    @Test
    void returnsNextPositiveWhenPrefixComplete() {
        assertEquals(4, solution.firstMissingPositive(new int[] {1, 2, 3}));
    }

    @Test
    void handlesAllValuesOutOfRange() {
        assertEquals(1, solution.firstMissingPositive(new int[] {7, 8, 9, 11, 12}));
    }
}
