package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0013RomanToIntegerTest {
    private final Leet0013RomanToInteger solution = new Leet0013RomanToInteger();

    @Test
    void convertsBasicNumeral() {
        assertEquals(3, solution.romanToInt("III"));
    }

    @Test
    void convertsSubtractiveNumeral() {
        assertEquals(4, solution.romanToInt("IV"));
    }

    @Test
    void convertsMixedNumeral() {
        assertEquals(58, solution.romanToInt("LVIII"));
    }

    @Test
    void convertsComplexNumeral() {
        assertEquals(1994, solution.romanToInt("MCMXCIV"));
    }

    @Test
    void convertsSingleLargestSymbol() {
        assertEquals(1000, solution.romanToInt("M"));
    }
}
