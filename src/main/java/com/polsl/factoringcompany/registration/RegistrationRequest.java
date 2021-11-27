package com.polsl.factoringcompany.registration;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RegistrationRequest {

    private final String username;
    private String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String country;
    private final String city;
    private final String street;
    private final String postalCode;
    private final String phone;

}
