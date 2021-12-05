package com.polsl.factoringcompany.credit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Credit request dto.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditRequestDto {

    /**
     * the amount of whole credit
     */
    private Double amount;

    /**
     * the next payment amount
     */
    private Double nextPayment;

    /**
     * the rate of interest on credit
     */
    private Double rateOfInterest;

    /**
     * the amount of one time commission
     */
    private Double oneTimeCommission;

    /**
     * the commission type
     */
    private String commission;

    /**
     * the status name
     */
    private String status;

    /**
     * the type of insurance
     */
    private String insurance;

    /**
     * the date of month of credit payment
     */
    private Integer paymentDay;

    /**
     * the number of installments
     */
    private Integer installments;

}
