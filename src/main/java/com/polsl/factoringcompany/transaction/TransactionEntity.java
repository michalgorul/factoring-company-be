package com.polsl.factoringcompany.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.credit.CreditEntity;
import com.polsl.factoringcompany.currency.CurrencyEntity;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * The type Transaction entity. Representation of user in database
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "transaction", schema ="public")
public class TransactionEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "transaction_id_seq",
            sequenceName = "transaction_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_id_seq"
    )
    private long id;

    /**
     * the transaction date
     */
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    /**
     * the transaction value
     */
    @Column(name = "value", nullable = false, precision = 2)
    private BigDecimal value;

    /**
     * boolean, if benefit or drawback
     */
    @Column(name = "benefit", nullable = false, precision = 2)
    private boolean benefit;

    /**
     * the name for transaction
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * the id of user that transaction belongs to
     */
    @Column(name = "user_id", nullable = false)
    private int userId;

    /**
     * the invoice id
     */
    @Column(name = "invoice_id")
    private Integer invoiceId;

    /**
     * the credit id
     */
    @Column(name = "credit_id")
    private Integer creditId;

    /**
     * the id of currency
     */
    @Column(name = "currency_id", nullable = false)
    private int currencyId;

    /**
     * the user entity that transaction is associated with
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity userByUserId;

    /**
     * the invoice entity that transaction is associated with
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", insertable = false, updatable = false)
    private InvoiceEntity invoiceByInvoiceId;

    /**
     * the credit entity that transaction is associated with
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "credit_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CreditEntity creditByCreditId;

    /**
     * the currency entity that transaction is associated with
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "currency_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CurrencyEntity currencyByCurrencyId;

    /**
     * Instantiates a new Transaction entity.
     *
     * @param transactionRequestDto the transaction request dto
     * @param userId                the desired user id
     * @param currencyId            the desired currency id
     */
    public TransactionEntity(TransactionRequestDto transactionRequestDto, Long userId, Long currencyId) {
        this.transactionDate = Date.valueOf(LocalDate.now());
        this.value = transactionRequestDto.getValue();
        this.benefit = transactionRequestDto.isBenefit();
        this.userId = Math.toIntExact(userId);
        this.invoiceId = transactionRequestDto.getInvoiceId();
        this.creditId = transactionRequestDto.getCreditId();
        this.currencyId = Math.toIntExact(currencyId);
        this.name = transactionRequestDto.getName();
    }

}
