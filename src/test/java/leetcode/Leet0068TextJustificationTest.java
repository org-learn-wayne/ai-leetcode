package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0068TextJustificationTest {
    private final Leet0068TextJustification solution = new Leet0068TextJustification();

    @Test
    void justifiesTypicalParagraph() {
        assertEquals(
                List.of(
                        "This    is    an",
                        "example  of text",
                        "justification.  "),
                solution.fullJustify(
                        new String[] {"This", "is", "an", "example", "of", "text", "justification."},
                        16));
    }

    @Test
    void leftJustifiesLastLine() {
        assertEquals(
                List.of(
                        "What   must   be",
                        "acknowledgment  ",
                        "shall be        "),
                solution.fullJustify(
                        new String[] {"What", "must", "be", "acknowledgment", "shall", "be"},
                        16));
    }

    @Test
    void handlesSingleWordLine() {
        assertEquals(
                List.of("Longword  "),
                solution.fullJustify(new String[] {"Longword"}, 10));
    }
}
