package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTests {

    @Test
    public void assertEmptyStringReturnsZero() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.add("")).isEqualTo(0);
    }
}
