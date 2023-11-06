package com.example.test.entities;

import org.junit.jupiter.api.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserdataTest {

    @Test
    public void userdataEntityAttributesTest() {
        Userdata userdata = new Userdata();

        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone());
        phones.add(new Phone());

        userdata.setId("1");
        userdata.setName("John Doe");
        userdata.setEmail("john@example.com");
        userdata.setPassword("password123");
        userdata.setCreated(LocalDate.now());
        userdata.setLastLogin(LocalDate.now());
        userdata.setToken("token123");
        userdata.setActive(true);
        userdata.setPhones(phones);

        assertEquals("1", userdata.getId());
        assertEquals("John Doe", userdata.getName());
        assertEquals("john@example.com", userdata.getEmail());
        assertEquals("password123", userdata.getPassword());
        assertNotNull(userdata.getCreated());
        assertNotNull(userdata.getLastLogin());
        assertEquals("token123", userdata.getToken());
        assertTrue(userdata.isActive());
        assertEquals(2, userdata.getPhones().size()); // Verificar la cantidad de teléfonos
    }

    @Test
    public void userdataEntityAnnotationsTest() {
        // Verificar si las anotaciones de la entidad están presentes y son correctas
        assertNotNull(Userdata.class.getAnnotation(Entity.class));
        assertNotNull(Userdata.class.getAnnotation(Table.class));
    }
}
