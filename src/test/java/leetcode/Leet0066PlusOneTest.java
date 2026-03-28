package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Leet0066PlusOneTest {
    private final Leet0066PlusOne solution = new Leet0066PlusOne();

    @Test
    void incrementsTypicalNumber() {
        assertArrayEquals(new int[] {1, 2, 4}, solution.plusOne(new int[] {1, 2, 3}));
    }

    @Test
    void carriesAcrossTrailingNines() {
        assertArrayEquals(new int[] {4, 3, 2, 2}, solution.plusOne(new int[] {4, 3, 2, 1}));
    }

    @Test
    void expandsArrayWhenAllDigitsAreNine() {
        assertArrayEquals(new int[] {1, 0, 0, 0}, solution.plusOne(new int[] {9, 9, 9}));
    }
}
