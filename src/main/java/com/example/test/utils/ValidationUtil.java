package com.example.test.utils;

import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPassword(String password) {
        // Expresión regular que verifica si la clave cumple con los requisitos
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d)[A-Za-z\\d]{8,12}$";

        return Pattern.matches(regex, password);
    }
}