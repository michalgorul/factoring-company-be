package com.polsl.factoringcompany.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type credit controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/credit")
public class CreditController {

    /**
     * the credit service bean
     */
    private final CreditService creditService;

    /**
     * Gets all credit entities from database.
     *
     * @return the credits
     */
    @GetMapping
    public List<CreditEntity> getCredits() {
        return this.creditService.getCredits();
    }

    /**
     * Gets all credit entities associated with currently logged user in JWT token.
     *
     * @return the credits current user
     */
    @GetMapping(path = "/current")
    public List<CreditEntity> getCreditsCurrentUser() {
        return this.creditService.getCreditsCurrentUser();
    }

    /**
     * Gets credit entity specified by id.
     *
     * @param id the id
     * @return the credit
     */
    @GetMapping(path = "/{id}")
    public CreditEntity getCredit(@PathVariable Long id) {
        return this.creditService.getCredit(id);
    }

    /**
     * Gets credit number from credit specified by id.
     *
     * @param id the id
     * @return the credit number
     */
    @GetMapping(path = "/name/{id}")
    public String getCreditNumber(@PathVariable Long id) {
        return this.creditService.getCreditNumber(id);
    }

    /**
     * Deletes credit from database.
     *
     * @param id the id
     */
    @DeleteMapping(path = "/{id}")
    public void deleteCredit(@PathVariable Long id) {
        this.creditService.deleteCredit(id);
    }

    /**
     * Creates credit entity associated with currently logged user in JWT token.
     *
     * @param creditRequestDto the credit request dto
     * @return the credit entity
     */
    @PostMapping
    public CreditEntity createCurrentUserCredit(@RequestBody CreditRequestDto creditRequestDto) {
        return this.creditService.createCurrentUserCredit(creditRequestDto);
    }

    /**
     * Updates amount paid by user specified by standard payment.
     *
     * @param id the id
     * @return the credit entity
     */
    @PostMapping(path = "/standard/{id}")
    public CreditEntity addStandardPayment(@PathVariable Long id) {
        return this.creditService.addStandardPayment(id);
    }

    /**
     * Creates overpay payment on credit entity specified by id.
     *
     * @param amount the amount
     * @param id     the id
     * @return the credit entity
     */
    @PostMapping(path = "/overpay/{id}")
    public CreditEntity addOverpayPayment(@RequestBody Double amount, @PathVariable Long id) {
        return this.creditService.addOverpayPayment(amount, id);
    }

    /**
     * Gets the amount left to pay that currently logged user in JWT token has.
     *
     * @return the left to pay amount
     */
    @GetMapping("/left")
    public Double getLeftToPay() {
        return this.creditService.getLeftToPay();
    }

    /**
     * Gets schedule of payments of specific credit entity.
     *
     * @param id the id
     * @return the schedule
     */
    @GetMapping(path = "/schedule/{id}")
    public List<CreditSchedule> getSchedule(@PathVariable Long id) {
        return this.creditService.getSchedule(id);
    }
}
