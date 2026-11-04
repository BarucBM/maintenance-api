package com.baruc.maintenance_api.domain.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateOrderRequestTests {

    @Test
    @DisplayName("description vazia deve ser rejeitada")
    void assertBlankDescriptionIsInvalid() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        CreateOrderRequest request = new CreateOrderRequest("");

        Set<ConstraintViolation<CreateOrderRequest>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }
}
