package com.polsl.factoringcompany.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

/**
 * The secret key class
 * @author Michal Goral
 * @version 1.0
 */
@Configuration
@RequiredArgsConstructor
public class JwtSecretKey {

    /**
     * Secret key bean. Uses HMAC
     * <a href="https://pl.wikipedia.org/wiki/HMAC">See more</a>
     *
     * @return the secret key bean
     */
    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(JwtProps.SECRET.getBytes());
    }
}
