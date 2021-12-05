package com.polsl.factoringcompany.credit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.transaction.TransactionEntity;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;


/**
 * The type Credit entity. Representation of credit in database
 *
 * @author Michal Goral
 * @version 1.0
 */
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "credit")
@Table(name = "credit", schema ="public")
public class CreditEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "credit_id_seq",
            sequenceName = "credit_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "credit_id_seq"
    )
    private long id;

    /**
     * the credit number
     */
    @Column(name = "credit_number", nullable = false, length = 15)
    private String creditNumber;

    /**
     * the amount of credit
     */
    @Column(name = "amount", nullable = false, precision = 2)
    private BigDecimal amount;

    /**
     * the value of next payment
     */
    @Column(name = "next_payment", nullable = false, precision = 2)
    private BigDecimal nextPayment;

    /**
     * the number of installments
     */
    @Column(name = "installments", nullable = false)
    private int installments;

    /**
     * the credit balance. It represents how much is left to pay
     */
    @Column(name = "balance", nullable = false, precision = 2)
    private BigDecimal balance;

    /**
     * the rate of interest of credit
     */
    @Column(name = "rate_of_interest", nullable = false, precision = 2)
    private BigDecimal rateOfInterest;

    /**
     * the one time commission amount
     */
    @Column(name = "one_time_commission", nullable = false, precision = 2)
    private BigDecimal oneTimeCommission;

    /**
     * the date of next payment
     */
    @Column(name = "next_payment_date", nullable = false)
    private Date nextPaymentDate;

    /**
     * the date of creation of credit
     */
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    /**
     * the date of last installment
     */
    @Column(name = "last_installment_date", nullable = false)
    private Date lastInstallmentDate;

    /**
     * the status name
     */
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    /**
     * the user id
     */
    @Column(name = "user_id", nullable = false)
    private int userId;

    /**
     * the day of month that credit is paid by user
     */
    @Column(name = "payment_day", nullable = false)
    private int paymentDay;

    /**
     * the commission of credit
     */
    @Column(name = "commission", nullable = false, length = 20)
    private String commission;

    /**
     * the insurance of credit
     */
    @Column(name = "insurance", nullable = false, length = 50)
    private String insurance;

    /**
     * the user entity that credit is associated with
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity userByUserId;

    /**
     * the collection of transactions associated with credit
     */
    @OneToMany(mappedBy = "invoiceByInvoiceId")
    @JsonIgnore
    private Collection<TransactionEntity> transactionsById;


    /**
     * Instantiates a new Credit entity.
     *
     * @param creditRequestDto the credit request dto
     * @param creditNumber     the credit number
     * @param userId           the user id
     */
    public CreditEntity(CreditRequestDto creditRequestDto, String creditNumber, Integer userId) {
        this.creditNumber = creditNumber;
        this.amount = BigDecimal.valueOf(creditRequestDto.getAmount());
        this.nextPayment = BigDecimal.valueOf(creditRequestDto.getNextPayment());
        this.installments = creditRequestDto.getInstallments();
        this.balance = BigDecimal.valueOf(creditRequestDto.getAmount());
        this.rateOfInterest = BigDecimal.valueOf(creditRequestDto.getRateOfInterest());

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        LocalDateTime nextPaymentDate = LocalDateTime.of(nextMonth.getYear(), Month.from(nextMonth),
                creditRequestDto.getPaymentDay(), 0, 0);

        this.nextPaymentDate = Date.valueOf(nextPaymentDate.toLocalDate());
        this.creationDate = Date.valueOf(LocalDate.now());
        this.lastInstallmentDate = Date.valueOf(nextPaymentDate.plusMonths(installments - 1).toLocalDate());
        this.userId = userId;
        this.status = creditRequestDto.getStatus();
        this.commission = creditRequestDto.getCommission();
        this.paymentDay = creditRequestDto.getPaymentDay();
        this.insurance = creditRequestDto.getInsurance();
        this.oneTimeCommission = BigDecimal.valueOf(creditRequestDto.getOneTimeCommission());
    }

}
