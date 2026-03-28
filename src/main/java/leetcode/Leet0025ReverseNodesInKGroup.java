package leetcode;

public class Leet0025ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode groupPrevious = dummyHead;

        while (true) {
            ListNode kth = getKthNode(groupPrevious, k);

            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;
            ListNode previous = groupNext;
            ListNode current = groupPrevious.next;

            while (current != groupNext) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            ListNode newGroupTail = groupPrevious.next;
            groupPrevious.next = kth;
            groupPrevious = newGroupTail;
        }

        return dummyHead.next;
    }

    private ListNode getKthNode(ListNode start, int k) {
        ListNode current = start;

        for (int count = 0; count < k; count++) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }

        return current;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
