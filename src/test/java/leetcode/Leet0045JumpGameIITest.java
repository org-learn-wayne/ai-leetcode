package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0045JumpGameIITest {
    private final Leet0045JumpGameII solution = new Leet0045JumpGameII();

    @Test
    void findsMinimumJumpsForTypicalCase() {
        assertEquals(2, solution.jump(new int[] {2, 3, 1, 1, 4}));
    }

    @Test
    void handlesAnotherReachableCase() {
        assertEquals(2, solution.jump(new int[] {2, 3, 0, 1, 4}));
    }

    @Test
    void handlesSingleElementArray() {
        assertEquals(0, solution.jump(new int[] {0}));
    }

    @Test
    void handlesLargeInitialJump() {
        assertEquals(1, solution.jump(new int[] {5, 1, 1, 1, 1, 1}));
    }
}
