package com.baruc.maintenance_api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class MaintenanceOrderTests {

    @Test
    @DisplayName("uma nova ordem começa com status OPEN")
    public void assertNewOrderStatusOpen() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.OPEN);
    }

    @Test
    @DisplayName("uma ordem com status OPEN pode mudar para IN_PROGRESS")
    public void assertOpenOrderCanChangeToInProgress() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("uma ordem com status OPEN pode mudar para CANCELLED")
    public void assertOpenOrderCanChangeToCancelled() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.CANCELLED));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELLED);
    }

    @Test
    @DisplayName("uma ordem com status OPEN não pode mudar para COMPLETED")
    public void assertOpenOrderCanNotChangeToCompleted() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatThrownBy(() -> order.setStatus(OrderStatus.COMPLETED))
                .isInstanceOf(OrderException.class);
    }

    @Test
    @DisplayName("uma ordem com status IN_PROGRESS pode mudar para COMPLETED")
    public void assertInProgressOrderCanChangeToCompleted() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.COMPLETED));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    @DisplayName("uma ordem com status IN_PROGRESS pode mudar para CANCELLED")
    public void assertInProgressOrderCanChangeToCancelled() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.CANCELLED));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELLED);
    }

    @Test
    @DisplayName("uma ordem com status IN_PROGRESS não pode mudar para OPEN")
    public void assertOpenOrderCanNotChangeToCancelled() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThatThrownBy(() -> order.setStatus(OrderStatus.OPEN))
                .isInstanceOf(OrderException.class);
    }

    @Test
    @DisplayName("status da ordem não muda após erro de transição")
    public void assertOrderStatusDoesNotChangeAfterTransitionError() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThatThrownBy(() -> order.setStatus(OrderStatus.OPEN))
                .isInstanceOf(OrderException.class);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.IN_PROGRESS);
    }

    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    @DisplayName("uma ordem com status COMPLETED não pode mudar para nenhum outro status")
    public void assertCompletedOrderCanNotChangeToAnyOtherStatus(OrderStatus newStatus) {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.IN_PROGRESS));
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.COMPLETED));
        assertThatThrownBy(() -> order.setStatus(newStatus))
                .isInstanceOf(OrderException.class);
    }

    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    @DisplayName("uma ordem com status CANCELLED não pode mudar para nenhum outro status")
    public void assertCancelledOrderCanNotChangeToAnyOtherStatus(OrderStatus newStatus) {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThatNoException().isThrownBy(() -> order.setStatus(OrderStatus.CANCELLED));
        assertThatThrownBy(() -> order.setStatus(newStatus))
                .isInstanceOf(OrderException.class);
    }
}
