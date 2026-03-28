package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Leet0065ValidNumberTest {
    private final Leet0065ValidNumber solution = new Leet0065ValidNumber();

    @Test
    void acceptsCommonValidNumbers() {
        assertTrue(solution.isNumber("2"));
        assertTrue(solution.isNumber("0089"));
        assertTrue(solution.isNumber("-0.1"));
        assertTrue(solution.isNumber("+3.14"));
        assertTrue(solution.isNumber("4."));
        assertTrue(solution.isNumber("-.9"));
        assertTrue(solution.isNumber("2e10"));
        assertTrue(solution.isNumber("-90E3"));
        assertTrue(solution.isNumber("3e+7"));
    }

    @Test
    void rejectsInvalidNumbers() {
        assertFalse(solution.isNumber("abc"));
        assertFalse(solution.isNumber("1a"));
        assertFalse(solution.isNumber("1e"));
        assertFalse(solution.isNumber("e3"));
        assertFalse(solution.isNumber("99e2.5"));
        assertFalse(solution.isNumber("--6"));
        assertFalse(solution.isNumber("-+3"));
        assertFalse(solution.isNumber("95a54e53"));
    }

    @Test
    void handlesTrimmedWhitespace() {
        assertTrue(solution.isNumber(" 6e-1 "));
    }
}
