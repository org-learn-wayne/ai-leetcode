package leetcode;

public class Leet0067AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int first = a.length() - 1;
        int second = b.length() - 1;

        while (first >= 0 || second >= 0 || carry > 0) {
            int sum = carry;
            if (first >= 0) {
                sum += a.charAt(first--) - '0';
            }
            if (second >= 0) {
                sum += b.charAt(second--) - '0';
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }
}
