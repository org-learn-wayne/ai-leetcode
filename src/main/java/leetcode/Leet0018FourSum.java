package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet0018FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int first = 0; first < nums.length - 3; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            for (int second = first + 1; second < nums.length - 2; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                int left = second + 1;
                int right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[first] + nums[second] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[first], nums[second], nums[left], nums[right]));
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }

                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
