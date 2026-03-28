package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0035SearchInsertPositionTest {
    private final Leet0035SearchInsertPosition solution = new Leet0035SearchInsertPosition();

    @Test
    void returnsExistingIndexWhenTargetFound() {
        assertEquals(2, solution.searchInsert(new int[] {1, 3, 5, 6}, 5));
    }

    @Test
    void returnsInsertionPointInsideArray() {
        assertEquals(1, solution.searchInsert(new int[] {1, 3, 5, 6}, 2));
    }

    @Test
    void returnsInsertionPointAtEnd() {
        assertEquals(4, solution.searchInsert(new int[] {1, 3, 5, 6}, 7));
    }

    @Test
    void returnsInsertionPointAtBeginning() {
        assertEquals(0, solution.searchInsert(new int[] {1, 3, 5, 6}, 0));
    }
}
