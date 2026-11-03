package com.baruc.maintenance_api.domain.exceptions;

public class InvalidStatusTransitionException extends OrderException {
    public InvalidStatusTransitionException(String message) {
        super(message);
    }

  public InvalidStatusTransitionException(String message, Throwable cause) {
    super(message, cause);
  }
}
