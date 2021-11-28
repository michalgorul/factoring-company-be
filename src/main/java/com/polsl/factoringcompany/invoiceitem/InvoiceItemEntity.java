package com.polsl.factoringcompany.invoiceitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import com.polsl.factoringcompany.product.ProductEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "invoice_item", schema ="public")
public class InvoiceItemEntity {

    @Id
    @SequenceGenerator(
            name = "invoice_item_id_seq",
            sequenceName = "invoice_item_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_item_id_seq"
    )
    private long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "net_price", nullable = false, precision = 2)
    private BigDecimal netPrice;

    @Column(name = "net_value", nullable = false, precision = 2)
    private BigDecimal netValue;

    @Column(name = "vat_rate", nullable = false, precision = 2)
    private BigDecimal vatRate;

    @Column(name = "vat_value", nullable = false, precision = 2)
    private BigDecimal vatValue;

    @Column(name = "gross_value", nullable = false, precision = 2)
    private BigDecimal grossValue;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "invoice_id", nullable = false)
    private int invoiceId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ProductEntity productByProductId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private InvoiceEntity invoiceByInvoiceId;

    public InvoiceItemEntity(InvoiceItemDto invoiceItemDto) {
        BigDecimal netValue = BigDecimal.valueOf(invoiceItemDto.getQuantity() * invoiceItemDto.getNetPrice().doubleValue());
        BigDecimal vatValue = BigDecimal.valueOf(invoiceItemDto.getQuantity() * invoiceItemDto.getVatRate().doubleValue() * invoiceItemDto.getNetPrice().doubleValue());

        this.quantity = invoiceItemDto.getQuantity();
        this.netPrice = invoiceItemDto.getNetPrice();
        this.netValue = netValue;
        this.vatRate = invoiceItemDto.getVatRate();
        this.vatValue = vatValue;
        this.grossValue = BigDecimal.valueOf(netValue.doubleValue() + vatValue.doubleValue());
        this.productId = Math.toIntExact(invoiceItemDto.getProductId());
        this.invoiceId = Math.toIntExact(invoiceItemDto.getInvoiceId());
    }


}
