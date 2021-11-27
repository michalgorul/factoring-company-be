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

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

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

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "postal_code", length = 15)
    private String postalCode;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "locked", nullable = false)
    private boolean locked;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<CustomerEntity> customersById;

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<FileEntity> filesById;

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<InvoiceEntity> invoiceById;

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<CreditEntity> creditsById;

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<ConfirmationTokenEntity> confirmationTokensByIdEntity;

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    private Collection<TransactionEntity> transactionsById;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CompanyEntity companyByCompanyId;

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

    public UserEntity(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.email = userEntity.getEmail();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.country = userEntity.getCountry();
        this.city = userEntity.getCity();
        this.street = userEntity.getStreet();
        this.postalCode = userEntity.getPostalCode();
        this.phone = userEntity.getPhone();
        this.companyId = userEntity.getCompanyId();
    }

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
