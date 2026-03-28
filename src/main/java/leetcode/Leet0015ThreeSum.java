package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet0015ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int index = 0; index < nums.length - 2; index++) {
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }

            int left = index + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[index], nums[left], nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
