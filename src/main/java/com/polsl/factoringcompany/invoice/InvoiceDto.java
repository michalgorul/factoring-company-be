package com.polsl.factoringcompany.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

/**
 * The type Invoice dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    /**
     * the invoice number
     */
    private String invoiceNumber;

    /**
     * the date that invoice was created
     */
    private Timestamp creationDate;

    /**
     * the date that service was soled on invoice
     */
    private Timestamp saleDate;

    /**
     * the payment deadline of invoice
     */
    private Timestamp paymentDeadline;

    /**
     * the amount to pay on invoice
     */
    private BigDecimal toPay;

    /**
     * the amount o pay by user
     */
    private BigDecimal toPayByUser;

    /**
     * the amount paid by user
     */
    private BigDecimal paidByUser;

    /**
     * the remarks on invoice
     */
    private String remarks;

    /**
     * the status
     */
    private String status;

    /**
     * the customer id
     */
    private int customerId;

    /**
     * the payment type id
     */
    private int paymentTypeId;

    /**
     * the currency id
     */
    private int currencyId;

    /**
     * the user id
     */
    private int userId;

    /**
     * the interest of factoring company on every invoice
     */
    private final Double INTEREST = 0.01;

    /**
     * Instantiates a new Invoice dto.
     *
     * @param invoiceCreateRequest the invoice create request
     * @param invoiceNumber        the invoice number
     * @param customerId           the customer id
     * @param paymentTypeId        the payment type id
     * @param currencyId           the currency id
     * @param userId               the user id
     */
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
        this.toPay = BigDecimal.valueOf(netValue.doubleValue() + vatValue.doubleValue())
                .setScale(2, RoundingMode.HALF_UP);
        this.toPayByUser = BigDecimal.valueOf(toPay.doubleValue() + (toPay.doubleValue() * INTEREST))
                .setScale(2, RoundingMode.HALF_UP);
        this.paidByUser = new BigDecimal(0);
        this.remarks = invoiceCreateRequest.getRemarks();
        this.status = "active";
        this.customerId = Math.toIntExact(customerId);
        this.paymentTypeId = Math.toIntExact(paymentTypeId);
        this.currencyId = Math.toIntExact(currencyId);
        this.userId = Math.toIntExact(userId);
    }
}
