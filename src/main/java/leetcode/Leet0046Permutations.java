package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leet0046Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        backtrack(nums, 0, permutations);
        return permutations;
    }

    private void backtrack(int[] nums, int start, List<List<Integer>> permutations) {
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>(nums.length);
            for (int num : nums) {
                permutation.add(num);
            }
            permutations.add(permutation);
            return;
        }

        for (int index = start; index < nums.length; index++) {
            swap(nums, start, index);
            backtrack(nums, start + 1, permutations);
            swap(nums, start, index);
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
