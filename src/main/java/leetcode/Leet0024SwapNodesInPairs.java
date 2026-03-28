package leetcode;

public class Leet0024SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode previous = dummyHead;

        while (previous.next != null && previous.next.next != null) {
            ListNode first = previous.next;
            ListNode second = first.next;

            first.next = second.next;
            second.next = first;
            previous.next = second;
            previous = first;
        }

        return dummyHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
