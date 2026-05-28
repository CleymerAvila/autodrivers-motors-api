package com.autodrivers.motors.infrastructure.errors.exception;

public class BusinessRulesValidationException extends RuntimeException {
    public BusinessRulesValidationException(String message) {
        super(message);
    }
}
