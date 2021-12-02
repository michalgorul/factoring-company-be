package com.polsl.factoringcompany.invoiceitem;

import com.polsl.factoringcompany.invoice.InvoiceCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * The type Invoice item dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {

    /**
     * the quantity
     */
    private int quantity;

    /**
     * the net price
     */
    private BigDecimal netPrice;

    /**
     * the vat rate
     */
    private BigDecimal vatRate;

    /**
     * the product id
     */
    private Long productId;

    /**
     * the invoice id
     */
    private Long invoiceId;

    /**
     * Instantiates a new Invoice item dto.
     *
     * @param invoiceCreateRequest the invoice create request
     * @param productId            the product id that invoice item is associated with
     * @param invoiceId            the invoice id that invoice item is associated with
     */
    public InvoiceItemDto(InvoiceCreateRequest invoiceCreateRequest, Long productId, Long invoiceId) {
        this.quantity = invoiceCreateRequest.getQuantity();
        this.netPrice = BigDecimal.valueOf(invoiceCreateRequest.getNet());
        this.vatRate = BigDecimal.valueOf(invoiceCreateRequest.getVat() / 100);
        this.productId = productId;
        this.invoiceId = invoiceId;
    }
}
