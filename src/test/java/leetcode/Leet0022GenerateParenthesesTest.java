package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0022GenerateParenthesesTest {
    private final Leet0022GenerateParentheses solution = new Leet0022GenerateParentheses();

    @Test
    void generatesAllCombinationsForThreePairs() {
        List<String> expected = List.of("((()))", "(()())", "(())()", "()(())", "()()()");
        assertEquals(sorted(expected), sorted(solution.generateParenthesis(3)));
    }

    @Test
    void handlesSinglePair() {
        assertEquals(List.of("()"), solution.generateParenthesis(1));
    }

    @Test
    void handlesZeroPairs() {
        assertEquals(List.of(""), solution.generateParenthesis(0));
    }

    private List<String> sorted(List<String> values) {
        List<String> copy = new ArrayList<>(values);
        Collections.sort(copy);
        return copy;
    }
}
