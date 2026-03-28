package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0054SpiralMatrixTest {
    private final Leet0054SpiralMatrix solution = new Leet0054SpiralMatrix();

    @Test
    void traversesSquareMatrixInSpiralOrder() {
        assertEquals(
                List.of(1, 2, 3, 6, 9, 8, 7, 4, 5),
                solution.spiralOrder(new int[][] {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                }));
    }

    @Test
    void traversesRectangularMatrixInSpiralOrder() {
        assertEquals(
                List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
                solution.spiralOrder(new int[][] {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}
                }));
    }

    @Test
    void handlesSingleRowMatrix() {
        assertEquals(List.of(1, 2, 3), solution.spiralOrder(new int[][] {{1, 2, 3}}));
    }
}
