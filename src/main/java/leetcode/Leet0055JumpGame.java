package leetcode;

public class Leet0055JumpGame {
    public boolean canJump(int[] nums) {
        int farthest = 0;

        for (int index = 0; index < nums.length; index++) {
            if (index > farthest) {
                return false;
            }

            farthest = Math.max(farthest, index + nums[index]);
        }

        return true;
    }
}
