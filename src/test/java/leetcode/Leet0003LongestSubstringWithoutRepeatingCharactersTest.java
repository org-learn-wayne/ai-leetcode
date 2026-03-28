package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0003LongestSubstringWithoutRepeatingCharactersTest {
    private final Leet0003LongestSubstringWithoutRepeatingCharacters solution =
            new Leet0003LongestSubstringWithoutRepeatingCharacters();

    @Test
    void handlesRepeatedCharacters() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void handlesAllSameCharacter() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    void handlesSubstringResetInsideWindow() {
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void handlesEmptyString() {
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    void handlesRepeatedCharacterBeforeWindowStart() {
        assertEquals(2, solution.lengthOfLongestSubstring("abba"));
    }
}
