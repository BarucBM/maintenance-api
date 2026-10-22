package com.baruc.maintenance_api.controller;

import com.baruc.maintenance_api.model.HealtModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class healthController {

    @GetMapping()
    public HealtModel healthCheck() {
        HealtModel health = new HealtModel();
        health.setUp(true);
        return health;
    }
}
