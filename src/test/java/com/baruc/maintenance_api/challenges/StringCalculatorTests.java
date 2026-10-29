package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void assertString1_2_3_4ReturnsTen() {
        assertThat(stringCalculator.add("1,2,3,4")).isEqualTo(10);
    }

    @Test
    public void assertBreakLineAsSeparator() {
        assertThat(stringCalculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void assertTwoSeparatorsIgnoreEmptySpaces() {
        assertThat(stringCalculator.add("1,\n2")).isEqualTo(3);
    }

    @Test
    public void assertPersonalizedSeparator() {
        assertThat(stringCalculator.add("//;\n1;2")).isEqualTo(3);
    }

    @Test
    public void assertPersonalizedSeparatorForNewNumbers() {
        assertThat(stringCalculator.add("//;\n3;4")).isEqualTo(7);
    }
}
