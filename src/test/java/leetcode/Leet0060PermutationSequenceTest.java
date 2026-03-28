package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0060PermutationSequenceTest {
    private final Leet0060PermutationSequence solution = new Leet0060PermutationSequence();

    @Test
    void findsThirdPermutationOfThreeNumbers() {
        assertEquals("213", solution.getPermutation(3, 3));
    }

    @Test
    void findsNinthPermutationOfFourNumbers() {
        assertEquals("2314", solution.getPermutation(4, 9));
    }

    @Test
    void handlesFirstPermutation() {
        assertEquals("123", solution.getPermutation(3, 1));
    }
}
