package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidParenthesisTests {

    public ValidParenthesis validParenthesis;

    @BeforeEach
    void setUp() {
        validParenthesis = new ValidParenthesis();
    }

    @Test
    public void assertBasicValidParenthesis() {
        assertThat(validParenthesis.verify("()")).isTrue();
    }

    @Test
    public void assertDoubleOpenerReturnsFalse() {
        assertThat(validParenthesis.verify("((")).isFalse();
    }

    @Test
    public void assertNotClosedReturnsFalse() {
        assertThat(validParenthesis.verify("()(")).isFalse();
    }

    @Test
    public void assertDiferentClosersReturnsFalse() {
        assertThat(validParenthesis.verify("((]]")).isFalse();
    }
}
