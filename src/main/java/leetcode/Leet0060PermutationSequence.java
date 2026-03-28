package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leet0060PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;

        for (int value = 1; value <= n; value++) {
            numbers.add(value);
            factorial[value] = factorial[value - 1] * value;
        }

        k--;
        StringBuilder permutation = new StringBuilder();
        for (int remaining = n; remaining >= 1; remaining--) {
            int index = k / factorial[remaining - 1];
            permutation.append(numbers.remove(index));
            k %= factorial[remaining - 1];
        }

        return permutation.toString();
    }
}
