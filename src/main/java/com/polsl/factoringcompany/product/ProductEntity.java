package com.polsl.factoringcompany.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.invoiceitem.InvoiceItemEntity;
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
@Table(name = "product")
public class ProductEntity {

    @Id
    @SequenceGenerator(
            name = "product_id_seq",
            sequenceName = "currency_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_id_seq"
    )
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "pkwiu", nullable = false, length = 10)
    private String pkwiu;

    @Column(name = "measure_unit", nullable = false, length = 8)
    private String measureUnit;

    @OneToMany(mappedBy = "productByProductId")
    @JsonIgnore
    private Collection<InvoiceItemEntity> invoiceItemsById;

    public ProductEntity(String name, String pkwiu, String measureUnit) {
        this.name = name;
        this.pkwiu = pkwiu;
        this.measureUnit = measureUnit;
    }
}
