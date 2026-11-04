package com.baruc.maintenance_api.controllers;

import com.baruc.maintenance_api.controller.OrderController;
import com.baruc.maintenance_api.domain.MaintenanceOrder;
import com.baruc.maintenance_api.domain.OrderStatus;
import com.baruc.maintenance_api.services.MaintenanceOrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    MaintenanceOrderService maintenanceOrderService;

    @Test
    @DisplayName("POST /orders com description válida retorna 201 e Location")
    void assertCreateOrderReturnsCreated() throws Exception {
        when(maintenanceOrderService.createOrder("Trocar filtro")).thenReturn(new MaintenanceOrder(1L, OrderStatus.OPEN, "Trocar filtro"));
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Trocar filtro\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/orders/1"));
    }
}
