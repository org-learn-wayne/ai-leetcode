package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0008StringToIntegerAtoiTest {
    private final Leet0008StringToIntegerAtoi solution = new Leet0008StringToIntegerAtoi();

    @Test
    void parsesPositiveNumberWithLeadingWhitespace() {
        assertEquals(42, solution.myAtoi("   42"));
    }

    @Test
    void parsesNegativeNumber() {
        assertEquals(-42, solution.myAtoi("   -42"));
    }

    @Test
    void stopsParsingAtFirstNonDigit() {
        assertEquals(4193, solution.myAtoi("4193 with words"));
    }

    @Test
    void returnsZeroWhenStringDoesNotStartWithNumber() {
        assertEquals(0, solution.myAtoi("words and 987"));
    }

    @Test
    void clampsToMaxValueOnOverflow() {
        assertEquals(Integer.MAX_VALUE, solution.myAtoi("91283472332"));
    }

    @Test
    void clampsToMinValueOnUnderflow() {
        assertEquals(Integer.MIN_VALUE, solution.myAtoi("-91283472332"));
    }

    @Test
    void rejectsSignSequenceWithoutDigits() {
        assertEquals(0, solution.myAtoi("+-12"));
    }
}
