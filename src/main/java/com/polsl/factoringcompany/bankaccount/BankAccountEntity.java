package com.polsl.factoringcompany.bankaccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.company.CompanyEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "bank_account", schema ="public")
public class BankAccountEntity {
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

    @Column(name = "bank_swift", nullable = false, length = 8)
    private String bankSwift;

    @Column(name = "bank_account_number", nullable = false, length = 28, unique = true)
    private String bankAccountNumber;

    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;

    @Column(name = "company_id")
    private Integer companyId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CompanyEntity companyByCompanyId;

    public BankAccountEntity(String bankSwift, String bankAccountNumber,
                             String bankName, Integer companyId) {
        this.bankSwift = bankSwift;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.companyId = companyId;
    }


}
