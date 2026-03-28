package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Leet0020ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (int index = 0; index < s.length(); index++) {
            char current = s.charAt(index);

            if (pairs.containsValue(current)) {
                stack.push(current);
            } else if (pairs.containsKey(current)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(current)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
