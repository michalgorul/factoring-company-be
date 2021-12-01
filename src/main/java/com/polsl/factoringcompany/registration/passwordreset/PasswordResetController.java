package com.polsl.factoringcompany.registration.passwordreset;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * The type Password reset controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/password")
@AllArgsConstructor
public class PasswordResetController {

    /**
     * the password reset bean
     */
    private final PasswordResetService passwordResetService;

    /**
     * Resets password of specific user
     *
     * @param email the email
     * @return the string that password is reset or not
     */
    @PostMapping
    public String resetPassword(@RequestParam("email") String email) {
        return this.passwordResetService.resetPassword(email);
    }

    /**
     * Confirms changing of password.
     *
     * @param passwordResetRequest the password reset request
     * @return the string that password is reset or not
     */
    @PutMapping(path = "/reset")
    public String confirm(@RequestBody PasswordResetRequest passwordResetRequest) {
        return this.passwordResetService.confirm(passwordResetRequest);
    }
}
