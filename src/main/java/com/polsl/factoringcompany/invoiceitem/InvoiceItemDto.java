package com.polsl.factoringcompany.invoiceitem;

import com.polsl.factoringcompany.invoice.InvoiceCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {

    private int quantity;
    private BigDecimal netPrice;
    private BigDecimal vatRate;
    private Long productId;
    private Long invoiceId;

    public InvoiceItemDto(InvoiceCreateRequest invoiceCreateRequest, Long productId, Long invoiceId) {
        this.quantity = invoiceCreateRequest.getQuantity();
        this.netPrice = BigDecimal.valueOf(invoiceCreateRequest.getNet());
        this.vatRate = BigDecimal.valueOf(invoiceCreateRequest.getVat() / 100);
        this.productId = productId;
        this.invoiceId = invoiceId;
    }
}
