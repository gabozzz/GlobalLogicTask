package com.example.test.dtos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SavedUserTest {

    @Test
    public void testGettersAndSetters() {
        // Crear un objeto SavedUser
        LocalDate created = LocalDate.now();
        LocalDate lastLogin = LocalDate.now();
        String id = "user123";
        String token = "someToken";
        boolean isActive = true;

        SavedUser savedUser = new SavedUser(id, created, lastLogin, token, isActive);

        // Verificar los valores a través de los getters
        assertEquals(id, savedUser.getId());
        assertEquals(created, savedUser.getCreated());
        assertEquals(lastLogin, savedUser.getLastLogin());
        assertEquals(token, savedUser.getToken());
        assertEquals(isActive, savedUser.isActive());

        // Modificar valores usando los setters
        LocalDate newDate = LocalDate.of(2023, 11, 10);
        savedUser.setCreated(newDate);
        savedUser.setLastLogin(newDate);
        savedUser.setId("newId");
        savedUser.setToken("newToken");
        savedUser.setActive(false);

        // Verificar que los valores cambiaron correctamente
        assertEquals(newDate, savedUser.getCreated());
        assertEquals(newDate, savedUser.getLastLogin());
        assertEquals("newId", savedUser.getId());
        assertEquals("newToken", savedUser.getToken());
        assertFalse(savedUser.isActive());
    }
}
