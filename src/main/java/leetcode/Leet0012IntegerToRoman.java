package leetcode;

public class Leet0012IntegerToRoman {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();

        for (int index = 0; index < values.length; index++) {
            while (num >= values[index]) {
                result.append(numerals[index]);
                num -= values[index];
            }
        }

        return result.toString();
    }
}
