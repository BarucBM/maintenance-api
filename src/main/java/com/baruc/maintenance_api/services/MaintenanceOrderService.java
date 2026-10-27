package com.baruc.maintenance_api.services;

import com.baruc.maintenance_api.domain.MaintenanceOrder;
import com.baruc.maintenance_api.domain.OrderException;
import com.baruc.maintenance_api.domain.OrderStatus;
import com.baruc.maintenance_api.repositories.MaintenanceOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaintenanceOrderService {

    private final MaintenanceOrderRepository maintenanceOrderRepository;

    public MaintenanceOrder createOrder(String description) {
        MaintenanceOrder order = new MaintenanceOrder();
        order.setDescription(description);
        return saveOrder(order);
    }

    public MaintenanceOrder getOrderById(Long id) {
        return maintenanceOrderRepository.findById(id)
                .orElseThrow(() -> new OrderException("Order with id " + id + " not found."));
    }

    public MaintenanceOrder updateOrderStatus(Long id, OrderStatus newStatus) {
        MaintenanceOrder order = getOrderById(id);
        order.setStatus(newStatus);
        return saveOrder(order);
    }


    private MaintenanceOrder saveOrder(MaintenanceOrder order) {
        return maintenanceOrderRepository.save(order);
    }
}
