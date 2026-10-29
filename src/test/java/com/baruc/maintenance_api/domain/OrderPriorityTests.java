package com.baruc.maintenance_api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    public void assertValueHigherThan3ReturnMedium() {
        assertThat(priorityCalculator.calculate(7, false)).isEqualTo(OrderPriority.MEDIUM);
    }

    @Test
    public void assertValueLowerThan4ReturnLow() {
        assertThat(priorityCalculator.calculate(3, false)).isEqualTo(OrderPriority.LOW);
    }

    @Test
    public void assertValueLowerThan0ThrowsException() {
        assertThatThrownBy(() -> priorityCalculator.calculate(-1, true))
            .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Days open cannot be negative");
    }

}
