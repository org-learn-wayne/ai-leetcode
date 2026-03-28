package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0051NQueensTest {
    private final Leet0051NQueens solution = new Leet0051NQueens();

    @Test
    void findsBothSolutionsForFourQueens() {
        assertEquals(
                normalize(List.of(
                        List.of(".Q..", "...Q", "Q...", "..Q."),
                        List.of("..Q.", "Q...", "...Q", ".Q.."))),
                normalize(solution.solveNQueens(4)));
    }

    @Test
    void handlesSingleQueenBoard() {
        assertEquals(List.of(List.of("Q")), solution.solveNQueens(1));
    }

    private List<List<String>> normalize(List<List<String>> solutions) {
        return solutions.stream()
                .sorted(Comparator.comparing(solution -> String.join("|", solution)))
                .toList();
    }
}
