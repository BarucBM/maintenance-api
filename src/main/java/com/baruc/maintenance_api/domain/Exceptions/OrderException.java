package com.baruc.maintenance_api.domain.Exceptions;

public class OrderException extends RuntimeException{

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(String message) {
        super(message);
    }
}
