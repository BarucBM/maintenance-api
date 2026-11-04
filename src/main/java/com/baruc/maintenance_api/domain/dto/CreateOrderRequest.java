package com.baruc.maintenance_api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateOrderRequest(@NotBlank String description) {}
