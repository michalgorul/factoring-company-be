package com.polsl.factoringcompany.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoicePaymentInfoUpdateRequest {
    private String currencyName;
    private String paymentTypeName;
}

