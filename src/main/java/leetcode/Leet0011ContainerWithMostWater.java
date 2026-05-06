package leetcode;

/**
 * Solves the Container With Most Water problem using a two-pointer technique.
 * 
 * Given an array of heights representing vertical lines, this class finds two lines
 * that, together with the x-axis, form a container that holds the maximum amount of water.
 * The area of water is determined by the distance between two lines (width) multiplied
 * by the height of the shorter line (limiting factor).
 * 
 * Algorithm: Uses two pointers starting from both ends of the array and moves them towards
 * each other. At each step, the pointer pointing to the shorter line is moved inward, since
 * moving the taller line can only decrease the area (the width decreases while the height
 * is already limited by the taller line). This greedy approach guarantees finding the maximum area.
 * 
 * Time Complexity: O(n) - single pass through the array.
 * Space Complexity: O(1) - only uses a constant amount of extra space.
 */
public class Leet0011ContainerWithMostWater {
    // please add some comments to explain the code
    // please use Sentence for comments (ending with period, starting with Capital letter).    
    // This is a two-pointer approach to find the maximum area between two lines
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int best = 0;

        while (left < right) {
            int width = right - left;
            int area = Math.min(height[left], height[right]) * width;
            best = Math.max(best, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return best;
    }
}
