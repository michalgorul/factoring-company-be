package com.polsl.factoringcompany.currency;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import com.polsl.factoringcompany.transaction.TransactionEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * The type currency entity. Representation of currency in database
 *
 * @author Michal Goral
 * @version 1.0
 */
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "currency", schema ="public")
public class CurrencyEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "currency_id_seq",
            sequenceName = "currency_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_id_seq"
    )
    private Long id;

    /**
     * the name
     */
    @Column(name = "name", nullable = false, length = 15, unique = true)
    private String name;

    /**
     * the code
     */
    @Column(name = "code", nullable = false, length = 5, unique = true)
    private String code;

    /**
     * the collection of invoice entities associated with currency
     */
    @JsonIgnore
    @OneToMany(mappedBy = "currencyByCurrencyId")
    private Collection<InvoiceEntity> invoicesById;

    /**
     * the collection of transactions associated with currency
     */
    @JsonIgnore
    @OneToMany(mappedBy = "currencyByCurrencyId")
    private Collection<TransactionEntity> transactionsById;


    /**
     * Instantiates a new Currency entity.
     *
     * @param name the name
     * @param code the code
     */
    public CurrencyEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
