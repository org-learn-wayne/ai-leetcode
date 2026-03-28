package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0046PermutationsTest {
    private final Leet0046Permutations solution = new Leet0046Permutations();

    @Test
    void generatesAllPermutations() {
        assertEquals(
                List.of(
                        List.of(1, 2, 3),
                        List.of(1, 3, 2),
                        List.of(2, 1, 3),
                        List.of(2, 3, 1),
                        List.of(3, 2, 1),
                        List.of(3, 1, 2)),
                solution.permute(new int[] {1, 2, 3}));
    }

    @Test
    void handlesSingleElementInput() {
        assertEquals(List.of(List.of(1)), solution.permute(new int[] {1}));
    }
}
