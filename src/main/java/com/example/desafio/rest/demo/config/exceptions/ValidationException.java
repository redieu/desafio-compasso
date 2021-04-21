package com.example.desafio.rest.demo.config.exceptions;

public class ValidationException extends RuntimeException {
    private Integer status_code;
    private String message;

    public ValidationException(int status_code, String message) {
        super();
        this.status_code = status_code;
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }

}
