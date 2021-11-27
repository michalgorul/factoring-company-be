package com.polsl.factoringcompany.transaction;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionEntity> getTransactions() {
        return this.transactionService.getTransactions();
    }

    @GetMapping(path = "/credit/{creditId}")
    public List<TransactionEntity> getCreditTransactions(@PathVariable Integer creditId) {
        return this.transactionService.getCreditTransactions(creditId);
    }

    @GetMapping(path = "/credit")
    public List<TransactionEntity> getAllCurrentUserCreditTransactions() {
        return this.transactionService.getAllCurrentUserCreditTransactions();
    }

    @GetMapping(path = "/invoice")
    public List<TransactionEntity> getAllCurrentUserInvoiceTransactions() {
        return this.transactionService.getAllCurrentUserInvoiceTransactions();
    }

    @GetMapping(path = "/{id}")
    public TransactionEntity getTransaction(@PathVariable Long id) {
        return this.transactionService.getTransaction(id);
    }

    @PostMapping
    public TransactionEntity addTransaction(@RequestBody TransactionRequestDto transactionEntity) {
        return this.transactionService.addTransaction(transactionEntity);
    }

    @PutMapping("/{id}")
    public TransactionEntity updateTransaction(@PathVariable Long id) {
        return this.transactionService.updateTransaction(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        this.transactionService.deleteTransaction(id);
    }
}
