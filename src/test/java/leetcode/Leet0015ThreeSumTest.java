package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0015ThreeSumTest {
    private final Leet0015ThreeSum solution = new Leet0015ThreeSum();

    @Test
    void findsAllUniqueTriplets() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(-1, -1, 2));
        expected.add(List.of(-1, 0, 1));

        assertEquals(normalize(expected), normalize(solution.threeSum(new int[] {-1, 0, 1, 2, -1, -4})));
    }

    @Test
    void returnsEmptyWhenNoTripletExists() {
        assertEquals(List.of(), solution.threeSum(new int[] {0, 1, 1}));
    }

    @Test
    void handlesAllZerosWithoutDuplicates() {
        assertEquals(List.of(List.of(0, 0, 0)), solution.threeSum(new int[] {0, 0, 0, 0}));
    }

    @Test
    void handlesMixedDuplicateValues() {
        List<List<Integer>> expected = List.of(List.of(-2, 0, 2), List.of(-2, 1, 1));
        assertEquals(normalize(expected), normalize(solution.threeSum(new int[] {-2, 0, 1, 1, 2})));
    }

    private List<List<Integer>> normalize(List<List<Integer>> values) {
        List<List<Integer>> normalized = new ArrayList<>(values);
        normalized.sort(Comparator.comparingInt((List<Integer> item) -> item.get(0))
                .thenComparingInt(item -> item.get(1))
                .thenComparingInt(item -> item.get(2)));
        return normalized;
    }
}
