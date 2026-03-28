package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Leet0055JumpGameTest {
    private final Leet0055JumpGame solution = new Leet0055JumpGame();

    @Test
    void returnsTrueForReachableCase() {
        assertTrue(solution.canJump(new int[] {2, 3, 1, 1, 4}));
    }

    @Test
    void returnsFalseWhenBlockedByZero() {
        assertFalse(solution.canJump(new int[] {3, 2, 1, 0, 4}));
    }

    @Test
    void handlesSingleElementArray() {
        assertTrue(solution.canJump(new int[] {0}));
    }
}
