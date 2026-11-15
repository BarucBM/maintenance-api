package com.baruc.maintenance_api.challenges;

import org.springframework.util.ObjectUtils;

public class TwoSortedLists {

    public ListNode mergeSortedArrays(ListNode listNode1, ListNode listNode2) {
        if (ObjectUtils.isEmpty(listNode1) && ObjectUtils.isEmpty(listNode2)) {
            return null;
        }
        if (ObjectUtils.isEmpty(listNode2)){
            return listNode1;
        }
        ListNode result = new ListNode();
        ListNode resultHead = new ListNode();

        if (listNode1.val > listNode2.val){
            resultHead = listNode2;
            listNode2 = listNode2.next;
        } else {
            resultHead = listNode1;
            listNode1 = listNode1.next;
        }
        result = resultHead;
        while (true){
            if (ObjectUtils.isEmpty(listNode1)){
                result.setNext(listNode2);
                break;
            }
            if (ObjectUtils.isEmpty(listNode2)){
                result.setNext(listNode1);
                break;
            }
            if (listNode1.val < listNode2.val){
                result.setNext(listNode1);
                listNode1 = listNode1.next;
            } else {
                result.setNext(listNode2);
                listNode2 = listNode2.next;
            }
            result = result.next;
        }
        return resultHead;
    }
}
