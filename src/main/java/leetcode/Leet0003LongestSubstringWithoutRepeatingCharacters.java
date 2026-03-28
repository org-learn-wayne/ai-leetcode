package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leet0003LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int best = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            if (lastSeen.containsKey(current)) {
                left = Math.max(left, lastSeen.get(current) + 1);
            }

            lastSeen.put(current, right);
            best = Math.max(best, right - left + 1);
        }

        return best;
    }
}
