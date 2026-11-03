package com.baruc.maintenance_api.domain.exceptions;

public class OrderPersistenceException extends OrderException {
    public OrderPersistenceException(String message) {
        super(message);
    }
    public OrderPersistenceException(String message, Throwable cause) {
    super(message, cause);
  }
}
