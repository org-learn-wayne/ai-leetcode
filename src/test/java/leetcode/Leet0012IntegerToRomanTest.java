package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0012IntegerToRomanTest {
    private final Leet0012IntegerToRoman solution = new Leet0012IntegerToRoman();

    @Test
    void convertsThree() {
        assertEquals("III", solution.intToRoman(3));
    }

    @Test
    void convertsSubtractiveCase() {
        assertEquals("IV", solution.intToRoman(4));
    }

    @Test
    void convertsMixedValue() {
        assertEquals("LVIII", solution.intToRoman(58));
    }

    @Test
    void convertsLargeExample() {
        assertEquals("MCMXCIV", solution.intToRoman(1994));
    }

    @Test
    void convertsUpperBoundStyleValue() {
        assertEquals("MMMCMXCIX", solution.intToRoman(3999));
    }
}
