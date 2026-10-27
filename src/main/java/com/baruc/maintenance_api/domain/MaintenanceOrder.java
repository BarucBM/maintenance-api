package com.baruc.maintenance_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MaintenanceOrder {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private OrderStatus status = OrderStatus.OPEN;

    @Getter
    @Setter
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
