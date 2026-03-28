package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet0032LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int longest = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == '(') {
                stack.push(index);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(index);
                } else {
                    longest = Math.max(longest, index - stack.peek());
                }
            }
        }

        return longest;
    }
}
