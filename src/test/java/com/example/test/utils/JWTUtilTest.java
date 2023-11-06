package com.example.test.utils;

import com.example.test.repository.UserRepository;
import com.example.test.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JWTUtilTest {


    String newSecretKey = "newSecretKey";
    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        // Modifica el campo SECRET_KEY usando reflexión
        Field field = JWTUtil.class.getDeclaredField("SECRET_KEY");
        field.setAccessible(true);
        field.set(null, newSecretKey);
    }

    @Test
    public void testGenerateToken() {
        String userId = "123";

        // Se simula la generación del token
        String token = JWTUtil.generateToken(userId);

        // Aquí podrías verificar si el token se generó correctamente
        // por ejemplo, podrías comprobar su estructura o decodificarlo para verificar sus componentes.
        // Debido a la naturaleza del token JWT, no se pueden desencriptar, pero se pueden verificar elementos como su estructura.
    }

    @Test
    public void testValidateAndExtractUserId() {
        String userId = "123";

        // Se simula la generación del token
        String token = JWTUtil.generateToken(userId);

        // Simulación de la lectura del token
        Jws<Claims> jws = Jwts.parser().setSigningKey(newSecretKey).parseClaimsJws(token);

        // Cuando se llama a validateAndExtractUserId, debe devolver el userId extraído del token.
        assertEquals(userId, JWTUtil.validateAndExtractUserId(token));
    }
}
