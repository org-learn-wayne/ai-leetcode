package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Leet0044WildcardMatchingTest {
    private final Leet0044WildcardMatching solution = new Leet0044WildcardMatching();

    @Test
    void rejectsNonMatchingLiteralPattern() {
        assertFalse(solution.isMatch("aa", "a"));
    }

    @Test
    void acceptsCatchAllStarPattern() {
        assertTrue(solution.isMatch("aa", "*"));
    }

    @Test
    void handlesMixedQuestionAndStarPattern() {
        assertFalse(solution.isMatch("cb", "?a"));
    }

    @Test
    void matchesComplexPatternWithBacktracking() {
        assertTrue(solution.isMatch("adceb", "*a*b"));
    }

    @Test
    void rejectsComplexPatternThatCannotCoverWholeString() {
        assertFalse(solution.isMatch("acdcb", "a*c?b"));
    }
}
