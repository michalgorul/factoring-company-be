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

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "transaction", schema ="public")
public class TransactionEntity {

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

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "value", nullable = false, precision = 2)
    private BigDecimal value;

    @Column(name = "benefit", nullable = false, precision = 2)
    private boolean benefit;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "credit_id")
    private Integer creditId;

    @Column(name = "currency_id", nullable = false)
    private int currencyId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity userByUserId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", insertable = false, updatable = false)
    private InvoiceEntity invoiceByInvoiceId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "credit_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CreditEntity creditByCreditId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "currency_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CurrencyEntity currencyByCurrencyId;

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
