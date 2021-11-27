package com.polsl.factoringcompany.registration.passwordreset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PasswordResetRequest {

    private String password;
    private String token;
}
