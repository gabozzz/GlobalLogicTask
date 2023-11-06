package com.example.test.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilTest {

    @Test
    public void testIsValidEmail_ValidEmail() {
        String validEmail = "test@example.com";
        assertTrue(ValidationUtil.isValidEmail(validEmail));
    }

    @Test
    public void testIsValidEmail_InvalidEmail() {
        String invalidEmail = "testexample.com";
        assertFalse(ValidationUtil.isValidEmail(invalidEmail));
    }

    @Test
    public void testIsValidPassword_ValidPassword() {
        String validPassword = "a2asfGfdfdf4";
        assertTrue(ValidationUtil.isValidPassword(validPassword));
    }

    @Test
    public void testIsValidPassword_InvalidPassword() {
        String invalidPassword = "password1"; // No cumple con los requisitos
        assertFalse(ValidationUtil.isValidPassword(invalidPassword));
    }
}
