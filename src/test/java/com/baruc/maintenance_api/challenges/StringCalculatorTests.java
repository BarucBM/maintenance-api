package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTests {

    public StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void assertEmptyStringReturnsZero() {
        assertThat(stringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void assertString1ReturnsOne() {
        assertThat(stringCalculator.add("1")).isEqualTo(1);
    }

    @Test
    public void assertString2ReturnsTwo() {
        assertThat(stringCalculator.add("2")).isEqualTo(2);
    }

    @Test
    public void assertString1_2ReturnsThree() {
        assertThat(stringCalculator.add("1,2")).isEqualTo(3);
    }
}
