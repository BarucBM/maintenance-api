package com.baruc.maintenance_api.service;


import com.baruc.maintenance_api.domain.MaintenanceOrder;
import com.baruc.maintenance_api.domain.OrderException;
import com.baruc.maintenance_api.domain.OrderStatus;
import com.baruc.maintenance_api.repositories.MaintenanceOrderRepository;
import com.baruc.maintenance_api.services.MaintenanceOrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaintenanceOrderServiceTests {

    @InjectMocks
    private MaintenanceOrderService maintenanceOrderService;

    @Mock
    private MaintenanceOrderRepository maintenanceOrderRepository;

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
        MaintenanceOrder order = new MaintenanceOrder(123L, OrderStatus.IN_PROGRESS, "Teste de ordem");
        when(maintenanceOrderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        assertThatNoException().isThrownBy(() -> maintenanceOrderService.updateOrderStatus(order.getId(), OrderStatus.COMPLETED));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
        verify(maintenanceOrderRepository).save(order);
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
    @DisplayName("uma ordem não encontrada retornada pelo repositório lança uma exception com Mockito")
    public void assertEmptyOrderThrows() {
        when(maintenanceOrderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> maintenanceOrderService.getOrderById(1L))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining("Order with id 1 not found.");

        verify(maintenanceOrderRepository, never()).save(any());
    }

    @Test
    @DisplayName("um erro ao salvar com Mockito")
    public void assertSaveException() {
        RuntimeException dbError = new RuntimeException("DB DOWN");
        when(maintenanceOrderRepository.save(any())).thenThrow(dbError);

        assertThatThrownBy(() -> maintenanceOrderService.createOrder("Teste de ordem"))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining("Failed to save order")
                .hasCause(dbError);

        verify(maintenanceOrderRepository).save(any());
    }

    @Test
    @DisplayName("uma ordem existente é retornada com Mockito")
    public void assertGetOrderReturnAnExistingOrder() {
        MaintenanceOrder order = new MaintenanceOrder(123L, OrderStatus.OPEN, "Teste de ordem");
        when(maintenanceOrderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        MaintenanceOrder retrievedOrder = maintenanceOrderService.getOrderById(order.getId());
        assertThat(retrievedOrder).isSameAs(order);
    }
}
