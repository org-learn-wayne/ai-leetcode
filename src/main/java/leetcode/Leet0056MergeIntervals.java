package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet0056MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (first, second) -> Integer.compare(first[0], second[0]));
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(new int[] {interval[0], interval[1]});
            } else {
                merged.get(merged.size() - 1)[1] =
                        Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(int[][]::new);
    }
}
