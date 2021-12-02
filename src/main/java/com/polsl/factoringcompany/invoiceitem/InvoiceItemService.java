package com.polsl.factoringcompany.invoiceitem;

import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The type Invoice item service. Used to connect controller with Data access object
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class InvoiceItemService {

    /**
     * the invoice item repository beam
     */
    private final InvoiceItemRepository invoiceItemRepository;

    /**
     * Gets all invoice items from database.
     *
     * @return the invoice items
     */
    public List<InvoiceItemEntity> getInvoiceItems() {
        return this.invoiceItemRepository.findAll();
    }

    /**
     * Gets invoice item specified by od.
     *
     * @param id the id
     * @return the invoice item
     */
    public InvoiceItemEntity getInvoiceItem(Long id) {
        return this.invoiceItemRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Invoice item", id));
    }

    /**
     * Gets invoice item from invoice specified by invoice id.
     *
     * @param invoiceId the invoice id
     * @return the invoice item from invoice entity
     */
    public InvoiceItemEntity getInvoiceItemFromInvoiceId(Long invoiceId) {
        return this.invoiceItemRepository.findByInvoiceId(Math.toIntExact(invoiceId))
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Invoice item", invoiceId));
    }

    /**
     * Creates invoice item entity and saves it to database.
     *
     * @param invoiceItemDto the invoice item dto
     * @return the invoice item entity
     */
    public InvoiceItemEntity addInvoiceItem(InvoiceItemDto invoiceItemDto) {
        try {
            return this.invoiceItemRepository.save(new InvoiceItemEntity(invoiceItemDto));

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates invoice item entity and saves it to database.
     *
     * @param id             the id
     * @param invoiceItemDto the invoice item dto
     * @return the invoice item entity
     */
    public InvoiceItemEntity updateInvoiceItem(Long id, InvoiceItemDto invoiceItemDto) {

        Optional<InvoiceItemEntity> invoiceItemEntityOptional = invoiceItemRepository.findById(id);

        if (invoiceItemEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Invoice item", id);

        BigDecimal netValue = BigDecimal.valueOf(invoiceItemDto.getQuantity() * invoiceItemDto.getNetPrice().doubleValue());
        BigDecimal vatValue = BigDecimal.valueOf(invoiceItemDto.getQuantity() * invoiceItemDto.getVatRate().doubleValue() * invoiceItemDto.getNetPrice().doubleValue() / 100);

        try {
            invoiceItemEntityOptional.get().setQuantity(invoiceItemDto.getQuantity());
            invoiceItemEntityOptional.get().setNetPrice(invoiceItemDto.getNetPrice());
            invoiceItemEntityOptional.get().setNetValue(netValue);
            invoiceItemEntityOptional.get().setVatRate(invoiceItemDto.getVatRate());
            invoiceItemEntityOptional.get().setVatValue(vatValue);
            invoiceItemEntityOptional.get().setGrossValue(BigDecimal.valueOf(
                    netValue.doubleValue() + vatValue.doubleValue()));
            invoiceItemEntityOptional.get().setProductId(Math.toIntExact(invoiceItemDto.getProductId()));
            invoiceItemEntityOptional.get().setInvoiceId(Math.toIntExact(invoiceItemDto.getInvoiceId()));

            return this.invoiceItemRepository.save(invoiceItemEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes invoice item from database.
     *
     * @param id the id
     */
    public void deleteInvoiceItem(Long id) {
        try {
            this.invoiceItemRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Invoice item", id);
        }
    }
}
