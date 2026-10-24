package com.baruc.maintenance_api.challenges;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TwoSumTests {

    public static Stream<Arguments> provideParameters() {
        return Stream.of(
                arguments(new ArrayList<>(List.of(2, 7, 11, 15)), 9, new ArrayList<>(List.of(0, 1))),
                arguments(new ArrayList<>(List.of(3, 2, 4)), 6, new ArrayList<>(List.of(1, 2))),
                arguments(new ArrayList<>(List.of(3, 3)), 6, new ArrayList<>(List.of(0, 1)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    @DisplayName("")
    public void assertTwoSumMethodCanSolve(ArrayList<Integer> numbers, int target, ArrayList<Integer> expected) {
        TwoSum twoSum = new TwoSum();
        assertThatNoException().isThrownBy(() -> twoSum.twoSumSolution(numbers, target));
        assertThat(twoSum.twoSumSolution(numbers, target)).isEqualTo(expected);
    }
}
