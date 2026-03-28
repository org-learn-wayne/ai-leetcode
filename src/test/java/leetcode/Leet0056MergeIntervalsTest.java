package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Leet0056MergeIntervalsTest {
    private final Leet0056MergeIntervals solution = new Leet0056MergeIntervals();

    @Test
    void mergesOverlappingIntervals() {
        assertArrayEquals(
                new int[][] {{1, 6}, {8, 10}, {15, 18}},
                solution.merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
    }

    @Test
    void mergesTouchingIntervals() {
        assertArrayEquals(
                new int[][] {{1, 5}},
                solution.merge(new int[][] {{1, 4}, {4, 5}}));
    }

    @Test
    void handlesSingleInterval() {
        assertArrayEquals(new int[][] {{1, 4}}, solution.merge(new int[][] {{1, 4}}));
    }
}
