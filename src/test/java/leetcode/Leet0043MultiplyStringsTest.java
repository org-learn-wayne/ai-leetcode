package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0043MultiplyStringsTest {
    private final Leet0043MultiplyStrings solution = new Leet0043MultiplyStrings();

    @Test
    void multipliesTypicalNumbers() {
        assertEquals("6", solution.multiply("2", "3"));
    }

    @Test
    void handlesCarryingAcrossDigits() {
        assertEquals("56088", solution.multiply("123", "456"));
    }

    @Test
    void returnsZeroWhenEitherFactorIsZero() {
        assertEquals("0", solution.multiply("0", "9133"));
    }

    @Test
    void handlesLargePowersOfTen() {
        assertEquals("1000000", solution.multiply("1000", "1000"));
    }
}
