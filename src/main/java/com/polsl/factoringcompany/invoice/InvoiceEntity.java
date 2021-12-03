package com.polsl.factoringcompany.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.currency.CurrencyEntity;
import com.polsl.factoringcompany.customer.CustomerEntity;
import com.polsl.factoringcompany.invoiceitem.InvoiceItemEntity;
import com.polsl.factoringcompany.paymenttype.PaymentTypeEntity;
import com.polsl.factoringcompany.transaction.TransactionEntity;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.allegro.finance.tradukisto.MoneyConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * The type Invoice entity. Representation of invoice item in database
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity(name = "invoice")
@Table(name = "invoice", schema ="public")
public class InvoiceEntity {
    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "invoice_id_seq",
            sequenceName = "invoice_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_id_seq"
    )
    private long id;

    /**
     * the invoice number
     */
    @Column(name = "invoice_number", nullable = false, length = 50)
    private String invoiceNumber;

    /**
     * the date of invoice creation
     */
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    /**
     * tha date of service sale on invoice
     */
    @Column(name = "sale_date", nullable = false)
    private Timestamp saleDate;

    /**
     * the deadline date
     */
    @Column(name = "payment_deadline", nullable = false)
    private Timestamp paymentDeadline;

    /**
     * the amount to pay on invoice
     */
    @Column(name = "to_pay", nullable = false, precision = 2)
    private BigDecimal toPay;

    /**
     * the amount to pay in words on invoice
     */
    @Column(name = "to_pay_in_words", nullable = false, length = 100)
    private String toPayInWords;

    /**
     * the amount paid by user
     */
    @Column(name = "paid_by_user", nullable = false, precision = 2)
    private BigDecimal paidByUser;

    /**
     * the amount to pay by user
     */
    @Column(name = "to_pay_by_user", nullable = false, precision = 2)
    private BigDecimal toPayByUser;

    /**
     * the remarks on invoice
     */
    @Column(name = "remarks", length = 100)
    private String remarks;

    /**
     * the status of invoice
     */
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    /**
     * the customer id
     */
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    /**
     * the payment type id
     */
    @Column(name = "payment_type_id", nullable = false)
    private int paymentTypeId;

    /**
     * the currency id
     */
    @Column(name = "currency_id", nullable = false)
    private int currencyId;

    /**
     * the user id
     */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /**
     * the customer entity that invoice is associated with
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CustomerEntity customerByCustomerId;

    /**
     * the payment type entity that invoice is associated with
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private PaymentTypeEntity paymentTypeByPaymentTypeId;

    /**
     * the currency entity that invoice is associated with
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CurrencyEntity currencyByCurrencyId;

    /**
     * the user entity that invoice is associated with
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userByUserId;

    /**
     * the collection of invoice items that are associated with invoice
     */
    @OneToMany(mappedBy = "invoiceByInvoiceId")
    @JsonIgnore
    private Collection<InvoiceItemEntity> invoiceItemsById;

    /**
     * the collection of transactions that are associated with invoice
     */
    @OneToMany(mappedBy = "invoiceByInvoiceId")
    @JsonIgnore
    private Collection<TransactionEntity> transactionsById;

    /**
     * Instantiates a new Invoice entity.
     *
     * @param invoiceDto the invoice dto
     */
    public InvoiceEntity(InvoiceDto invoiceDto) {
        MoneyConverters converter = MoneyConverters.ENGLISH_BANKING_MONEY_VALUE;
        String toPayInWords = converter.asWords(invoiceDto.getToPay());

        this.invoiceNumber = invoiceDto.getInvoiceNumber();
        this.creationDate = invoiceDto.getCreationDate();
        this.saleDate = invoiceDto.getSaleDate();
        this.paymentDeadline = invoiceDto.getPaymentDeadline();
        this.toPay = invoiceDto.getToPay();
        this.toPayInWords = toPayInWords.replaceAll(" £", "");
        this.paidByUser = invoiceDto.getPaidByUser();
        this.toPayByUser = invoiceDto.getToPayByUser();
        this.remarks = invoiceDto.getRemarks();
        this.status = invoiceDto.getStatus();
        this.customerId = invoiceDto.getCustomerId();
        this.paymentTypeId = invoiceDto.getPaymentTypeId();
        this.currencyId = invoiceDto.getCurrencyId();
        this.userId = invoiceDto.getUserId();
    }

}
