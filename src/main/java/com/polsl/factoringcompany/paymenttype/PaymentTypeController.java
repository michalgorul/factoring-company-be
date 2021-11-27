package com.polsl.factoringcompany.paymenttype;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/payment-type")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;


    @GetMapping
    public List<PaymentTypeEntity> getPaymentTypes() {
        return paymentTypeService.getPaymentTypes();
    }


    @GetMapping(path = "/{id}")
    public PaymentTypeEntity getPaymentType(@PathVariable Long id) {
        return this.paymentTypeService.getPaymentType(id);
    }


    @PostMapping
    public PaymentTypeEntity addPaymentType(@RequestParam String paymentTypeName) {
        return this.paymentTypeService.addPaymentType(paymentTypeName);
    }


    @PutMapping("/{id}")
    public PaymentTypeEntity updatePaymentType(@PathVariable Long id, @RequestParam String paymentTypeName) {
        return this.paymentTypeService.updatePaymentType(id, paymentTypeName);
    }


    @DeleteMapping("/{id}")
    public void deletePaymentType(@PathVariable Long id) {
        paymentTypeService.deletePaymentType(id);
    }
}
