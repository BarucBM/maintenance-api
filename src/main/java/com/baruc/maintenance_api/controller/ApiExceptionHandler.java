package com.baruc.maintenance_api.controller;

import com.baruc.maintenance_api.domain.exceptions.InvalidStatusTransitionException;
import com.baruc.maintenance_api.domain.exceptions.OrderNotFoundException;
import com.baruc.maintenance_api.domain.exceptions.OrderPersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ProblemDetail handleNotFound(OrderNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidStatusTransitionException.class)
    public ProblemDetail handleInvalidTransition(InvalidStatusTransitionException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());
    }

    @ExceptionHandler(OrderPersistenceException.class)
    public ProblemDetail handlePersistenceProblem(OrderPersistenceException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor ao salvar Order. Por favor, tente novamente mais tarde.");
    }
}
