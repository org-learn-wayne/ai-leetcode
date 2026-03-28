package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0006ZigzagConversionTest {
    private final Leet0006ZigzagConversion solution = new Leet0006ZigzagConversion();

    @Test
    void convertsUsingThreeRows() {
        assertEquals("PAHNAPLSIIGYIR", solution.convert("PAYPALISHIRING", 3));
    }

    @Test
    void convertsUsingFourRows() {
        assertEquals("PINALSIGYAHRPI", solution.convert("PAYPALISHIRING", 4));
    }

    @Test
    void returnsOriginalWhenOneRow() {
        assertEquals("ABC", solution.convert("ABC", 1));
    }

    @Test
    void returnsOriginalWhenRowsExceedLength() {
        assertEquals("AB", solution.convert("AB", 5));
    }

    @Test
    void handlesTwoRows() {
        assertEquals("ACEBD", solution.convert("ABCDE", 2));
    }
}
