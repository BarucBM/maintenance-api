package com.baruc.maintenance_api.domain.Exceptions;

public class OrderNotFoundException extends OrderException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
