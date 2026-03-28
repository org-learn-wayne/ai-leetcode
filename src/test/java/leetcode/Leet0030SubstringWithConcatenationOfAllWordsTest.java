package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0030SubstringWithConcatenationOfAllWordsTest {
    private final Leet0030SubstringWithConcatenationOfAllWords solution =
            new Leet0030SubstringWithConcatenationOfAllWords();

    @Test
    void findsMultipleValidStartingIndices() {
        assertEquals(List.of(0, 9), solution.findSubstring("barfoothefoobarman", new String[] {"foo", "bar"}));
    }

    @Test
    void handlesOverlappingWordsWithNoMatch() {
        assertEquals(List.of(), solution.findSubstring("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "word"}));
    }

    @Test
    void handlesRepeatedWords() {
        assertEquals(List.of(8), solution.findSubstring("wordgoodgoodgoodbestword", new String[] {"good", "good", "best", "word"}));
    }

    @Test
    void handlesEmptySourceString() {
        assertEquals(List.of(), solution.findSubstring("", new String[] {"a"}));
    }
}
