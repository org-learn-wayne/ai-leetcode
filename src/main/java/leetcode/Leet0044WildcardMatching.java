package leetcode;

public class Leet0044WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sIndex = 0;
        int pIndex = 0;
        int starIndex = -1;
        int matchIndex = 0;

        while (sIndex < s.length()) {
            if (pIndex < p.length() && (p.charAt(pIndex) == '?' || p.charAt(pIndex) == s.charAt(sIndex))) {
                sIndex++;
                pIndex++;
            } else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
                starIndex = pIndex++;
                matchIndex = sIndex;
            } else if (starIndex != -1) {
                pIndex = starIndex + 1;
                sIndex = ++matchIndex;
            } else {
                return false;
            }
        }

        while (pIndex < p.length() && p.charAt(pIndex) == '*') {
            pIndex++;
        }

        return pIndex == p.length();
    }
}
