package com.baruc.maintenance_api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MaintenanceOrderTests {

    @Test
    @DisplayName("uma conta nova ordem começa com status OPEN")
    public void assertNewOrderStatusOpen() {
        MaintenanceOrder order = new MaintenanceOrder();

        assert order.getStatus().equals(OrderStatus.OPEN);
    }

    @Test
    @DisplayName("uma ordem com status OPEN pode mudar para IN_PROGRESS")
    public void assertOpenOrderCanChangeToInProgress() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertDoesNotThrow(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assert order.getStatus().equals(OrderStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("uma ordem com status IN_PROGRESS pode mudar para COMPLETED")
    public void assertInProgressOrderCanChangeToCompleted() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertDoesNotThrow(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertDoesNotThrow(() -> order.setStatus(OrderStatus.COMPLETED));
        assert order.getStatus().equals(OrderStatus.COMPLETED);
    }

    @Test
    @DisplayName("uma ordem com status IN_PROGRESS não pode mudar para OPEN")
    public void assertOpenOrderCanNotChangeToCancelled() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertDoesNotThrow(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThrows(IllegalArgumentException.class, () -> order.setStatus(OrderStatus.OPEN));
    }

    @Test
    @DisplayName("status da ordem não muda após erro de transição")
    public void assertOrderStatusDoesNotChangeAfterTransitionError() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertDoesNotThrow(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThrows(IllegalArgumentException.class, () -> order.setStatus(OrderStatus.OPEN));
        assert order.getStatus().equals(OrderStatus.IN_PROGRESS);
    }

    @ParameterizedTest
    @EnumSource(names = {"OPEN", "IN_PROGRESS", "CANCELLED"})
    @DisplayName("uma ordem com status COMPLETED não pode mudar para nenhum outro status")
    public void assertCompletedOrderCanNotChangeToAnyOtherStatus(OrderStatus newStatus) {
        MaintenanceOrder order = new MaintenanceOrder();
        assertDoesNotThrow(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertDoesNotThrow(() -> order.setStatus(OrderStatus.COMPLETED));
        assertThrows(IllegalArgumentException.class, () -> order.setStatus(newStatus));
    }
}
