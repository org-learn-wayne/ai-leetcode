package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0040CombinationSumIITest {
    private final Leet0040CombinationSumII solution = new Leet0040CombinationSumII();

    @Test
    void avoidsDuplicateCombinations() {
        assertEquals(
                List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6)),
                solution.combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
    }

    @Test
    void handlesRepeatedCandidatesWithSingleUniqueAnswer() {
        assertEquals(
                List.of(List.of(1, 2, 2), List.of(5)),
                solution.combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
    }

    @Test
    void returnsEmptyWhenNoCombinationMatches() {
        assertEquals(List.of(), solution.combinationSum2(new int[] {4, 6, 8}, 5));
    }
}
