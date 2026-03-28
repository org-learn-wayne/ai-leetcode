package leetcode;

public class Leet0010RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int patternIndex = 2; patternIndex <= p.length(); patternIndex++) {
            if (p.charAt(patternIndex - 1) == '*') {
                dp[0][patternIndex] = dp[0][patternIndex - 2];
            }
        }

        for (int stringIndex = 1; stringIndex <= s.length(); stringIndex++) {
            for (int patternIndex = 1; patternIndex <= p.length(); patternIndex++) {
                char patternChar = p.charAt(patternIndex - 1);

                if (patternChar == '*') {
                    dp[stringIndex][patternIndex] = dp[stringIndex][patternIndex - 2];

                    char previousPatternChar = p.charAt(patternIndex - 2);
                    if (matches(s.charAt(stringIndex - 1), previousPatternChar)) {
                        dp[stringIndex][patternIndex] =
                                dp[stringIndex][patternIndex]
                                        || dp[stringIndex - 1][patternIndex];
                    }
                } else if (matches(s.charAt(stringIndex - 1), patternChar)) {
                    dp[stringIndex][patternIndex] = dp[stringIndex - 1][patternIndex - 1];
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    private boolean matches(char source, char pattern) {
        return pattern == '.' || source == pattern;
    }
}
