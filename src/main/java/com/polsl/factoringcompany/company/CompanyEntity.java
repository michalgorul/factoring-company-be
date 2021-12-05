package com.polsl.factoringcompany.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.bankaccount.BankAccountEntity;
import com.polsl.factoringcompany.customer.CustomerEntity;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * The type Company entity. Representation of company in database
 *
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "company", schema ="public")
public class CompanyEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "company_id_seq",
            sequenceName = "company_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_id_seq"
    )
    private Long id;

    /**
     * the company name
     */
    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;

    /**
     * the country
     */
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    /**
     * the city
     */
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    /**
     * the street
     */
    @Column(name = "street", nullable = false, length = 50)
    private String street;

    /**
     * the postal code
     */
    @Column(name = "postal_code", length = 15)
    private String postalCode;

    /**
     * the nip number
     */
    @Column(name = "nip", nullable = false, length = 10, unique = true)
    private String nip;

    /**
     * the regon numbre
     */
    @Column(name = "regon", nullable = false, length = 14, unique = true)
    private String regon;

    /**
     * the collection of bank account entities that are associated with company
     */
    @OneToMany(mappedBy = "companyByCompanyId")
    @JsonIgnore
    private Collection<BankAccountEntity> bankAccountsById;

    /**
     * the collection of user entities that are associated with company
     */
    @OneToMany(mappedBy = "companyByCompanyId")
    @JsonIgnore
    private Collection<UserEntity> usersById;

    /**
     * the collection of customer entities that are associated with company
     */
    @OneToMany(mappedBy = "companyByCompanyId")
    @JsonIgnore
    private Collection<CustomerEntity> customersById;

    /**
     * Instantiates a new Company entity.
     *
     * @param companyName the company name
     * @param country     the country
     * @param city        the city
     * @param street      the street
     * @param postalCode  the postal code
     * @param nip         the nip
     * @param regon       the regon
     */
    public CompanyEntity(String companyName, String country, String city, String street, String postalCode, String nip, String regon) {
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.nip = nip;
        this.regon = regon;
    }
}
