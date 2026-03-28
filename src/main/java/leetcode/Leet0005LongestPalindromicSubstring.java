package leetcode;

public class Leet0005LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        int start = 0;
        int end = 0;

        for (int index = 0; index < s.length(); index++) {
            int oddLength = expandAroundCenter(s, index, index);
            int evenLength = expandAroundCenter(s, index, index + 1);
            int bestLength = Math.max(oddLength, evenLength);

            if (bestLength > end - start + 1) {
                start = index - (bestLength - 1) / 2;
                end = index + bestLength / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
