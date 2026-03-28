package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0002AddTwoNumbersTest {
    private final Leet0002AddTwoNumbers solution = new Leet0002AddTwoNumbers();

    @Test
    void addsTwoListsOfEqualLength() {
        Leet0002AddTwoNumbers.ListNode result =
                solution.addTwoNumbers(list(2, 4, 3), list(5, 6, 4));

        assertArrayEquals(new int[] {7, 0, 8}, toArray(result));
    }

    @Test
    void addsZeroValues() {
        Leet0002AddTwoNumbers.ListNode result =
                solution.addTwoNumbers(list(0), list(0));

        assertArrayEquals(new int[] {0}, toArray(result));
    }

    @Test
    void handlesCarryAcrossMultipleNodes() {
        Leet0002AddTwoNumbers.ListNode result =
                solution.addTwoNumbers(list(9, 9, 9, 9, 9, 9, 9), list(9, 9, 9, 9));

        assertArrayEquals(new int[] {8, 9, 9, 9, 0, 0, 0, 1}, toArray(result));
    }

    @Test
    void handlesListsWithDifferentLengths() {
        Leet0002AddTwoNumbers.ListNode result =
                solution.addTwoNumbers(list(1, 8), list(0));

        assertArrayEquals(new int[] {1, 8}, toArray(result));
    }

    private Leet0002AddTwoNumbers.ListNode list(int... values) {
        Leet0002AddTwoNumbers.ListNode dummyHead = new Leet0002AddTwoNumbers.ListNode(0);
        Leet0002AddTwoNumbers.ListNode current = dummyHead;

        for (int value : values) {
            current.next = new Leet0002AddTwoNumbers.ListNode(value);
            current = current.next;
        }

        return dummyHead.next;
    }

    private int[] toArray(Leet0002AddTwoNumbers.ListNode node) {
        List<Integer> values = new ArrayList<>();

        while (node != null) {
            values.add(node.val);
            node = node.next;
        }

        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
