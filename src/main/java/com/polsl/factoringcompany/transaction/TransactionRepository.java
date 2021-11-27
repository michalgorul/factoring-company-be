package com.polsl.factoringcompany.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByCreditId(Integer creditId);

    List<TransactionEntity> findAllByInvoiceId(Integer invoiceId);

    List<TransactionEntity> findAllByUserIdAndInvoiceIdIsNull(int userId);

    List<TransactionEntity> findAllByUserIdAndCreditIdIsNull(int userId);


}
