package com.polsl.factoringcompany.paymenttype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * The type Payment Type entity. Representation of user in database
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "payment_type", schema ="public")
public class PaymentTypeEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "payment_type_id_seq",
            sequenceName = "payment_type_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_type_id_seq"
    )
    private Long id;

    /**
     * the payment type name
     */
    @Column(name = "payment_type_name", nullable = false, length = 25, unique = true)
    private String paymentTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentTypeByPaymentTypeId")
    private Collection<InvoiceEntity> invoicesById;

    /**
     * Instantiates a new Payment type entity.
     *
     * @param name the name
     */
    public PaymentTypeEntity(String name) {
        this.paymentTypeName = name;
    }

}
