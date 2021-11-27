package com.polsl.factoringcompany.credit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditRequestDto {

    private Double amount;
    private Double nextPayment;
    private Double rateOfInterest;
    private Double oneTimeCommission;
    private String commission;
    private String status;
    private String insurance;
    private Integer paymentDay;
    private Integer installments;

}
