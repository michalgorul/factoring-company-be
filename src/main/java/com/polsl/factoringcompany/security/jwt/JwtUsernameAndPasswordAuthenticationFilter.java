package com.polsl.factoringcompany.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * The type Jwt username and password authentication filter.
 * @author Michal Goral
 * @version 1.0
 */
@RequiredArgsConstructor
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * the AuthenticationManager bean
     */
    private final AuthenticationManager authenticationManager;

    /**
     * the secret key bean
     */
    private final SecretKey secretKey;

    /**
     * Attempts authentication of JWT token
     * @param request the request
     * @param response the response
     * @return result of authentication
     * @throws AuthenticationException the AuthenticationException exception
     */
    // Trigger when we issue POST request to /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            // Get credentials and map them to UsernameAndPasswordAuthenticationRequest
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            // Create login token
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            // Authenticate user
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {

        // Creating JWT token
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(JwtProps.EXPIRATION_TIME_IN_DAYS)))
                .signWith(secretKey)
                .compact();

        // Add token in response header
        response.addHeader(JwtProps.AUTH_HEADER, JwtProps.TOKEN_PREFIX + token);
    }
}
