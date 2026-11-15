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

    @Test
    public void assertGetMinWithTwoValues() {
        minStack.push(5);
        minStack.push(3);
        assertThat(minStack.getMin()).isEqualTo(3);
    }

    @Test
    public void assertGetMinWithDifferentValues() {
        minStack.push(89);
        minStack.push(35);
        assertThat(minStack.getMin()).isEqualTo(35);
    }

    @Test
    public void assertGetMinWithPop() {
        minStack.push(89);
        minStack.push(35);
        minStack.pop();
        assertThat(minStack.getMin()).isEqualTo(89);
    }

    @Test
    public void assertGetMinWithDoubledNumbersAndPop() {
        minStack.push(35);
        minStack.push(35);
        minStack.pop();
        assertThat(minStack.getMin()).isEqualTo(35);
    }

}
