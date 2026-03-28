package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leet0068TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            int lineStart = index;
            int lineLength = words[index].length();
            index++;

            while (index < words.length && lineLength + 1 + words[index].length() <= maxWidth) {
                lineLength += 1 + words[index].length();
                index++;
            }

            lines.add(buildLine(words, lineStart, index - 1, maxWidth, index == words.length));
        }

        return lines;
    }

    private String buildLine(String[] words, int start, int end, int maxWidth, boolean lastLine) {
        if (start == end || lastLine) {
            StringBuilder line = new StringBuilder(words[start]);
            for (int index = start + 1; index <= end; index++) {
                line.append(' ').append(words[index]);
            }
            while (line.length() < maxWidth) {
                line.append(' ');
            }
            return line.toString();
        }

        int totalLetters = 0;
        for (int index = start; index <= end; index++) {
            totalLetters += words[index].length();
        }

        int gaps = end - start;
        int totalSpaces = maxWidth - totalLetters;
        int spacesPerGap = totalSpaces / gaps;
        int extraSpaces = totalSpaces % gaps;

        StringBuilder line = new StringBuilder();
        for (int index = start; index < end; index++) {
            line.append(words[index]);
            int spaces = spacesPerGap + (index - start < extraSpaces ? 1 : 0);
            for (int count = 0; count < spaces; count++) {
                line.append(' ');
            }
        }
        line.append(words[end]);
        return line.toString();
    }
}
