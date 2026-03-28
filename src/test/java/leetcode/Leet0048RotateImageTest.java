package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Leet0048RotateImageTest {
    private final Leet0048RotateImage solution = new Leet0048RotateImage();

    @Test
    void rotatesThreeByThreeMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        solution.rotate(matrix);

        assertArrayEquals(new int[][] {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        }, matrix);
    }

    @Test
    void rotatesFourByFourMatrix() {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        solution.rotate(matrix);

        assertArrayEquals(new int[][] {
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        }, matrix);
    }
}
