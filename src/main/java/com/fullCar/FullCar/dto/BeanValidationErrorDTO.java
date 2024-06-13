package com.fullCar.FullCar.dto;

import org.springframework.validation.FieldError;

public record BeanValidationErrorDTO(String field, String message) {
    public BeanValidationErrorDTO(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }

}
