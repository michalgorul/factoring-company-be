package com.polsl.factoringcompany.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type User request DTO used in REST API.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    /**
     * the first name
     */
    private String firstName;

    /**
     * the last name
     */
    private String lastName;

    /**
     * the email
     */
    private String email;

    /**
     * the country
     */
    private String country;

    /**
     * the city
     */
    private String city;

    /**
     * the street
     */
    private String street;

    /**
     * the postal code
     */
    private String postalCode;

    /**
     * the phone number
     */
    private String phone;
}
