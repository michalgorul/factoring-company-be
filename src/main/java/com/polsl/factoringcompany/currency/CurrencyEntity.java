package com.polsl.factoringcompany.currency;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import com.polsl.factoringcompany.transaction.TransactionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "currency", schema ="public")
public class CurrencyEntity {

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

    @Column(name = "name", nullable = false, length = 15, unique = true)
    private String name;

    @Column(name = "code", nullable = false, length = 5, unique = true)
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "currencyByCurrencyId")
    private Collection<InvoiceEntity> invoicesById;

    @JsonIgnore
    @OneToMany(mappedBy = "currencyByCurrencyId")
    private Collection<TransactionEntity> transactionsById;


    public CurrencyEntity() {
    }

    public CurrencyEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyEntity that = (CurrencyEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
