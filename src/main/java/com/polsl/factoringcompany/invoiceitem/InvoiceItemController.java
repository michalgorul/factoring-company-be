package com.polsl.factoringcompany.invoiceitem;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Invoice item controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/invoice-item")
public class InvoiceItemController {

    /**
     * the invoice item service bean
     */
    private final InvoiceItemService invoiceItemService;

    /**
     * Gets invoice all items from database.
     *
     * @return the invoice items
     */
    @GetMapping
    public List<InvoiceItemEntity> getInvoiceItems() {
        return this.invoiceItemService.getInvoiceItems();
    }


    /**
     * Gets invoice item specified by id.
     *
     * @param id the id
     * @return the invoice item
     */
    @GetMapping(path = "/{id}")
    public InvoiceItemEntity getInvoiceItem(@PathVariable Long id) {
        return this.invoiceItemService.getInvoiceItem(id);
    }


    /**
     * Creates invoice item entity and saves it to database.
     *
     * @param invoiceItemDto the invoice item dto
     * @return the invoice item entity
     */
    @PostMapping
    public InvoiceItemEntity addInvoiceItem(@RequestBody InvoiceItemDto invoiceItemDto) {
        return this.invoiceItemService.addInvoiceItem(invoiceItemDto);
    }


    /**
     * Updates invoice item entity and saves to database.
     *
     * @param id             the id
     * @param invoiceItemDto the invoice item dto
     * @return the invoice item entity
     */
    @PutMapping("/{id}")
    public InvoiceItemEntity updateInvoiceItem(@PathVariable Long id, @RequestBody InvoiceItemDto invoiceItemDto) {
        return this.invoiceItemService.updateInvoiceItem(id, invoiceItemDto);
    }


    /**
     * Deletes invoice item from database.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteInvoiceItem(@PathVariable Long id) {
        this.invoiceItemService.deleteInvoiceItem(id);
    }
}
