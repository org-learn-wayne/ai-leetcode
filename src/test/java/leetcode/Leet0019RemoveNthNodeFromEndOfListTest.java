package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0019RemoveNthNodeFromEndOfListTest {
    private final Leet0019RemoveNthNodeFromEndOfList solution =
            new Leet0019RemoveNthNodeFromEndOfList();

    @Test
    void removesMiddleNode() {
        assertArrayEquals(new int[] {1, 2, 3, 5}, toArray(solution.removeNthFromEnd(list(1, 2, 3, 4, 5), 2)));
    }

    @Test
    void removesOnlyNode() {
        assertArrayEquals(new int[] {}, toArray(solution.removeNthFromEnd(list(1), 1)));
    }

    @Test
    void removesHeadNode() {
        assertArrayEquals(new int[] {2}, toArray(solution.removeNthFromEnd(list(1, 2), 2)));
    }

    private Leet0019RemoveNthNodeFromEndOfList.ListNode list(int... values) {
        Leet0019RemoveNthNodeFromEndOfList.ListNode dummy =
                new Leet0019RemoveNthNodeFromEndOfList.ListNode(0);
        Leet0019RemoveNthNodeFromEndOfList.ListNode current = dummy;

        for (int value : values) {
            current.next = new Leet0019RemoveNthNodeFromEndOfList.ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private int[] toArray(Leet0019RemoveNthNodeFromEndOfList.ListNode node) {
        List<Integer> values = new ArrayList<>();

        while (node != null) {
            values.add(node.val);
            node = node.next;
        }

        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
