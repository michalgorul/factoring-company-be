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

@EqualsAndHashCode
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer", schema ="public")
public class CustomerEntity {

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

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;

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

    @Column(name = "blacklisted", nullable = false)
    private boolean blacklisted;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "company_id")
    private Integer companyId;

    @OneToMany(mappedBy = "customerByCustomerId")
    @JsonIgnore
    private Collection<InvoiceEntity> invoicesById;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userByUserId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CompanyEntity companyByCompanyId;

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
