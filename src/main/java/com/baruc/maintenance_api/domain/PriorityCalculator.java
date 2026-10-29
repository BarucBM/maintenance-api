package com.baruc.maintenance_api.domain;

public class PriorityCalculator {

    public OrderPriority calculate(int daysOpen, boolean machineStopped){
        if (machineStopped)
            return OrderPriority.CRITICAL;

        return OrderPriority.LOW;
    }
}
