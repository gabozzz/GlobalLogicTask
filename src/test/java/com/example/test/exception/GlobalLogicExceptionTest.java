package com.example.test.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GlobalLogicExceptionTest {

    @Test
    public void globalLogicExceptionTest() {
        int codigo = 404;
        String detalle = "Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;

        GlobalLogicException exception = new GlobalLogicException("Error occurred", codigo, detalle, status);

        assertEquals(codigo, exception.getCodigo());
        assertEquals(detalle, exception.getDetalle());
        assertEquals(status, exception.getHttpStatus());
    }

    @Test
    public void getMapTest() {
        int codigo = 404;
        String detalle = "Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;

        GlobalLogicException exception = new GlobalLogicException("Error occurred", codigo, detalle, status);

        Map<String, Object> errorMap = exception.getMap();
        assertNotNull(errorMap);

        assertEquals(1, errorMap.size()); // Only 'error' key is expected in the map

        Map<String, Object> error = (Map<String, Object>) errorMap.get("error");
        assertNotNull(error);
        assertEquals(3, error.size()); // Expecting 'timestamp', 'codigo', and 'detalle' keys

        assertNotNull(error.get("timestamp"));
        assertEquals(codigo, error.get("codigo"));
        assertEquals(detalle, error.get("detalle"));
    }
}
