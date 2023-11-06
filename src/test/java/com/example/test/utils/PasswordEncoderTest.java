package com.example.test.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordEncoderTest {

    @Test
    public void testEncryptPassword() {
        String password = "mySecretPassword";

        // Se encripta el password utilizando el método encryptPassword de la clase PasswordEncoder
        String encryptedPassword = PasswordEncoder.encryptPassword(password);

        // Asegúrate de que la contraseña encriptada no sea igual a la contraseña original
        assertNotEquals(password, encryptedPassword);

        // Puedes verificar si el password encriptado se puede comparar correctamente con la contraseña original
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(password, encryptedPassword);
        // Debería devolver verdadero si la contraseña coincide con la versión encriptada
        assert matches;
    }
}
