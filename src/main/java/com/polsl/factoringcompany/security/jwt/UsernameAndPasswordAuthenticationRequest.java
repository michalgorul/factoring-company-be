package com.polsl.factoringcompany.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The username and password authentication request DAO
 */
@Getter
@Setter
@NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {

    /**
     * the username
     */
    private String username;

    /**
     * the password
     */
    private String password;

}
