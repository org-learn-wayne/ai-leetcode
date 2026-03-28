package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Leet0020ValidParenthesesTest {
    private final Leet0020ValidParentheses solution = new Leet0020ValidParentheses();

    @Test
    void acceptsSimplePairSequence() {
        assertTrue(solution.isValid("()[]{}"));
    }

    @Test
    void rejectsMismatchedPairs() {
        assertFalse(solution.isValid("(]"));
    }

    @Test
    void rejectsIncorrectNesting() {
        assertFalse(solution.isValid("([)]"));
    }

    @Test
    void acceptsNestedPairs() {
        assertTrue(solution.isValid("{[]}"));
    }

    @Test
    void rejectsUnclosedOpeningBracket() {
        assertFalse(solution.isValid("("));
    }
}
