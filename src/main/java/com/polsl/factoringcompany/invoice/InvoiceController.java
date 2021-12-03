package com.polsl.factoringcompany.invoice;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Invoice controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/invoice")
public class InvoiceController {

    /**
     * the invoice service bean
     */
    private final InvoiceService invoiceService;

    /**
     * Gets all invoices from database.
     *
     * @return the invoices
     */
    @GetMapping
    public List<InvoiceEntity> getInvoices() {
        return this.invoiceService.getInvoices();
    }

    /**
     * Gets invoices for currently logged user in JWT token.
     *
     * @return the invoices for current user
     */
    @GetMapping(path = "/current")
    public List<InvoiceEntity> getInvoicesCurrentUser() {
        return this.invoiceService.getInvoicesCurrentUser();
    }

    /**
     * Gets invoice specified by id.
     *
     * @param id the id
     * @return the invoice
     */
    @GetMapping(path = "/{id}")
    public InvoiceEntity getInvoice(@PathVariable Long id) {
        return this.invoiceService.getInvoice(id);
    }


    /**
     * Creates invoice entity and saves it to database.
     *
     * @param invoiceCreateRequest the invoice create request
     * @return the invoice entity
     */
    @PostMapping
    public InvoiceEntity addInvoice(@RequestBody InvoiceCreateRequest invoiceCreateRequest) {
        return this.invoiceService.addInvoice(invoiceCreateRequest);
    }


    /**
     * Updates invoice entity and saves it to database.
     *
     * @param id         the id
     * @param invoiceDto the invoice dto
     * @return the invoice entity
     */
    @PutMapping("/{id}")
    public InvoiceEntity updateInvoice(@PathVariable Long id, @RequestBody InvoiceDto invoiceDto) {
        return this.invoiceService.updateInvoice(id, invoiceDto);
    }

    /**
     * Updates invoice payment info of all invoices.
     *
     * @param id                              the id
     * @param invoicePaymentInfoUpdateRequest the invoice payment info update request
     * @return the invoice entity
     */
    @PutMapping("/payment/currency/{id}")
    public InvoiceEntity updateInvoicePaymentInfo(@PathVariable Long id,
                                                  @RequestBody InvoicePaymentInfoUpdateRequest invoicePaymentInfoUpdateRequest) {
        return this.invoiceService.updateInvoicePaymentInfo(id, invoicePaymentInfoUpdateRequest);
    }


    /**
     * Deletes invoice specified by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        this.invoiceService.deleteInvoice(id);
    }

    /**
     * Gets ass invoice statuses.
     *
     * @return the invoice statuses
     */
    @GetMapping("/statuses")
    public List<String> getInvoiceStatuses() {
        return this.invoiceService.getInvoiceStatuses();
    }

    /**
     * Gets all active invoices paid value.
     *
     * @return the active invoices paid value
     */
    @GetMapping("/paid")
    public Double getActiveInvoicesPaidValue() {
        return this.invoiceService.getActiveInvoicesPaidValue();
    }

    /**
     * Updates value paid for invoice specified by id.
     *
     * @param id the id
     */
    @PostMapping(path = "/pay/{id}")
    public void payForInvoice(@PathVariable Long id) {
        this.invoiceService.payForInvoice(id);
    }
}
