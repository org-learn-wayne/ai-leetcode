package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0014LongestCommonPrefixTest {
    private final Leet0014LongestCommonPrefix solution = new Leet0014LongestCommonPrefix();

    @Test
    void findsCommonPrefix() {
        assertEquals("fl", solution.longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
    }

    @Test
    void returnsEmptyWhenNoCommonPrefix() {
        assertEquals("", solution.longestCommonPrefix(new String[] {"dog", "racecar", "car"}));
    }

    @Test
    void handlesSingleElementArray() {
        assertEquals("solo", solution.longestCommonPrefix(new String[] {"solo"}));
    }

    @Test
    void handlesEmptyStringInArray() {
        assertEquals("", solution.longestCommonPrefix(new String[] {"", "b"}));
    }

    @Test
    void handlesEmptyInputArray() {
        assertEquals("", solution.longestCommonPrefix(new String[] {}));
    }
}
