package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0061RotateListTest {
    private final Leet0061RotateList solution = new Leet0061RotateList();

    @Test
    void rotatesTypicalList() {
        assertArrayEquals(
                new int[] {4, 5, 1, 2, 3},
                toArray(solution.rotateRight(list(1, 2, 3, 4, 5), 2)));
    }

    @Test
    void handlesRotationLargerThanLength() {
        assertArrayEquals(
                new int[] {2, 0, 1},
                toArray(solution.rotateRight(list(0, 1, 2), 4)));
    }

    @Test
    void handlesEmptyList() {
        assertArrayEquals(new int[] {}, toArray(solution.rotateRight(null, 3)));
    }

    private Leet0061RotateList.ListNode list(int... values) {
        Leet0061RotateList.ListNode dummy = new Leet0061RotateList.ListNode(0);
        Leet0061RotateList.ListNode current = dummy;
        for (int value : values) {
            current.next = new Leet0061RotateList.ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    private int[] toArray(Leet0061RotateList.ListNode node) {
        List<Integer> values = new ArrayList<>();
        while (node != null) {
            values.add(node.val);
            node = node.next;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
