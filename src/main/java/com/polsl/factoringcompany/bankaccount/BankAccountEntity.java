package com.polsl.factoringcompany.bankaccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.company.CompanyEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type Bank account entity. Representation of bank account in database
 *
 * @author Michal Goral
 * @version 1.0
 */
@EqualsAndHashCode
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "bank_account", schema ="public")
public class BankAccountEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "bank_account_id_seq",
            sequenceName = "bank_account_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bank_account_id_seq"
    )
    private Long id;

    /**
     * the bank account swift number
     * <a href="https://konsument.gov.pl/faq/co-to-jest-numer-iban-i-numer-bic-swift-code/">See more</a>
     */
    @Column(name = "bank_swift", nullable = false, length = 8)
    private String bankSwift;

    /**
     * the bank account number
     */
    @Column(name = "bank_account_number", nullable = false, length = 28, unique = true)
    private String bankAccountNumber;

    /**
     * the bank name
     */
    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;

    /**
     * the company id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * the company entity that is associated with bank account
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CompanyEntity companyByCompanyId;


    /**
     * Instantiates a new Bank account entity.
     *
     * @param bankSwift         the bank swift
     * @param bankAccountNumber the bank account number
     * @param bankName          the bank name
     * @param companyId         the company id
     */
    public BankAccountEntity(String bankSwift, String bankAccountNumber,
                             String bankName, Integer companyId) {
        this.bankSwift = bankSwift;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.companyId = companyId;
    }


}
