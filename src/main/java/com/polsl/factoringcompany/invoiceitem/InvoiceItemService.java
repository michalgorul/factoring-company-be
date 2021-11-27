package com.polsl.factoringcompany.invoiceitem;

import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    public List<InvoiceItemEntity> getInvoiceItems() {
        return this.invoiceItemRepository.findAll();
    }

    public InvoiceItemEntity getInvoiceItem(Long id) {
        return this.invoiceItemRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Invoice item", id));
    }

    public InvoiceItemEntity getInvoiceItemFromInvoiceId(Long invoiceId) {
        return this.invoiceItemRepository.findByInvoiceId(Math.toIntExact(invoiceId))
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Invoice item", invoiceId));
    }

    public InvoiceItemEntity addInvoiceItem(InvoiceItemDto invoiceItemDto) {
        try {
            return this.invoiceItemRepository.save(new InvoiceItemEntity(invoiceItemDto));

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void deleteInvoiceItem(Long id) {
        try {
            this.invoiceItemRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Invoice item", id);
        }
    }
}
