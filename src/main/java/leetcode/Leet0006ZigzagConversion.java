package leetcode;

public class Leet0006ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];

        for (int index = 0; index < numRows; index++) {
            rows[index] = new StringBuilder();
        }

        int currentRow = 0;
        int direction = 1;

        for (int index = 0; index < s.length(); index++) {
            rows[currentRow].append(s.charAt(index));

            if (currentRow == 0) {
                direction = 1;
            } else if (currentRow == numRows - 1) {
                direction = -1;
            }

            currentRow += direction;
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
