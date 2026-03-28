package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0023MergeKSortedListsTest {
    private final Leet0023MergeKSortedLists solution = new Leet0023MergeKSortedLists();

    @Test
    void mergesMultipleSortedLists() {
        Leet0023MergeKSortedLists.ListNode[] lists = new Leet0023MergeKSortedLists.ListNode[] {
            list(1, 4, 5), list(1, 3, 4), list(2, 6)
        };
        assertArrayEquals(new int[] {1, 1, 2, 3, 4, 4, 5, 6}, toArray(solution.mergeKLists(lists)));
    }

    @Test
    void handlesEmptyArrayOfLists() {
        assertArrayEquals(new int[] {}, toArray(solution.mergeKLists(new Leet0023MergeKSortedLists.ListNode[] {})));
    }

    @Test
    void handlesArrayWithNullLists() {
        Leet0023MergeKSortedLists.ListNode[] lists = new Leet0023MergeKSortedLists.ListNode[] {null, list(-1, 5, 11), null};
        assertArrayEquals(new int[] {-1, 5, 11}, toArray(solution.mergeKLists(lists)));
    }

    private Leet0023MergeKSortedLists.ListNode list(int... values) {
        Leet0023MergeKSortedLists.ListNode dummy = new Leet0023MergeKSortedLists.ListNode(0);
        Leet0023MergeKSortedLists.ListNode current = dummy;

        for (int value : values) {
            current.next = new Leet0023MergeKSortedLists.ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private int[] toArray(Leet0023MergeKSortedLists.ListNode node) {
        List<Integer> values = new ArrayList<>();
        while (node != null) {
            values.add(node.val);
            node = node.next;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
