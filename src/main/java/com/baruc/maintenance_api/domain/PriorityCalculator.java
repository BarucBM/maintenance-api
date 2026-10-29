package com.baruc.maintenance_api.domain;

public class PriorityCalculator {

    public OrderPriority calculate(int daysOpen, boolean machineStopped){
        if (machineStopped)
            return OrderPriority.CRITICAL;

        if (daysOpen > 7)
            return OrderPriority.HIGH;

        if (daysOpen > 3)
            return OrderPriority.MEDIUM;

        return OrderPriority.LOW;
    }
}
