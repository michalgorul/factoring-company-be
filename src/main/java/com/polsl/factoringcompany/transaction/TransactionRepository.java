package com.polsl.factoringcompany.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Transaction repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    /**
     * Finds all transactions by credit id.
     *
     * @param creditId the desired credit id
     * @return the list of transactions
     */
    List<TransactionEntity> findAllByCreditId(Integer creditId);

    /**
     * Find all transactions by user id whereas invoice id is null.
     *
     * @param userId the desired user id
     * @return the list
     */
    List<TransactionEntity> findAllByUserIdAndInvoiceIdIsNull(int userId);

    /**
     * Find all transactions by user id whereas credit id is null.
     *
     * @param userId the desired user id
     * @return the list
     */
    List<TransactionEntity> findAllByUserIdAndCreditIdIsNull(int userId);


}
