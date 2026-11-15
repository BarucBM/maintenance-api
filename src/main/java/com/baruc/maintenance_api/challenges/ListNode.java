package com.baruc.maintenance_api.challenges;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ListNode {
    int val;
    @Setter
    ListNode next;
    ListNode(int val) { this.val = val; }
}
