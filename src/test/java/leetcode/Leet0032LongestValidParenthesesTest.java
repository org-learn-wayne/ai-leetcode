package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0032LongestValidParenthesesTest {
    private final Leet0032LongestValidParentheses solution = new Leet0032LongestValidParentheses();

    @Test
    void findsLongestValidSubstringInMixedInput() {
        assertEquals(2, solution.longestValidParentheses("(()"));
    }

    @Test
    void handlesNestedAndAdjacentPairs() {
        assertEquals(6, solution.longestValidParentheses("()(())"));
    }

    @Test
    void handlesLeadingInvalidCloseParen() {
        assertEquals(4, solution.longestValidParentheses(")()())"));
    }

    @Test
    void returnsZeroForEmptyString() {
        assertEquals(0, solution.longestValidParentheses(""));
    }
}
