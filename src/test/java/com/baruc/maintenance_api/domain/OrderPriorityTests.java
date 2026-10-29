package com.baruc.maintenance_api.domain;

import org.junit.jupiter.api.BeforeEach;

public class OrderPriorityTests {

    private PriorityCalculator priorityCalculator;

    @BeforeEach
    void setUp() {
        priorityCalculator = new PriorityCalculator();
    }

}
