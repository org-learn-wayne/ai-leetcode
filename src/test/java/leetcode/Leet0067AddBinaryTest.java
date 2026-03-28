package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0067AddBinaryTest {
    private final Leet0067AddBinary solution = new Leet0067AddBinary();

    @Test
    void addsBinaryStrings() {
        assertEquals("100", solution.addBinary("11", "1"));
    }

    @Test
    void handlesDifferentLengths() {
        assertEquals("10101", solution.addBinary("1010", "1011"));
    }

    @Test
    void handlesZeroInputs() {
        assertEquals("0", solution.addBinary("0", "0"));
    }
}
