package com.example.test.entities;

import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PhoneTest {

    @Test
    public void phoneEntityAttributesTest() {
        Phone phone = new Phone();

        phone.setId(1L);
        phone.setNumber("123456789");
        phone.setCitycode(123);
        phone.setCountrycode("US");
        phone.setUserId("user123");

        assertEquals(1L, phone.getId());
        assertEquals("123456789", phone.getNumber());
        assertEquals(123, phone.getCitycode());
        assertEquals("US", phone.getCountrycode());
        assertEquals("user123", phone.getUserId());
    }

    @Test
    public void phoneEntityAnnotationsTest() {
        // Verificar si las anotaciones de la entidad están presentes y son correctas
        assertNotNull(Phone.class.getAnnotation(Entity.class));
    }
}
