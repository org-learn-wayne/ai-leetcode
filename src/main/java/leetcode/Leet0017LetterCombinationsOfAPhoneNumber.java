package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leet0017LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }

        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), mapping, result);
        return result;
    }

    private void backtrack(
            String digits, int index, StringBuilder current, String[] mapping, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = mapping[digits.charAt(index) - '0'];

        for (int letterIndex = 0; letterIndex < letters.length(); letterIndex++) {
            current.append(letters.charAt(letterIndex));
            backtrack(digits, index + 1, current, mapping, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
