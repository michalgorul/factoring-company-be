package com.polsl.factoringcompany.invoice;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvoiceCreateRequest {

    private String customerPhone;
    private String productName;
    private Integer quantity;
    private Double vat;
    private Double net;
    private String currencyName;
    private String paymentTypeName;
    private Timestamp performanceDate;
    private Timestamp issueDate;
    private Integer months;
    private String remarks;

}
