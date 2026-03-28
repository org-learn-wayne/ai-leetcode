package leetcode;

public class Leet0038CountAndSay {
    public String countAndSay(int n) {
        String current = "1";

        for (int iteration = 2; iteration <= n; iteration++) {
            StringBuilder next = new StringBuilder();
            int count = 1;

            for (int index = 1; index <= current.length(); index++) {
                if (index < current.length() && current.charAt(index) == current.charAt(index - 1)) {
                    count++;
                    continue;
                }

                next.append(count).append(current.charAt(index - 1));
                count = 1;
            }

            current = next.toString();
        }

        return current;
    }
}
