package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0058LengthOfLastWordTest {
    private final Leet0058LengthOfLastWord solution = new Leet0058LengthOfLastWord();

    @Test
    void findsLengthOfLastWord() {
        assertEquals(5, solution.lengthOfLastWord("Hello World"));
    }

    @Test
    void ignoresTrailingSpaces() {
        assertEquals(4, solution.lengthOfLastWord("   fly me   to   the moon  "));
    }

    @Test
    void handlesSingleWord() {
        assertEquals(5, solution.lengthOfLastWord("luffy"));
    }
}
