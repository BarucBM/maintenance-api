package com.baruc.maintenance_api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderPriorityTests {

    private PriorityCalculator priorityCalculator;

    @BeforeEach
    void setUp() {
        priorityCalculator = new PriorityCalculator();
    }

    @Test
    public void assertBooleanTrueAlwaysReturnCritical() {
        assertThat(priorityCalculator.calculate(7, true)).isEqualTo(OrderPriority.CRITICAL);
    }

    @Test
    public void assertValueHigherThan7ReturnHigh() {
        assertThat(priorityCalculator.calculate(8, false)).isEqualTo(OrderPriority.HIGH);
    }

}
