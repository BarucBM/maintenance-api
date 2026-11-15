package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TwoSortedListsTests {

    public TwoSortedLists twoSortedLists;

    @BeforeEach
    void setUp() {
        twoSortedLists = new TwoSortedLists();
    }

    @Test
    public void assertBothInputNullReturnsNull() {
        assertThat(twoSortedLists.mergeSortedArrays(null, null)).isEqualTo(null);
    }

    @Test
    public void assertOneInputNullReturnsOther() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.setNext(listNode2);
        List <Integer> expected = convertToList(twoSortedLists.mergeSortedArrays(listNode1, null));
        assertThat(expected).containsExactly(1,2);
    }

    private List<Integer> convertToList(ListNode node) {
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        return result;
    }
}
