package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0024SwapNodesInPairsTest {
    private final Leet0024SwapNodesInPairs solution = new Leet0024SwapNodesInPairs();

    @Test
    void swapsAdjacentPairs() {
        assertArrayEquals(new int[] {2, 1, 4, 3}, toArray(solution.swapPairs(list(1, 2, 3, 4))));
    }

    @Test
    void handlesSingleNode() {
        assertArrayEquals(new int[] {1}, toArray(solution.swapPairs(list(1))));
    }

    @Test
    void handlesEmptyList() {
        assertArrayEquals(new int[] {}, toArray(solution.swapPairs(null)));
    }

    private Leet0024SwapNodesInPairs.ListNode list(int... values) {
        Leet0024SwapNodesInPairs.ListNode dummy = new Leet0024SwapNodesInPairs.ListNode(0);
        Leet0024SwapNodesInPairs.ListNode current = dummy;

        for (int value : values) {
            current.next = new Leet0024SwapNodesInPairs.ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private int[] toArray(Leet0024SwapNodesInPairs.ListNode node) {
        List<Integer> values = new ArrayList<>();
        while (node != null) {
            values.add(node.val);
            node = node.next;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
