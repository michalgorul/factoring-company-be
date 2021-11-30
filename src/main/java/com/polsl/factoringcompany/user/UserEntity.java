package com.polsl.factoringcompany.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.company.CompanyEntity;
import com.polsl.factoringcompany.credit.CreditEntity;
import com.polsl.factoringcompany.customer.CustomerEntity;
import com.polsl.factoringcompany.files.FileEntity;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import com.polsl.factoringcompany.registration.RegistrationRequest;
import com.polsl.factoringcompany.registration.token.ConfirmationTokenEntity;
import com.polsl.factoringcompany.transaction.TransactionEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


/**
 * The type User entity. Representation of user in database
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "user", schema ="public")
public class UserEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    private Long id;

    /**
     * the username
     */
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    /**
     * the password that is encrypted with BCrypt
     */
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    /**
     * the email
     */
    @Column(name = "email", nullable = false, length = 50, unique = true)
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
     * the company id that user has
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * the information that user account is locked or not
     */
    @Column(name = "locked", nullable = false)
    private boolean locked;

    /**
     * the information that user account is enabled or not
     */
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    /**
     * the collection of customers that are associated with user
     */
    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<CustomerEntity> customersById;

    /**
     * the collection of files that are associated with user
     */
    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<FileEntity> filesById;

    /**
     * the collection of invoices that are associated with user
     */
    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<InvoiceEntity> invoiceById;

    /**
     * the collection of credits that are associated with user
     */
    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<CreditEntity> creditsById;

    /**
     * the collection of confirmation tokens that are associated with user
     */
    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<ConfirmationTokenEntity> confirmationTokensByIdEntity;

    /**
     * the collection of transactions that are associated with user
     */
    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<TransactionEntity> transactionsById;

    /**
     * the company that user is associated with
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CompanyEntity companyByCompanyId;


    /**
     * Instantiates a new User entity.
     *
     * @param username   the username
     * @param password   the password
     * @param email      the email
     * @param firstName  the first name
     * @param lastName   the last name
     * @param country    the country
     * @param city       the city
     * @param street     the street
     * @param postalCode the postal code
     * @param phone      the phone number
     * @param companyId  the company id that user should have
     */
    public UserEntity(String username, String password, String email, String firstName,
                      String lastName, String country, String city, String street,
                      String postalCode, String phone, int companyId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.phone = phone;
        this.companyId = companyId;
    }

    /**
     * Instantiates a new User entity.
     *
     * @param registrationRequest the registration request object
     * @param locked              the information if user entity should be locked
     * @param enabled             the information if user entity should be enabled
     */
    public UserEntity(RegistrationRequest registrationRequest, boolean locked, boolean enabled) {
        this.username = registrationRequest.getUsername();
        this.password = registrationRequest.getPassword();
        this.email = registrationRequest.getEmail();
        this.firstName = registrationRequest.getFirstName();
        this.lastName = registrationRequest.getLastName();
        this.country = registrationRequest.getCountry();
        this.city = registrationRequest.getCity();
        this.street = registrationRequest.getStreet();
        this.postalCode = registrationRequest.getPostalCode();
        this.phone = registrationRequest.getPhone();
        this.companyId = null;
        this.locked = locked;
        this.enabled = enabled;
    }
}
