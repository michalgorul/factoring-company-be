package com.polsl.factoringcompany.bankaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface bank account repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    /**
     * Finds BankAccountEntity by bank account number.
     *
     * @param bankAccountNumber the bank account number
     * @return the optional BankAccountEntity
     */
    Optional<BankAccountEntity> findByBankAccountNumber(String bankAccountNumber);

    /**
     * Finds BankAccountEntity by company id.
     *
     * @param companyId the company id
     * @return the optional BankAccountEntity
     */
    Optional<BankAccountEntity> findByCompanyId(Integer companyId);
}
