package com.polsl.factoringcompany.transaction;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Transaction controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController {

    /**
     * the transaction service bean
     */
    private final TransactionService transactionService;

    /**
     * Gets all transactions from database.
     *
     * @return the transactions
     */
    @GetMapping
    public List<TransactionEntity> getTransactions() {
        return this.transactionService.getTransactions();
    }

    /**
     * Gets all transactions made in specific credit.
     *
     * @param creditId the desired credit id
     * @return the desired credit transactions
     */
    @GetMapping(path = "/credit/{creditId}")
    public List<TransactionEntity> getCreditTransactions(@PathVariable Integer creditId) {
        return this.transactionService.getCreditTransactions(creditId);
    }

    /**
     * Gets all current user logged in JWT token transactions made in credits.
     *
     * @return the all current user credit transactions
     */
    @GetMapping(path = "/credit")
    public List<TransactionEntity> getAllCurrentUserCreditTransactions() {
        return this.transactionService.getAllCurrentUserCreditTransactions();
    }

    /**
     * Gets all current user logged in JWT token transactions made in invoices.
     *
     * @return the all current user invoice transactions
     */
    @GetMapping(path = "/invoice")
    public List<TransactionEntity> getAllCurrentUserInvoiceTransactions() {
        return this.transactionService.getAllCurrentUserInvoiceTransactions();
    }

    /**
     * Gets desired transaction.
     *
     * @param id the id of desired transaction
     * @return the desired transaction
     */
    @GetMapping(path = "/{id}")
    public TransactionEntity getTransaction(@PathVariable Long id) {
        return this.transactionService.getTransaction(id);
    }

    /**
     * Creates new transaction entity.
     *
     * @param transactionEntity the transaction entity
     * @return the transaction entity
     */
    @PostMapping
    public TransactionEntity addTransaction(@RequestBody TransactionRequestDto transactionEntity) {
        return this.transactionService.addTransaction(transactionEntity);
    }

    /**
     * Updates specific transaction entity.
     *
     * @param id the id of desired transaction
     * @return the transaction entity
     */
    @PutMapping("/{id}")
    public TransactionEntity updateTransaction(@PathVariable Long id) {
        return this.transactionService.updateTransaction(id);
    }

    /**
     * Deleted transaction.
     *
     * @param id the id of desired transaction
     */
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        this.transactionService.deleteTransaction(id);
    }
}
