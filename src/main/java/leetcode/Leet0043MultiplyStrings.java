package leetcode;

public class Leet0043MultiplyStrings {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int[] digits = new int[num1.length() + num2.length()];

        for (int first = num1.length() - 1; first >= 0; first--) {
            for (int second = num2.length() - 1; second >= 0; second--) {
                int product = (num1.charAt(first) - '0') * (num2.charAt(second) - '0');
                int ones = first + second + 1;
                int tens = first + second;
                int sum = product + digits[ones];
                digits[ones] = sum % 10;
                digits[tens] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < digits.length && digits[index] == 0) {
            index++;
        }

        while (index < digits.length) {
            result.append(digits[index++]);
        }

        return result.toString();
    }
}
