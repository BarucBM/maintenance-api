package com.baruc.maintenance_api.domain.exceptions;

public class OrderNotFoundException extends OrderException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
