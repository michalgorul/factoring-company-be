package com.polsl.factoringcompany.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtProps {

    public static final String SECRET = "~9L/fFl-z1)vLti(TcxDd'+aK)raQNnQ[W5+nr/Dz:%&VX0dzb$`0biETG_n!&c33's:AQ%aydQUK~f*kJqCZ5QR8Zv}?l,j'1MLinT%}Qy{Mwd#IM9Z7LycJe3qNUsHcQh3r11682z7EvhicZggVKyzQQ";
    public static final Integer EXPIRATION_TIME_IN_DAYS = 15;
    //    public static final Integer EXPIRATION_TIME_IN_DAYS = Math.toIntExact(TimeUnit.SECONDS.toDays(10));
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = HttpHeaders.AUTHORIZATION;

}
