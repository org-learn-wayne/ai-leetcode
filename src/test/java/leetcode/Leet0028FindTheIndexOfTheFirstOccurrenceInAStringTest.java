package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0028FindTheIndexOfTheFirstOccurrenceInAStringTest {
    private final Leet0028FindTheIndexOfTheFirstOccurrenceInAString solution =
            new Leet0028FindTheIndexOfTheFirstOccurrenceInAString();

    @Test
    void findsSubstringAtBeginning() {
        assertEquals(0, solution.strStr("sadbutsad", "sad"));
    }

    @Test
    void returnsNegativeWhenMissing() {
        assertEquals(-1, solution.strStr("leetcode", "leeto"));
    }

    @Test
    void handlesEmptyNeedle() {
        assertEquals(0, solution.strStr("abc", ""));
    }

    @Test
    void findsSubstringInMiddle() {
        assertEquals(2, solution.strStr("hello", "ll"));
    }
}
