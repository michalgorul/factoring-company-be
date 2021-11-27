package com.polsl.factoringcompany.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/credit")
public class CreditController {
    private final CreditService creditService;

    @GetMapping
    public List<CreditEntity> getCredits() {
        return this.creditService.getCredits();
    }

    @GetMapping(path = "/current")
    public List<CreditEntity> getCreditsCurrentUser() {
        return this.creditService.getCreditsCurrentUser();
    }

    @GetMapping(path = "/{id}")
    public CreditEntity getCredit(@PathVariable Long id) {
        return this.creditService.getCredit(id);
    }

    @GetMapping(path = "/name/{id}")
    public String getCreditNumber(@PathVariable Long id) {
        return this.creditService.getCreditNumber(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCredit(@PathVariable Long id) {
        this.creditService.deleteCredit(id);
    }

    @PostMapping
    public CreditEntity createCurrentUserCredit(@RequestBody CreditRequestDto creditRequestDto) {
        return this.creditService.createCurrentUserCredit(creditRequestDto);
    }

    @PostMapping(path = "/standard/{id}")
    public CreditEntity addStandardPayment(@PathVariable Long id) {
        return this.creditService.addStandardPayment(id);
    }

    @PostMapping(path = "/overpay/{id}")
    public CreditEntity addOverpayPayment(@RequestBody Double amount, @PathVariable Long id) {
        return this.creditService.addOverpayPayment(amount, id);
    }

    @GetMapping("/left")
    public Double getLeftToPay() {
        return this.creditService.getLeftToPay();
    }

    @GetMapping(path = "/schedule/{id}")
    public List<CreditSchedule> getSchedule(@PathVariable Long id) {
        return this.creditService.getSchedule(id);
    }
}
