package leetcode;

import java.util.Arrays;

public class Leet0016ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int index = 0; index < nums.length - 2; index++) {
            int left = index + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }
            }
        }

        return closest;
    }
}
