package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Leet0059SpiralMatrixIITest {
    private final Leet0059SpiralMatrixII solution = new Leet0059SpiralMatrixII();

    @Test
    void generatesThreeByThreeSpiralMatrix() {
        assertArrayEquals(
                new int[][] {
                        {1, 2, 3},
                        {8, 9, 4},
                        {7, 6, 5}
                },
                solution.generateMatrix(3));
    }

    @Test
    void generatesSingleValueMatrix() {
        assertArrayEquals(new int[][] {{1}}, solution.generateMatrix(1));
    }
}
