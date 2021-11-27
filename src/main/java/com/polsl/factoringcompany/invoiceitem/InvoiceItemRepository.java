package com.polsl.factoringcompany.invoiceitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long> {
    Optional<InvoiceItemEntity> findByInvoiceId(int invoiceId);
}
