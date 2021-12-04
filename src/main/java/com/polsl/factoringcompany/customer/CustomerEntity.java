package com.polsl.factoringcompany.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.company.CompanyEntity;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * The type Customer entity. Representation of invoice item in database
 *
 * @author Michal Goral
 * @version 1.0
 */
@EqualsAndHashCode
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer", schema ="public")
public class CustomerEntity {

    /**
     * the id
     */
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_seq"
    )
    private Long id;

    /**
     * the email address
     */
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    /**
     * the first name
     */
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * the last name
     */
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

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
     * the phone number
     */
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    /**
     * the information if customer is on black list
     */
    @Column(name = "blacklisted", nullable = false)
    private boolean blacklisted;

    /**
     * the user id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * the company id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * the collection of invoice entities associated with customer
     */
    @OneToMany(mappedBy = "customerByCustomerId")
    @JsonIgnore
    private Collection<InvoiceEntity> invoicesById;

    /**
     * the user entity that is associated with customer
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userByUserId;

    /**
     * the company entity that is associated with customer
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CompanyEntity companyByCompanyId;

    /**
     * Instantiates a new Customer entity.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param companyName the company name
     * @param country     the country
     * @param city        the city
     * @param street      the street
     * @param postalCode  the postal code
     * @param phone       the phone
     * @param blacklisted the blacklisted
     * @param userId      the user id
     * @param email       the email
     */
    public CustomerEntity(String firstName, String lastName, String companyName, String country,
                          String city, String street, String postalCode, String phone,
                          boolean blacklisted, Integer userId, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.phone = phone;
        this.blacklisted = blacklisted;
        this.userId = userId;
        this.email = email;
    }

}
