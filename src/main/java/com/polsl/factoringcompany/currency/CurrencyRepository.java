package com.polsl.factoringcompany.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    Optional<CurrencyEntity> findCurrencyEntityByName(String name);

    Optional<CurrencyEntity> findCurrencyEntityByCode(String code);
}
