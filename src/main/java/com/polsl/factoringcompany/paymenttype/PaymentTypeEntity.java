package com.polsl.factoringcompany.paymenttype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
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
@Table(name = "payment_type", schema ="public")
public class PaymentTypeEntity {

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

    @Column(name = "payment_type_name", nullable = false, length = 25, unique = true)
    private String paymentTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentTypeByPaymentTypeId")
    private Collection<InvoiceEntity> invoicesById;

    public PaymentTypeEntity(String name) {
        this.paymentTypeName = name;
    }

}
