package leetcode;

public class Leet0053MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int best = nums[0];
        int current = nums[0];

        for (int index = 1; index < nums.length; index++) {
            current = Math.max(nums[index], current + nums[index]);
            best = Math.max(best, current);
        }

        return best;
    }
}
