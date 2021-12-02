package com.polsl.factoringcompany.invoiceitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface invoice item repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long> {
    /**
     * Finds by invoice specified by id optional.
     *
     * @param invoiceId the invoice id
     * @return the invoice item entity optional
     */
    Optional<InvoiceItemEntity> findByInvoiceId(int invoiceId);
}
