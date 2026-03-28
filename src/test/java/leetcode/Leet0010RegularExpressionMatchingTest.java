package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Leet0010RegularExpressionMatchingTest {
    private final Leet0010RegularExpressionMatching solution =
            new Leet0010RegularExpressionMatching();

    @Test
    void rejectsIncompleteLiteralMatch() {
        assertFalse(solution.isMatch("aa", "a"));
    }

    @Test
    void acceptsStarRepeat() {
        assertTrue(solution.isMatch("aa", "a*"));
    }

    @Test
    void acceptsWildcardDotStar() {
        assertTrue(solution.isMatch("ab", ".*"));
    }

    @Test
    void handlesComplexFalseCase() {
        assertFalse(solution.isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    void handlesComplexTrueCase() {
        assertTrue(solution.isMatch("aab", "c*a*b"));
    }

    @Test
    void handlesEmptyStringAgainstStarPattern() {
        assertTrue(solution.isMatch("", "a*b*c*"));
    }
}
