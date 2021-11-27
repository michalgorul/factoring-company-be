package com.polsl.factoringcompany.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequestDto {

    private String email;
    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String phone;
    private boolean blacklisted;
}
