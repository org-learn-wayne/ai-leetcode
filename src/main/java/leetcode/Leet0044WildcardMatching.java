package leetcode;

/**
 * Solves wildcard pattern matching with support for '?' and '*'.
 *
 * The pattern rules are:
 * '?' matches any single character.
 * '*' matches any sequence of characters, including the empty sequence.
 *
 * This implementation uses a greedy two-pointer scan with backtracking to the
 * most recent '*'. When characters match, both pointers advance. When a '*'
 * appears, its position is recorded and the algorithm tentatively treats it as
 * matching an empty sequence. If a later mismatch happens, the algorithm
 * backtracks to that '*', expands it to cover one more character from the
 * source string, and tries again.
 *
 * Time Complexity: O(s.length() * number of backtracks), which is O(n) in
 * practice for this greedy approach.
 * Space Complexity: O(1).
 */
public class Leet0044WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sIndex = 0;
        int pIndex = 0;
        int starIndex = -1; // Most recent '*' position in the pattern.
        int matchIndex = 0; // String position currently consumed by that '*'.

        while (sIndex < s.length()) {
            if (pIndex < p.length() && (p.charAt(pIndex) == '?' || p.charAt(pIndex) == s.charAt(sIndex))) {
                sIndex++;
                pIndex++;
            } else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
                // Record the '*' and first try to match it with an empty sequence.
                starIndex = pIndex++;
                matchIndex = sIndex;
            } else if (starIndex != -1) {
                // Revisit the most recent '*' and let it absorb one more character.
                pIndex = starIndex + 1;
                sIndex = ++matchIndex;
            } else {
                return false;
            }
        }

        // Any remaining pattern characters must all be '*' to match an empty suffix.
        while (pIndex < p.length() && p.charAt(pIndex) == '*') {
            pIndex++;
        }

        return pIndex == p.length();
    }
}
