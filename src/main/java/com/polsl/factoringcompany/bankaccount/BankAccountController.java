package com.polsl.factoringcompany.bankaccount;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/bank-account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccountEntity> getBankAccounts() {
        return this.bankAccountService.getBankAccounts();
    }

    @GetMapping(path = "/{id}")
    public BankAccountEntity getBankAccount(@PathVariable Long id) {
        return this.bankAccountService.getBankAccount(id);
    }

    @GetMapping(path = "/current")
    public BankAccountEntity getCurrentUserBankAccount() {
        return this.bankAccountService.getCurrentUserBankAccount();
    }

    @GetMapping(path = "/company/{companyId}")
    public BankAccountEntity getCompanyBankAccount(@PathVariable Long companyId) {
        return this.bankAccountService.getCompanyBankAccount(companyId);
    }

    @PutMapping(path = "/current")
    public BankAccountEntity updateCurrentUserBankAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.updateCurrentUserBankAccount(bankAccountRequestDto);
    }

    @PutMapping(path = "/{id}")
    public BankAccountEntity updateBankAccount(@PathVariable Long id, @RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.updateBankAccount(id, bankAccountRequestDto);
    }

    @PostMapping(path = "/current")
    public BankAccountEntity createCurrentUserBankAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.createCurrentUserBankAccount(bankAccountRequestDto);
    }

    @PostMapping(path = "/customer/{customerId}")
    public BankAccountEntity createCustomersBankAccount(@PathVariable Long customerId, @RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.createCustomersBankAccount(customerId, bankAccountRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable Long id) {
        this.bankAccountService.deleteBankAccount(id);
    }
}
