package com.example.test.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@Data
public class GlobalLogicException extends RuntimeException {

    private int codigo;
    private String detalle;
    private HttpStatus httpStatus;

    public GlobalLogicException(String message, int codigo, String detalle,HttpStatus httpStatus) {
        super(message);
        this.codigo = codigo;
        this.detalle = detalle;
        this.httpStatus = httpStatus;
    }


    public Map<String, Object> getMap() {
        Map<String, Object> error = new HashMap<>();

        error.put("timestamp", Timestamp.from(Instant.now()));
        error.put("codigo", this.codigo);
        error.put("detalle", this.detalle);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", error);
        return errorResponse;
    }
}
