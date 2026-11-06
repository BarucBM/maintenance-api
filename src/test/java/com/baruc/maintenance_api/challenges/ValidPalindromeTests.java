package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @Test
    public void assertStringAnaReturnTrue() {
        assertThat(validPalindrome.valid("ana")).isEqualTo(true);
    }

    @Test
    @Disabled("aguarda toLowerCase e skip")
    public void assertStringWithNonDigitsReturnTrue() {
        assertThat(validPalindrome.valid("A man, a plan, a canal: Panama")).isEqualTo(true);
    }
}
