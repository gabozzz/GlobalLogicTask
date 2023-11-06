package com.example.test.utils;

import com.example.test.exception.GlobalLogicException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import java.util.Date;
@Configuration
public class JWTUtil {

    private static String SECRET_KEY;

    @Value("${SECRET_KEY}")
    private String secretKeyFromProperties;

    @PostConstruct
    public void init() {
        JWTUtil.SECRET_KEY = secretKeyFromProperties;
    }


    public static String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public static String validateAndExtractUserId(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception ex){
            throw  new GlobalLogicException("",400,
                    "Error al validar JWT", HttpStatus.UNAUTHORIZED);
        }

    }
}
