package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidPalindromeTests {

    public ValidPalindrome validPalindrome;

    @BeforeEach
    void setUp() {
        validPalindrome = new ValidPalindrome();
    }

    @Test
    public void assertEmptyStringReturnTrue() {
        assertThat(validPalindrome.valid(" ")).isEqualTo(true);
    }

    @Test
    public void assertStringTesteReturnFalse() {
        assertThat(validPalindrome.valid("teste")).isEqualTo(false);
    }
}
