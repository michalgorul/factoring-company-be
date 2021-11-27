package com.polsl.factoringcompany.bankaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    Optional<BankAccountEntity> findByBankAccountNumber(String bankAccountNumber);

    Optional<BankAccountEntity> findByCompanyId(Integer companyId);
}
