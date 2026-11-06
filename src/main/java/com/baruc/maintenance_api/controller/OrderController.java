package com.baruc.maintenance_api.controller;

import com.baruc.maintenance_api.domain.MaintenanceOrder;
import com.baruc.maintenance_api.domain.dto.CreateOrderRequest;
import com.baruc.maintenance_api.domain.dto.UpdateOrderStatusRequest;
import com.baruc.maintenance_api.services.MaintenanceOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class OrderController {

    @Autowired
    private MaintenanceOrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<MaintenanceOrder> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        MaintenanceOrder order = orderService.createOrder(createOrderRequest.description());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location).body(order);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<MaintenanceOrder> getOrder(@PathVariable Long id) {
        MaintenanceOrder order = orderService.getOrderById(id);

        return ResponseEntity.ok(order);
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<MaintenanceOrder> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOrderStatusRequest updateOrderStatusRequest
    ) {
        MaintenanceOrder order = orderService.updateOrderStatus(id, updateOrderStatusRequest.status());

        return ResponseEntity.ok(order);
    }
}
