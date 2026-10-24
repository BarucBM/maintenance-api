package com.baruc.maintenance_api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    OPEN(0, Arrays.asList(1, 3)),
    IN_PROGRESS(1, Arrays.asList(2, 3)),
    COMPLETED(2, List.of()),
    CANCELLED(3, List.of());

    private final int value;
    private final List<Integer> transitions;
}
