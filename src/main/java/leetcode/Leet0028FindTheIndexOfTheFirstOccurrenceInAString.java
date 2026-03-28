package leetcode;

public class Leet0028FindTheIndexOfTheFirstOccurrenceInAString {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        for (int start = 0; start <= haystack.length() - needle.length(); start++) {
            int index = 0;

            while (index < needle.length() && haystack.charAt(start + index) == needle.charAt(index)) {
                index++;
            }

            if (index == needle.length()) {
                return start;
            }
        }

        return -1;
    }
}
