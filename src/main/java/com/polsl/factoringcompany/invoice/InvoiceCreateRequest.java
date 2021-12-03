package com.polsl.factoringcompany.invoice;

import lombok.*;

import java.sql.Timestamp;

/**
 * The type Invoice create request.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvoiceCreateRequest {

    /**
     * the customer phone number
     */
    private String customerPhone;

    /**
     * the product name
     */
    private String productName;

    /**
     * the quantity
     */
    private Integer quantity;

    /**
     * the vat rate
     */
    private Double vat;

    /**
     * the net value for invoice
     */
    private Double net;

    /**
     * the currency name
     */
    private String currencyName;

    /**
     * the payment type name
     */
    private String paymentTypeName;

    /**
     * the date of performance of invoice
     */
    private Timestamp performanceDate;

    /**
     * the date of issue of invoice
     */
    private Timestamp issueDate;

    /**
     * number of months to invoice deadline
     */
    private Integer months;

    /**
     * the remarks on invoice
     */
    private String remarks;

}
