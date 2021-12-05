package com.polsl.factoringcompany.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Company request dto.
 * @author Michal Goral
 * @version 1.0
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyRequestDto {

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
     * the NIP number
     */
    private String nip;

    /**
     * the REGON number
     */
    private String regon;
}
