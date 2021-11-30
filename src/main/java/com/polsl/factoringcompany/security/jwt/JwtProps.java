package com.polsl.factoringcompany.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.NoArgsConstructor;

/**
 * The JWT properties.
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
public class JwtProps {

    /**
     * The constant SECRET.
     */
    public static final String SECRET = "~9L/fFl-z1)vLti(TcxDd'+aK)raQNnQ[W5+nr/Dz:%&VX0dzb$`0biETG_n!&c33's:AQ%aydQUK~f*kJqCZ5QR8Zv}?l,j'1MLinT%}Qy{Mwd#IM9Z7LycJe3qNUsHcQh3r11682z7EvhicZggVKyzQQ";
    /**
     * The constant EXPIRATION_TIME_IN_DAYS.
     */
    public static final Integer EXPIRATION_TIME_IN_DAYS = 15;
    /**
     * The constant TOKEN_PREFIX.
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * The constant AUTH_HEADER.
     */
    public static final String AUTH_HEADER = HttpHeaders.AUTHORIZATION;

}
