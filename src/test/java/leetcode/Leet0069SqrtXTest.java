package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0069SqrtXTest {
    private final Leet0069SqrtX solution = new Leet0069SqrtX();

    @Test
    void handlesPerfectSquare() {
        assertEquals(2, solution.mySqrt(4));
    }

    @Test
    void truncatesNonPerfectSquare() {
        assertEquals(2, solution.mySqrt(8));
    }

    @Test
    void handlesLargeInput() {
        assertEquals(46339, solution.mySqrt(2147395599));
    }
}
