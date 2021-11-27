package com.polsl.factoringcompany.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyRequestDto {

    private String companyName;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String nip;
    private String regon;
}
