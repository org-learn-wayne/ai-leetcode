package leetcode;

public class Leet0027RemoveElement {
    public int removeElement(int[] nums, int val) {
        int writeIndex = 0;

        for (int num : nums) {
            if (num != val) {
                nums[writeIndex++] = num;
            }
        }

        return writeIndex;
    }
}
