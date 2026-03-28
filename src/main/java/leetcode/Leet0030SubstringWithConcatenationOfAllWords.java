package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet0030SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s.isEmpty() || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalLength = wordLength * words.length;
        Map<String, Integer> targetCounts = new HashMap<>();

        for (String word : words) {
            targetCounts.put(word, targetCounts.getOrDefault(word, 0) + 1);
        }

        for (int start = 0; start <= s.length() - totalLength; start++) {
            Map<String, Integer> seenCounts = new HashMap<>();
            int wordIndex = 0;

            while (wordIndex < words.length) {
                int segmentStart = start + wordIndex * wordLength;
                String segment = s.substring(segmentStart, segmentStart + wordLength);

                if (!targetCounts.containsKey(segment)) {
                    break;
                }

                seenCounts.put(segment, seenCounts.getOrDefault(segment, 0) + 1);
                if (seenCounts.get(segment) > targetCounts.get(segment)) {
                    break;
                }

                wordIndex++;
            }

            if (wordIndex == words.length) {
                result.add(start);
            }
        }

        return result;
    }
}
