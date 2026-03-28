package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leet0057InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int index = 0;

        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            merged.add(intervals[index++]);
        }

        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            index++;
        }
        merged.add(new int[] {newInterval[0], newInterval[1]});

        while (index < intervals.length) {
            merged.add(intervals[index++]);
        }

        return merged.toArray(int[][]::new);
    }
}
