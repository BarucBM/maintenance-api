package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FirstUniqueCharacterTests {


    public FirstUniqueCharacter findString;

    @BeforeEach
    void setUp() {
        findString = new FirstUniqueCharacter();
    }

    @Test
    public void assertFirstReturnA() {
        assertThat(findString.find("a")).isEqualTo("a");
    }

    @Test
    public void assertFirstReturnC() {
        assertThat(findString.find("cbb")).isEqualTo("c");
    }

    @Test
    public void assertFirstReturnTeste() {
        assertThat(findString.find("teste")).isEqualTo("s");
    }

    @Test
    public void assertFirstReturnAABB() {
        assertThat(findString.find("AABB")).isEqualTo("");
    }

    @Test
    public void assertEmptyStringReturns() {
        assertThat(findString.find("")).isEqualTo("");
    }

    @Test
    public void assertNullThrowsIllegalArgumentException() {
        assertThatThrownBy(() ->findString.find(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input string cannot be null");;
    }
}
