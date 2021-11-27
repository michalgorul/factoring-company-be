package com.polsl.factoringcompany.invoice;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public List<InvoiceEntity> getInvoices() {
        return this.invoiceService.getInvoices();
    }

    @GetMapping(path = "/current")
    public List<InvoiceEntity> getInvoicesCurrentUser() {
        return this.invoiceService.getInvoicesCurrentUser();
    }

    @GetMapping(path = "/{id}")
    public InvoiceEntity getInvoice(@PathVariable Long id) {
        return this.invoiceService.getInvoice(id);
    }


    @PostMapping
    public InvoiceEntity addInvoice(@RequestBody InvoiceCreateRequest invoiceCreateRequest) {
        return this.invoiceService.addInvoice(invoiceCreateRequest);
    }


    @PutMapping("/{id}")
    public InvoiceEntity updateInvoice(@PathVariable Long id, @RequestBody InvoiceDto invoiceDto) {
        return this.invoiceService.updateInvoice(id, invoiceDto);
    }

    @PutMapping("/payment/currency/{id}")
    public InvoiceEntity updateInvoicePaymentInfo(@PathVariable Long id,
                                                  @RequestBody InvoicePaymentInfoUpdateRequest invoicePaymentInfoUpdateRequest) {
        return this.invoiceService.updateInvoicePaymentInfo(id, invoicePaymentInfoUpdateRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        this.invoiceService.deleteInvoice(id);
    }

    @GetMapping("/statuses")
    public List<String> getInvoiceStatuses() {
        return this.invoiceService.getInvoiceStatuses();
    }

    @GetMapping("/paid")
    public Double getActiveInvoicesPaidValue() {
        return this.invoiceService.getActiveInvoicesPaidValue();
    }

    @PostMapping(path = "/pay/{id}")
    public void payForInvoice(@PathVariable Long id) {
        this.invoiceService.payForInvoice(id);
    }
}
