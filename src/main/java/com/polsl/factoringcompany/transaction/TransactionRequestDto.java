package com.polsl.factoringcompany.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionRequestDto {

    private BigDecimal value;
    private boolean benefit;
    private String name;
    private Integer invoiceId;
    private Integer creditId;
}
