package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0018FourSumTest {
    private final Leet0018FourSum solution = new Leet0018FourSum();

    @Test
    void findsAllUniqueQuadruplets() {
        List<List<Integer>> expected = List.of(
                List.of(-2, -1, 1, 2),
                List.of(-2, 0, 0, 2),
                List.of(-1, 0, 0, 1));

        assertEquals(normalize(expected), normalize(solution.fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0)));
    }

    @Test
    void handlesAllSameValues() {
        assertEquals(List.of(List.of(2, 2, 2, 2)), solution.fourSum(new int[] {2, 2, 2, 2, 2}, 8));
    }

    @Test
    void returnsEmptyWhenNoQuadrupletExists() {
        assertEquals(List.of(), solution.fourSum(new int[] {1, 2, 3, 4}, 100));
    }

    private List<List<Integer>> normalize(List<List<Integer>> values) {
        List<List<Integer>> normalized = new ArrayList<>(values);
        normalized.sort(Comparator.comparingInt((List<Integer> item) -> item.get(0))
                .thenComparingInt(item -> item.get(1))
                .thenComparingInt(item -> item.get(2))
                .thenComparingInt(item -> item.get(3)));
        return normalized;
    }
}
