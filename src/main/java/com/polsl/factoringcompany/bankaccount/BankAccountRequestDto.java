package com.polsl.factoringcompany.bankaccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccountRequestDto {

    private String bankSwift;
    private String bankAccountNumber;
    private String bankName;
}
