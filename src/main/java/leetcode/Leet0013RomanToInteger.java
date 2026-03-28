package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leet0013RomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int total = 0;

        for (int index = 0; index < s.length(); index++) {
            int current = values.get(s.charAt(index));
            int next = index + 1 < s.length() ? values.get(s.charAt(index + 1)) : 0;

            if (current < next) {
                total -= current;
            } else {
                total += current;
            }
        }

        return total;
    }
}
