package com.polsl.factoringcompany.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface invoice repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    /**
     * Gets new invoice number.
     *
     * @param month the month
     * @param year  the year
     * @return the last invoice id
     * */
    @Query(value = "SELECT max(i.id) FROM invoice i " +
            "WHERE FUNCTION('MONTH', i.creationDate) = ?1 AND " +
            "FUNCTION('YEAR', i.creationDate) = ?2")
    Long getInvoiceNumber(int month, int year);

    /**
     * Finds all invoices by user id list.
     *
     * @param id the id
     * @return the list
     */
    List<InvoiceEntity> findAllByUserId(Integer id);

    /**
     * Gets all invoice statuses.
     *
     * @return the statuses
     */
    @Query(value = "select distinct status from invoice")
    List<String> getStatuses();

    /**
     * Finds all invoices specified by status list.
     *
     * @param status the status
     * @return the list
     */
    List<InvoiceEntity> findAllByStatusEquals(String status);
}
