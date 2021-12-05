package com.polsl.factoringcompany.bankaccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Bank account request dto.
 * @author Michal Goral
 * @version 1.0
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccountRequestDto {

    /**
     * the bank account SWIFT number
     */
    private String bankSwift;

    /**
     * the bank account number
     */
    private String bankAccountNumber;

    /**
     * the bank's name
     */
    private String bankName;
}
