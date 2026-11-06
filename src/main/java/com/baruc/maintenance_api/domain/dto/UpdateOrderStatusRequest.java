package com.baruc.maintenance_api.domain.dto;

import com.baruc.maintenance_api.domain.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(@NotNull OrderStatus status) {
}
