package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.Test;

public class Leet0005LongestPalindromicSubstringTest {
    private final Leet0005LongestPalindromicSubstring solution =
            new Leet0005LongestPalindromicSubstring();

    @Test
    void findsOddLengthPalindrome() {
        assertTrue(Set.of("bab", "aba").contains(solution.longestPalindrome("babad")));
    }

    @Test
    void findsEvenLengthPalindrome() {
        assertEquals("bb", solution.longestPalindrome("cbbd"));
    }

    @Test
    void handlesSingleCharacter() {
        assertEquals("a", solution.longestPalindrome("a"));
    }

    @Test
    void handlesEntireStringAsPalindrome() {
        assertEquals("racecar", solution.longestPalindrome("racecar"));
    }

    @Test
    void prefersLongestRunAroundCenter() {
        assertEquals("geeksskeeg", solution.longestPalindrome("forgeeksskeegfor"));
    }
}
