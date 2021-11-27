package com.polsl.factoringcompany.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    private String invoiceNumber;
    private Timestamp creationDate;
    private Timestamp saleDate;
    private Timestamp paymentDeadline;
    private BigDecimal toPay;
    private BigDecimal toPayByUser;
    private BigDecimal paidByUser;
    private String remarks;
    private String status;
    private int customerId;
    private int paymentTypeId;
    private int currencyId;
    private int userId;

    private final Double interest = 0.01;

    public InvoiceDto(InvoiceCreateRequest invoiceCreateRequest, String invoiceNumber, Long customerId,
                      Long paymentTypeId, Long currencyId, Long userId) {

        BigDecimal netValue = BigDecimal.valueOf(invoiceCreateRequest.getQuantity() * invoiceCreateRequest.getNet());
        BigDecimal vatValue = BigDecimal.valueOf(invoiceCreateRequest.getQuantity() * invoiceCreateRequest.getVat()
                * invoiceCreateRequest.getNet() / 100);

        this.invoiceNumber = invoiceNumber;
        this.creationDate = invoiceCreateRequest.getIssueDate();
        this.saleDate = invoiceCreateRequest.getPerformanceDate();
        this.paymentDeadline = Timestamp.valueOf(invoiceCreateRequest.getPerformanceDate()
                .toLocalDateTime().plusMonths(invoiceCreateRequest.getMonths()));
        this.toPay = BigDecimal.valueOf(netValue.doubleValue() + vatValue.doubleValue());
        this.toPayByUser = BigDecimal.valueOf(toPay.doubleValue() + (toPay.doubleValue() * interest));
        this.paidByUser = new BigDecimal(0);
        this.remarks = invoiceCreateRequest.getRemarks();
        this.status = "active";
        this.customerId = Math.toIntExact(customerId);
        this.paymentTypeId = Math.toIntExact(paymentTypeId);
        this.currencyId = Math.toIntExact(currencyId);
        this.userId = Math.toIntExact(userId);
    }
}
