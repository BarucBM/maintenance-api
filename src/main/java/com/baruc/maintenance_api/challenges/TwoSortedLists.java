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
        return result;
    }
}
