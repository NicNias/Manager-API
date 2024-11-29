package com.app.manager.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final Map<String, String> errors;

    public CustomException(String message, HttpStatus httpStatus, Map<String, String> errors) {
        super(message);
        this.httpStatus = httpStatus;
        this.errors = errors;
    }
}
