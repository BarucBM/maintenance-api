package com.baruc.maintenance_api.repository;

import com.baruc.maintenance_api.domain.MaintenanceOrder;
import com.baruc.maintenance_api.domain.OrderStatus;
import com.baruc.maintenance_api.repositories.MaintenanceOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase
public class MaintenanceOrderRepositoryTests {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:15.3");

    @Autowired
    private MaintenanceOrderRepository maintenanceOrderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void assertSavedOrderReturnsWithSameStatusAndDescription() {
        MaintenanceOrder order = new MaintenanceOrder();
        order.setDescription("Description of the maintenance order");

        maintenanceOrderRepository.save(order);

        entityManager.flush();
        entityManager.clear();

        MaintenanceOrder retrievedOrder = maintenanceOrderRepository.findById(order.getId()).orElseThrow();
        assertThat(retrievedOrder.getDescription()).isEqualTo(order.getDescription());
        assertThat(retrievedOrder.getStatus()).isEqualTo(OrderStatus.OPEN);
    }

    @Test
    public void assertSavedOrderReturnsWithSameChangedStatusAndDescription() {
        MaintenanceOrder order = new MaintenanceOrder();
        order.setDescription("Description of the maintenance order");
        order.setStatus(OrderStatus.IN_PROGRESS);

        maintenanceOrderRepository.save(order);

        entityManager.flush();
        entityManager.clear();

        MaintenanceOrder retrievedOrder = maintenanceOrderRepository.findById(order.getId()).orElseThrow();
        assertThat(retrievedOrder.getDescription()).isEqualTo(order.getDescription());
        assertThat(retrievedOrder.getStatus()).isEqualTo(OrderStatus.IN_PROGRESS);
    }
}
