package com.polsl.factoringcompany.credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface credit repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

    /**
     * Finds all credits by user id in list.
     *
     * @param userId the user id
     * @return the list of credits
     */
    List<CreditEntity> findAllByUserId(int userId);

    /**
     * Gets new credit number.
     *
     * @param month the month
     * @param year  the year
     * @return the new credit number
     */
    @Query(value = "SELECT max(c.id) FROM credit c " +
            "WHERE FUNCTION('MONTH', c.creationDate) = ?1 AND " +
            "FUNCTION('YEAR', c.creationDate) = ?2")
    Long getCreditNumber(int month, int year);

    /**
     * Finds credit entity specified by credit number.
     *
     * @param creditNumber the credit number
     * @return the credit entity optional
     */
    Optional<CreditEntity> findByCreditNumber(String creditNumber);

    /**
     * Finds all credit entities specified by status.
     *
     * @param status the status
     * @return the credit entities
     */
    List<CreditEntity> findAllByStatusEquals(String status);
}
