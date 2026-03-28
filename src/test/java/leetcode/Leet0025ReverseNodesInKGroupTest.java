package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0025ReverseNodesInKGroupTest {
    private final Leet0025ReverseNodesInKGroup solution = new Leet0025ReverseNodesInKGroup();

    @Test
    void reversesNodesInGroupsOfTwo() {
        assertArrayEquals(new int[] {2, 1, 4, 3, 5}, toArray(solution.reverseKGroup(list(1, 2, 3, 4, 5), 2)));
    }

    @Test
    void reversesNodesInGroupsOfThree() {
        assertArrayEquals(new int[] {3, 2, 1, 4, 5}, toArray(solution.reverseKGroup(list(1, 2, 3, 4, 5), 3)));
    }

    @Test
    void leavesListUnchangedWhenKIsOne() {
        assertArrayEquals(new int[] {1, 2}, toArray(solution.reverseKGroup(list(1, 2), 1)));
    }

    @Test
    void leavesTrailingShortGroupUntouched() {
        assertArrayEquals(new int[] {2, 1, 3}, toArray(solution.reverseKGroup(list(1, 2, 3), 2)));
    }

    private Leet0025ReverseNodesInKGroup.ListNode list(int... values) {
        Leet0025ReverseNodesInKGroup.ListNode dummy = new Leet0025ReverseNodesInKGroup.ListNode(0);
        Leet0025ReverseNodesInKGroup.ListNode current = dummy;

        for (int value : values) {
            current.next = new Leet0025ReverseNodesInKGroup.ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private int[] toArray(Leet0025ReverseNodesInKGroup.ListNode node) {
        List<Integer> values = new ArrayList<>();
        while (node != null) {
            values.add(node.val);
            node = node.next;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
