package com.baruc.maintenance_api.controllers;

import com.baruc.maintenance_api.controller.OrderController;
import com.baruc.maintenance_api.domain.MaintenanceOrder;
import com.baruc.maintenance_api.domain.OrderStatus;
import com.baruc.maintenance_api.domain.exceptions.InvalidStatusTransitionException;
import com.baruc.maintenance_api.domain.exceptions.OrderNotFoundException;
import com.baruc.maintenance_api.services.MaintenanceOrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    @DisplayName("POST /orders com description vazia retorna 400 e nunca chama o service")
    void assertEmptyDescriptionNeverCallsService() throws Exception {
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"\"}"))
                .andExpect(status().isBadRequest());

        verify(maintenanceOrderService, never()).createOrder(any());
    }

    @Test
    @DisplayName("GET /orders/{id} retorna 200 e o order correto")
    void assertGetOrderByIdReturnsOrder() throws Exception {
        when(maintenanceOrderService.getOrderById(1L)).thenReturn(new MaintenanceOrder(1L, OrderStatus.OPEN, "Trocar filtro"));
        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andExpect(jsonPath("$.description").value("Trocar filtro"));
    }

    @Test
    @DisplayName("GET /orders/99 retorna 404 quando o order não existe")
    void assertGetOrderByIdReturns404() throws Exception {
        when(maintenanceOrderService.getOrderById(99L))
                .thenThrow(new OrderNotFoundException("Order with id 99 not found."));

        mockMvc.perform(get("/orders/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("Order with id 99 not found."));
    }

    @Test
    @DisplayName("Patch /orders/{id} para status válido retorna 200")
    void assertPatchOrderReturnsOk() throws Exception {
        when(maintenanceOrderService.updateOrderStatus(1L, OrderStatus.IN_PROGRESS)).thenReturn(new MaintenanceOrder(1L, OrderStatus.IN_PROGRESS, ""));

        mockMvc.perform(patch("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"IN_PROGRESS\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    @DisplayName("Patch /orders/{id} para status invalido retorna 422")
    void assertPatchOrderWithInvalidTransitionReturnsException() throws Exception {
        when(maintenanceOrderService.updateOrderStatus(1L, OrderStatus.COMPLETED))
                .thenThrow(new InvalidStatusTransitionException("Status change from OPEN to COMPLETED is not allowed."));

        mockMvc.perform(patch("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"COMPLETED\"}"))
                .andExpect(status().isUnprocessableContent());
    }
}
