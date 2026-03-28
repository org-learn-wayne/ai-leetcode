package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0038CountAndSayTest {
    private final Leet0038CountAndSay solution = new Leet0038CountAndSay();

    @Test
    void returnsBaseCase() {
        assertEquals("1", solution.countAndSay(1));
    }

    @Test
    void buildsSequenceForFourthTerm() {
        assertEquals("1211", solution.countAndSay(4));
    }

    @Test
    void handlesLongerSequence() {
        assertEquals("111221", solution.countAndSay(5));
    }
}
