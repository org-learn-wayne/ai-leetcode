package leetcode;

public class Leet0029DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);
        int quotient = 0;

        while (dividendLong >= divisorLong) {
            long value = divisorLong;
            int multiple = 1;

            while (dividendLong >= (value << 1)) {
                value <<= 1;
                multiple <<= 1;
            }

            dividendLong -= value;
            quotient += multiple;
        }

        return (dividend > 0) == (divisor > 0) ? quotient : -quotient;
    }
}
