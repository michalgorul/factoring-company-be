package com.polsl.factoringcompany.registration;

import lombok.*;

/**
 * The type Registration request. It contains essential info about user that is trying to register
 * @author Michal Goral
 * @version 1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RegistrationRequest {

    /**
     * the username
     */
    private final String username;

    /**
     * the password
     */
    private String password;

    /**
     * the email
     */
    private final String email;

    /**
     * the first name
     */
    private final String firstName;

    /**
     * the last name
     */
    private final String lastName;

    /**
     * the country
     */
    private final String country;

    /**
     * the city
     */
    private final String city;

    /**
     * the street
     */
    private final String street;

    /**
     * the postal code
     */
    private final String postalCode;

    /**
     * the phone number
     */
    private final String phone;

}
