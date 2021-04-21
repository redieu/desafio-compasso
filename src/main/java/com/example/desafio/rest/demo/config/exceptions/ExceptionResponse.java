package com.example.desafio.rest.demo.config.exceptions;

public class ExceptionResponse {
    private Integer status_code;
    private String message;

    public ExceptionResponse(Integer status_code, String message) {
        super();
        this.status_code = status_code;
        this.message = message;
    }

    public Integer getStatusCode() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }
}
