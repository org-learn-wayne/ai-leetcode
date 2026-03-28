package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Leet0001TwoSumsTest {
    private final Leet0001TwoSums solution = new Leet0001TwoSums();

    @Test
    void returnsIndicesForBasicCase() {
        assertArrayEquals(new int[] {0, 1}, solution.twoSum(new int[] {2, 7, 11, 15}, 9));
    }

    @Test
    void returnsIndicesWhenAnswerIsLaterInArray() {
        assertArrayEquals(new int[] {1, 2}, solution.twoSum(new int[] {3, 2, 4}, 6));
    }

    @Test
    void returnsIndicesForDuplicateValues() {
        assertArrayEquals(new int[] {0, 1}, solution.twoSum(new int[] {3, 3}, 6));
    }

    @Test
    void throwsWhenNoSolutionExists() {
        assertThrows(IllegalArgumentException.class, () -> solution.twoSum(new int[] {1, 2, 3}, 7));
    }
}
