package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
