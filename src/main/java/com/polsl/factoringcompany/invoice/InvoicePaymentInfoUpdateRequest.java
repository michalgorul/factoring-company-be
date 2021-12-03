package com.polsl.factoringcompany.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Invoice payment info update request.
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoicePaymentInfoUpdateRequest {

    /**
     * the currency name
     */
    private String currencyName;

    /**
     * the payment type name
     */
    private String paymentTypeName;
}

