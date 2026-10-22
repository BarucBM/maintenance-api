package com.baruc.maintenance_api.controller;

import com.baruc.maintenance_api.model.HealthModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping()
    public HealthModel healthCheck() {
        HealthModel health = new HealthModel();
        health.setStatus("UP");
        return health;
    }
}
