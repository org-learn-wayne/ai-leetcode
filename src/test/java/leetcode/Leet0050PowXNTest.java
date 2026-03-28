package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0050PowXNTest {
    private final Leet0050PowXN solution = new Leet0050PowXN();

    @Test
    void handlesPositiveExponent() {
        assertEquals(1024.0, solution.myPow(2.0, 10), 1e-10);
    }

    @Test
    void handlesFractionalResult() {
        assertEquals(9.261, solution.myPow(2.1, 3), 1e-10);
    }

    @Test
    void handlesNegativeExponent() {
        assertEquals(0.25, solution.myPow(2.0, -2), 1e-10);
    }

    @Test
    void handlesMinIntegerExponent() {
        assertEquals(0.0, solution.myPow(2.0, Integer.MIN_VALUE), 0.0);
    }
}
