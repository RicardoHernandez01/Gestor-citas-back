package com.example.gestorCitas.exception;

import lombok.Data;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Data
public class ResponseRuntimeException extends RuntimeException {
    private HttpStatus status;
    public ResponseRuntimeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
