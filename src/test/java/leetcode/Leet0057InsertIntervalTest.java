package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Leet0057InsertIntervalTest {
    private final Leet0057InsertInterval solution = new Leet0057InsertInterval();

    @Test
    void insertsAndMergesMiddleInterval() {
        assertArrayEquals(
                new int[][] {{1, 5}, {6, 9}},
                solution.insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}));
    }

    @Test
    void mergesAcrossSeveralIntervals() {
        assertArrayEquals(
                new int[][] {{1, 2}, {3, 10}, {12, 16}},
                solution.insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8}));
    }

    @Test
    void handlesEmptyInput() {
        assertArrayEquals(new int[][] {{5, 7}}, solution.insert(new int[][] {}, new int[] {5, 7}));
    }
}
