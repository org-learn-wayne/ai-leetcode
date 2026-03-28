package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet0039CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> combinations = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void backtrack(int[] candidates, int remaining, int start,
                           List<Integer> path, List<List<Integer>> combinations) {
        if (remaining == 0) {
            combinations.add(new ArrayList<>(path));
            return;
        }

        for (int index = start; index < candidates.length; index++) {
            int candidate = candidates[index];
            if (candidate > remaining) {
                return;
            }

            path.add(candidate);
            backtrack(candidates, remaining - candidate, index, path, combinations);
            path.remove(path.size() - 1);
        }
    }
}
