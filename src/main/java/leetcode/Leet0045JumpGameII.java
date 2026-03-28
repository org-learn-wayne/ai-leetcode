package leetcode;

public class Leet0045JumpGameII {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int index = 0; index < nums.length - 1; index++) {
            farthest = Math.max(farthest, index + nums[index]);
            if (index == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }
}
