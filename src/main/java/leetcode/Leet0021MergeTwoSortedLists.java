package leetcode;

public class Leet0021MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                current.next = new ListNode(list2.val);
                list2 = list2.next;
            }

            current = current.next;
        }

        while (list1 != null) {
            current.next = new ListNode(list1.val);
            current = current.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            current.next = new ListNode(list2.val);
            current = current.next;
            list2 = list2.next;
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
