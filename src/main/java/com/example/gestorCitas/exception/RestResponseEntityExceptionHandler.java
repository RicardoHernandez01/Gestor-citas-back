package com.example.gestorCitas.exception;

import com.example.gestorCitas.exception.dto.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseRuntimeException.class)
    public ResponseEntity<ErrorMessage> runtimeExceptionHandler(ResponseRuntimeException ex){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .status(ex.getStatus())
                .build();
        return new ResponseEntity<>(errorMessage,errorMessage.getStatus());
    }
}
