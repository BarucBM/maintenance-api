package com.baruc.maintenance_api.domain;

import com.baruc.maintenance_api.repositories.MaintenanceOrderRepository;
import com.baruc.maintenance_api.services.MaintenanceOrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaintenanceOrderTests {

    @InjectMocks
    @Autowired
    private MaintenanceOrderService maintenanceOrderService;

    @Mock
    private MaintenanceOrderRepository maintenanceOrderRepository;

    @Test
    @DisplayName("uma nova ordem começa com status OPEN")
    public void assertNewOrderStatusOpen() {
        MaintenanceOrder order = new MaintenanceOrder();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.OPEN);
    }

    @Test
    @DisplayName("uma nova ordem começa com status OPEN com Mockito")
    public void assertNewOrderStatusOpenWithMockito() {
        when(maintenanceOrderRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        MaintenanceOrder order = maintenanceOrderService.createOrder("Teste de ordem");

        assertThat(order.getStatus()).isEqualTo(OrderStatus.OPEN);
        verify(maintenanceOrderRepository).save(order);
    }

    @Test
    @DisplayName("uma ordem IN_PROGRESS pode ser finalizada com Mockito")
    public void assertFinishInProgressOrder() {
        when(maintenanceOrderRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        MaintenanceOrder order = maintenanceOrderService.createOrder("Teste de ordem");
        when(maintenanceOrderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        assertThatNoException().isThrownBy(() -> maintenanceOrderService.updateOrderStatus(order.getId(), OrderStatus.IN_PROGRESS));
        assertThatNoException().isThrownBy(() -> maintenanceOrderService.updateOrderStatus(order.getId(), OrderStatus.COMPLETED));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
        verify(maintenanceOrderRepository, times(3)).save(order);
    }


    @Test
    @DisplayName("uma ordem OPEN não pode ser finalizada com Mockito")
    public void assertOpenOrderCanNotBeFinished() {
        MaintenanceOrder order = new MaintenanceOrder();
        when(maintenanceOrderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        assertThatThrownBy(() ->  maintenanceOrderService.updateOrderStatus(order.getId(), OrderStatus.COMPLETED))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining("Status change from");
        verify(maintenanceOrderRepository, never()).save(order);
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
    public void assertInProgressOrderCanNotChangeToOpen() {
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
