package com.polsl.factoringcompany.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * The type Transaction request dto used in REST API.
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionRequestDto {

    /**
     * the value
     */
    private BigDecimal value;

    /**
     * boolean, if benefit or drawback
     */
    private boolean benefit;

    /**
     * the name
     */
    private String name;

    /**
     * the invoice id
     */
    private Integer invoiceId;

    /**
     * the credit id
     */
    private Integer creditId;
}
