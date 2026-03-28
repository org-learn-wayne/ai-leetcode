package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0047PermutationsIITest {
    private final Leet0047PermutationsII solution = new Leet0047PermutationsII();

    @Test
    void avoidsDuplicatePermutations() {
        assertEquals(
                List.of(
                        List.of(1, 1, 2),
                        List.of(1, 2, 1),
                        List.of(2, 1, 1)),
                solution.permuteUnique(new int[] {1, 1, 2}));
    }

    @Test
    void handlesAllValuesEqual() {
        assertEquals(List.of(List.of(2, 2, 2)), solution.permuteUnique(new int[] {2, 2, 2}));
    }
}
