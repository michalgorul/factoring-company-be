package com.polsl.factoringcompany.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface currency repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    /**
     * Finds currency entity specified by name.
     *
     * @param name the name of currency
     * @return the optional currency entity
     */
    Optional<CurrencyEntity> findCurrencyEntityByName(String name);

    /**
     * Find currency entity specified by code.
     *
     * @param code the code of currency
     * @return the currency entity optional
     */
    Optional<CurrencyEntity> findCurrencyEntityByCode(String code);
}
