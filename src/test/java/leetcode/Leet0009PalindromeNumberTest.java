package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Leet0009PalindromeNumberTest {
    private final Leet0009PalindromeNumber solution = new Leet0009PalindromeNumber();

    @Test
    void identifiesPalindromeNumber() {
        assertTrue(solution.isPalindrome(121));
    }

    @Test
    void rejectsNegativeNumber() {
        assertFalse(solution.isPalindrome(-121));
    }

    @Test
    void rejectsNumberEndingInZero() {
        assertFalse(solution.isPalindrome(10));
    }

    @Test
    void acceptsZero() {
        assertTrue(solution.isPalindrome(0));
    }

    @Test
    void identifiesEvenLengthPalindrome() {
        assertTrue(solution.isPalindrome(1221));
    }
}
