package com.baruc.maintenance_api.domain;

public class OrderException extends RuntimeException{

    public OrderException(String message) {
        super(message);
    }
}
