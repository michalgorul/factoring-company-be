package com.polsl.factoringcompany.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * The type Registration controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Registers new user.
     *
     * @param registrationRequest the registration request
     * @return the confirmation link
     */
    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return this.registrationService.register(registrationRequest);
    }

    /**
     * Confirms token.
     *
     * @param token the token
     * @return the confirmation string
     */
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return this.registrationService.confirmToken(token);
    }
}
