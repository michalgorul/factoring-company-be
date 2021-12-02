package com.polsl.factoringcompany.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.invoiceitem.InvoiceItemEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * The type Product entity. Representation of product in database
 * @author Michal Goral
 * @version 1.0
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "product", schema ="public")
public class ProductEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "product_id_seq",
            sequenceName = "product_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_seq"
    )
    private Long id;

    /**
     * the name
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * the PKWIU
     * <a href="https://pl.wikipedia.org/wiki/Polska_Klasyfikacja_Wyrob%C3%B3w_i_Us%C5%82ug">See more</a>
     */
    @Column(name = "pkwiu", nullable = false, length = 10)
    private String pkwiu;

    /**
     * the measure unit, e.g. kilograms, number
     */
    @Column(name = "measure_unit", nullable = false, length = 8)
    private String measureUnit;

    /**
     * the collection of invoices that are associated with product
     */
    @OneToMany(mappedBy = "productByProductId")
    @JsonIgnore
    private Collection<InvoiceItemEntity> invoiceItemsById;

    /**
     * Instantiates a new Product entity.
     *
     * @param name        the name
     * @param pkwiu       the pkwiu
     * @param measureUnit the measure unit
     */
    public ProductEntity(String name, String pkwiu, String measureUnit) {
        this.name = name;
        this.pkwiu = pkwiu;
        this.measureUnit = measureUnit;
    }
}
