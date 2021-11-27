package com.polsl.factoringcompany.invoiceitem;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/invoice-item")
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @GetMapping
    public List<InvoiceItemEntity> getInvoiceItems() {
        return this.invoiceItemService.getInvoiceItems();
    }


    @GetMapping(path = "/{id}")
    public InvoiceItemEntity getInvoiceItem(@PathVariable Long id) {
        return this.invoiceItemService.getInvoiceItem(id);
    }


    @PostMapping
    public InvoiceItemEntity addInvoiceItem(@RequestBody InvoiceItemDto invoiceItemDto) {
        return this.invoiceItemService.addInvoiceItem(invoiceItemDto);
    }


    @PutMapping("/{id}")
    public InvoiceItemEntity updateInvoiceItem(@PathVariable Long id, @RequestBody InvoiceItemDto invoiceItemDto) {
        return this.invoiceItemService.updateInvoiceItem(id, invoiceItemDto);
    }


    @DeleteMapping("/{id}")
    public void deleteInvoiceItem(@PathVariable Long id) {
        this.invoiceItemService.deleteInvoiceItem(id);
    }
}
