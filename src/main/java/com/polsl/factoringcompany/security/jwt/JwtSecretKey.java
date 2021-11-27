package com.polsl.factoringcompany.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
public class JwtSecretKey {

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(JwtProps.SECRET.getBytes());
    }
}
