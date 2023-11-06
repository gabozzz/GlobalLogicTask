package com.example.test.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalLogicException.class)
    public ResponseEntity<Map<String, Object>> handleGlobalLogicException(GlobalLogicException ex) {
        return new ResponseEntity<>( ex.getMap()
                ,  ex.getHttpStatus());
    }
}
