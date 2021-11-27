package com.polsl.factoringcompany.registration.passwordreset;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/password")
@AllArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping
    public String resetPassword(@RequestParam("email") String email) {
        return this.passwordResetService.resetPassword(email);
    }

    @PutMapping(path = "/reset")
    public String confirm(@RequestBody PasswordResetRequest passwordResetRequest) {
        return this.passwordResetService.confirm(passwordResetRequest);
    }
}
