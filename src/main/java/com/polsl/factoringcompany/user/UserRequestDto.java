package com.polsl.factoringcompany.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String phone;
}
