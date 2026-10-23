package com.baruc.maintenance_api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderExceptions {
    ERRO01("ERRO01", "Status change not allowed"),
    ERRO02("ERRO02", "Order not found");

    private final String code;
    private final String message;
}
