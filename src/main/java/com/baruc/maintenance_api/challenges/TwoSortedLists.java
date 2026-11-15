package com.baruc.maintenance_api.challenges;

public class TwoSortedLists {

    public ListNode mergeSortedArrays(ListNode listNode1, ListNode listNode2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode result = dummyHead;
        while (true){
            if (listNode1 == null){
                result.setNext(listNode2);
                break;
            }
            if (listNode2 == null){
                result.setNext(listNode1);
                break;
            }
            if (listNode1.val > listNode2.val){
                result.setNext(listNode2);
                listNode2 = listNode2.next;
            } else {
                result.setNext(listNode1);
                listNode1 = listNode1.next;
            }
            result = result.next;
        }
        return dummyHead.next;
    }
}
