package com.polsl.factoringcompany.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Customer request dto.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequestDto {

    /**
     * the email address
     */
    private String email;

    /**
     * the first name
     */
    private String firstName;

    /**
     * the last name
     */
    private String lastName;

    /**
     * the company name
     */
    private String companyName;

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

    /**
     * the information if customer is on blacklist
     */
    private boolean blacklisted;
}
