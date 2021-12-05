package com.polsl.factoringcompany.bankaccount;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type bank account controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/bank-account")
public class BankAccountController {

    /**
     * the bank account service bean
     */
    private final BankAccountService bankAccountService;

    /**
     * Gets all bank accounts from database.
     *
     * @return the bank accounts
     */
    @GetMapping
    public List<BankAccountEntity> getBankAccounts() {
        return this.bankAccountService.getBankAccounts();
    }

    /**
     * Gets bank account specified by id.
     *
     * @param id the id
     * @return the bank account
     */
    @GetMapping(path = "/{id}")
    public BankAccountEntity getBankAccount(@PathVariable Long id) {
        return this.bankAccountService.getBankAccount(id);
    }

    /**
     * Gets currently logged user's bank account.
     *
     * @return the current user bank account
     */
    @GetMapping(path = "/current")
    public BankAccountEntity getCurrentUserBankAccount() {
        return this.bankAccountService.getCurrentUserBankAccount();
    }

    /**
     * Gets company's bank account.
     *
     * @param companyId the company id
     * @return the company bank account
     */
    @GetMapping(path = "/company/{companyId}")
    public BankAccountEntity getCompanyBankAccount(@PathVariable Long companyId) {
        return this.bankAccountService.getCompanyBankAccount(companyId);
    }

    /**
     * Updates currently logged user's bank account.
     *
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    @PutMapping(path = "/current")
    public BankAccountEntity updateCurrentUserBankAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.updateCurrentUserBankAccount(bankAccountRequestDto);
    }

    /**
     * Updates bank account entity specified by id.
     *
     * @param id                    the id
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    @PutMapping(path = "/{id}")
    public BankAccountEntity updateBankAccount(@PathVariable Long id, @RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.updateBankAccount(id, bankAccountRequestDto);
    }

    /**
     * Creates currently logged user's bank account entity.
     *
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    @PostMapping(path = "/current")
    public BankAccountEntity createCurrentUserBankAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.createCurrentUserBankAccount(bankAccountRequestDto);
    }

    /**
     * Creates customer's bank account entity.
     *
     * @param customerId            the customer id
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    @PostMapping(path = "/customer/{customerId}")
    public BankAccountEntity createCustomersBankAccount(@PathVariable Long customerId, @RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return this.bankAccountService.createCustomersBankAccount(customerId, bankAccountRequestDto);
    }

    /**
     * Deletes bank account from database.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable Long id) {
        this.bankAccountService.deleteBankAccount(id);
    }
}
