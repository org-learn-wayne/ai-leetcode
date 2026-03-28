package leetcode;

import java.util.PriorityQueue;

public class Leet0023MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = new ListNode(smallest.val);
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
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
