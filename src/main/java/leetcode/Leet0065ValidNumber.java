package leetcode;

public class Leet0065ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;
        boolean digitAfterExponent = true;

        for (int index = 0; index < s.length(); index++) {
            char current = s.charAt(index);

            if (Character.isDigit(current)) {
                seenDigit = true;
                digitAfterExponent = true;
            } else if (current == '+' || current == '-') {
                if (index > 0 && s.charAt(index - 1) != 'e' && s.charAt(index - 1) != 'E') {
                    return false;
                }
            } else if (current == '.') {
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else if (current == 'e' || current == 'E') {
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                digitAfterExponent = false;
            } else {
                return false;
            }
        }

        return seenDigit && digitAfterExponent;
    }
}
