package com.example.test.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @Test
    public void handleGlobalLogicExceptionTest() {
        GlobalLogicException exception = new GlobalLogicException("", 400,
                "Error mail invalido", HttpStatus.BAD_REQUEST);
        // Mocking the GlobalLogicException
        GlobalLogicException mockedException = mock(GlobalLogicException.class);
        when(mockedException.getMap()).thenReturn(new HashMap<>()); // Return an empty map
        when(mockedException.getHttpStatus()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR); // Return status code

        // Instantiate the GlobalExceptionHandler
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        // Invoke the method to handle the exception
        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleGlobalLogicException(mockedException);

        // Assert the response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size()); // Check if the map is empty
    }
}
