package com.polsl.factoringcompany.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The JWT token verifier. It is filter for http requests
 * @author Michal Goral
 * @version 1.0
 */
@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    /**
     * the secret key bean
     */
    private final SecretKey secretKey;

    /**
     * Filters and checks if JWT token is proper
     * @param httpServletRequest the http servlet request
     * @param httpServletResponse the http servlet response
     * @param filterChain the filter chain
     * @throws ServletException the servlet exception
     * @throws IOException the io exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        // Read the authorization header where the JWT token should be
        String authorizationHeader = httpServletRequest.getHeader(JwtProps.AUTH_HEADER);

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(JwtProps.TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // If present, gram the token
        String token = authorizationHeader.replace(JwtProps.TOKEN_PREFIX, "");


        try {
            // parsing the token
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            // Get authorities from the token body
            var authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
