package com.polsl.factoringcompany.paymenttype;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Payment type controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/payment-type")
public class PaymentTypeController {

    /**
     * the payment service bean
     */
    private final PaymentTypeService paymentTypeService;


    /**
     * Gets all payment types from database.
     *
     * @return the payment types
     */
    @GetMapping
    public List<PaymentTypeEntity> getPaymentTypes() {
        return paymentTypeService.getPaymentTypes();
    }


    /**
     * Gets payment type specified by id.
     *
     * @param id the id
     * @return the payment type
     */
    @GetMapping(path = "/{id}")
    public PaymentTypeEntity getPaymentType(@PathVariable Long id) {
        return this.paymentTypeService.getPaymentType(id);
    }


    /**
     * Creates payment type entity.
     *
     * @param paymentTypeName the payment type name
     * @return the payment type entity
     */
    @PostMapping
    public PaymentTypeEntity addPaymentType(@RequestParam String paymentTypeName) {
        return this.paymentTypeService.addPaymentType(paymentTypeName);
    }


    /**
     * Updates payment type payment entity.
     *
     * @param id              the id
     * @param paymentTypeName the payment type name
     * @return the payment type entity
     */
    @PutMapping("/{id}")
    public PaymentTypeEntity updatePaymentType(@PathVariable Long id, @RequestParam String paymentTypeName) {
        return this.paymentTypeService.updatePaymentType(id, paymentTypeName);
    }


    /**
     * Deletes payment type specified by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deletePaymentType(@PathVariable Long id) {
        paymentTypeService.deletePaymentType(id);
    }
}
