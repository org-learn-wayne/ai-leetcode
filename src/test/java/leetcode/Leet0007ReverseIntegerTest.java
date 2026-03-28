package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0007ReverseIntegerTest {
    private final Leet0007ReverseInteger solution = new Leet0007ReverseInteger();

    @Test
    void reversesPositiveNumber() {
        assertEquals(321, solution.reverse(123));
    }

    @Test
    void reversesNegativeNumber() {
        assertEquals(-321, solution.reverse(-123));
    }

    @Test
    void removesLeadingZeroesAfterReverse() {
        assertEquals(21, solution.reverse(120));
    }

    @Test
    void returnsZeroOnPositiveOverflow() {
        assertEquals(0, solution.reverse(1534236469));
    }

    @Test
    void returnsZeroOnNegativeOverflow() {
        assertEquals(0, solution.reverse(-1563847412));
    }
}
