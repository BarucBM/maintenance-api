package com.baruc.maintenance_api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceOrder {

    private Long id;

    private OrderStatus status = OrderStatus.OPEN;

    private String description;

    private boolean statusCanChangeTo(OrderStatus newStatus)    {
        return this.status.getTransitions().contains(newStatus.getValue());
    }

    public void setStatus(OrderStatus status) {
        if (this.statusCanChangeTo(status)) {
            this.status = status;
        } else {
            throw new OrderException("Status change from " + this.status + " to " + status + " is not allowed.");
        }
    }
}
