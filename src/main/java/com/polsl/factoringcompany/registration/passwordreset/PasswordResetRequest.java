package com.polsl.factoringcompany.registration.passwordreset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Password reset request. It contains password and token that is needed to change user's password
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@Getter
@Setter
public class PasswordResetRequest {

    /**
     * the password
     */
    private String password;

    /**
     * the token
     */
    private String token;
}
