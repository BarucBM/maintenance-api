package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MinStackTests {

    public MinStack minStack;

    @BeforeEach
    void setUp() {
        minStack = new MinStack();
    }

    @Test
    public void assertPushAndPop() {
        minStack.push(5);
        assertThat(minStack.top()).isEqualTo(5);
    }

    @Test
    public void assertGetMin() {
        minStack.push(5);
        assertThat(minStack.getMin()).isEqualTo(5);
    }
}
