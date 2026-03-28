package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0039CombinationSumTest {
    private final Leet0039CombinationSum solution = new Leet0039CombinationSum();

    @Test
    void findsAllCombinationsWithReuse() {
        assertEquals(
                List.of(List.of(2, 2, 3), List.of(7)),
                solution.combinationSum(new int[] {2, 3, 6, 7}, 7));
    }

    @Test
    void handlesMultipleCombinationLengths() {
        assertEquals(
                List.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5)),
                solution.combinationSum(new int[] {2, 3, 5}, 8));
    }

    @Test
    void returnsEmptyWhenTargetCannotBeReached() {
        assertEquals(List.of(), solution.combinationSum(new int[] {2}, 1));
    }
}
