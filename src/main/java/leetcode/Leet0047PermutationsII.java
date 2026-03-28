package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet0047PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> permutations = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), permutations);
        return permutations;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> permutations) {
        if (path.size() == nums.length) {
            permutations.add(new ArrayList<>(path));
            return;
        }

        for (int index = 0; index < nums.length; index++) {
            if (used[index] || (index > 0 && nums[index] == nums[index - 1] && !used[index - 1])) {
                continue;
            }

            used[index] = true;
            path.add(nums[index]);
            backtrack(nums, used, path, permutations);
            path.remove(path.size() - 1);
            used[index] = false;
        }
    }
}
