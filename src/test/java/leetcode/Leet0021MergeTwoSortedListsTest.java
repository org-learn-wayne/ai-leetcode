package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0021MergeTwoSortedListsTest {
    private final Leet0021MergeTwoSortedLists solution = new Leet0021MergeTwoSortedLists();

    @Test
    void mergesTwoSortedLists() {
        assertArrayEquals(new int[] {1, 1, 2, 3, 4, 4}, toArray(solution.mergeTwoLists(list(1, 2, 4), list(1, 3, 4))));
    }

    @Test
    void handlesBothListsEmpty() {
        assertArrayEquals(new int[] {}, toArray(solution.mergeTwoLists(null, null)));
    }

    @Test
    void handlesOneEmptyList() {
        assertArrayEquals(new int[] {0}, toArray(solution.mergeTwoLists(null, list(0))));
    }

    private Leet0021MergeTwoSortedLists.ListNode list(int... values) {
        Leet0021MergeTwoSortedLists.ListNode dummy = new Leet0021MergeTwoSortedLists.ListNode(0);
        Leet0021MergeTwoSortedLists.ListNode current = dummy;

        for (int value : values) {
            current.next = new Leet0021MergeTwoSortedLists.ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private int[] toArray(Leet0021MergeTwoSortedLists.ListNode node) {
        List<Integer> values = new ArrayList<>();
        while (node != null) {
            values.add(node.val);
            node = node.next;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
