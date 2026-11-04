package com.baruc.maintenance_api.controller;

import com.baruc.maintenance_api.domain.MaintenanceOrder;
import com.baruc.maintenance_api.services.MaintenanceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private MaintenanceOrderService orderService;

    @PostMapping("/orders")
    public MaintenanceOrder createOrder(@RequestBody String description) {
        return orderService.createOrder(description);
    }
}
