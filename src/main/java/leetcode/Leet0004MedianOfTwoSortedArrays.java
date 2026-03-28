package leetcode;

public class Leet0004MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int[] merged = new int[totalLength];
        int left = 0;
        int right = 0;
        int index = 0;

        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] <= nums2[right]) {
                merged[index++] = nums1[left++];
            } else {
                merged[index++] = nums2[right++];
            }
        }

        while (left < nums1.length) {
            merged[index++] = nums1[left++];
        }

        while (right < nums2.length) {
            merged[index++] = nums2[right++];
        }

        if (totalLength % 2 == 1) {
            return merged[totalLength / 2];
        }

        int rightMiddle = totalLength / 2;
        int leftMiddle = rightMiddle - 1;
        return (merged[leftMiddle] + merged[rightMiddle]) / 2.0;
    }
}
