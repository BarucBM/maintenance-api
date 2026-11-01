package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
