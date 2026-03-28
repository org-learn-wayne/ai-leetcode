package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Leet0031NextPermutationTest {
    private final Leet0031NextPermutation solution = new Leet0031NextPermutation();

    @Test
    void generatesNextPermutationForTypicalCase() {
        int[] nums = {1, 2, 3};

        solution.nextPermutation(nums);

        assertArrayEquals(new int[] {1, 3, 2}, nums);
    }

    @Test
    void wrapsDescendingArrayToSmallestPermutation() {
        int[] nums = {3, 2, 1};

        solution.nextPermutation(nums);

        assertArrayEquals(new int[] {1, 2, 3}, nums);
    }

    @Test
    void handlesDuplicatesCorrectly() {
        int[] nums = {1, 1, 5};

        solution.nextPermutation(nums);

        assertArrayEquals(new int[] {1, 5, 1}, nums);
    }

    @Test
    void updatesPivotNearFront() {
        int[] nums = {1, 3, 2};

        solution.nextPermutation(nums);

        assertArrayEquals(new int[] {2, 1, 3}, nums);
    }
}
