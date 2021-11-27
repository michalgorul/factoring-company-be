package com.polsl.factoringcompany.transaction;

import com.polsl.factoringcompany.currency.CurrencyService;
import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CurrencyService currencyService;

    public List<TransactionEntity> getTransactions() {
        return this.transactionRepository.findAll();
    }

    public List<TransactionEntity> getCreditTransactions(Integer creditId) {
        return this.transactionRepository.findAllByCreditId(creditId);
    }


    public TransactionEntity getTransaction(Long id) {
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Transaction", id));
    }


    public void deleteTransaction(Long id) {
        try {
            this.transactionRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Transaction", id);
        }
    }


    public TransactionEntity addTransaction(TransactionRequestDto transactionRequestDto) {
        try {
            return this.transactionRepository.save(new TransactionEntity(
                    transactionRequestDto,
                    userService.getCurrentUserId(),
                    currencyService.getCurrencyByCurrencyName("Dollar").getId()
            ));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTransaction(TransactionRequestDto transactionRequestDto, Integer userId) {
        try {
            this.transactionRepository.save(new TransactionEntity(
                    transactionRequestDto,
                    (long) userId,
                    currencyService.getCurrencyByCurrencyName("Dollar").getId()
            ));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionEntity updateTransaction(Long id) {
        Optional<TransactionEntity> transactionEntityOptional = transactionRepository.findById(id);

        if (transactionEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Transaction", id);

        try {
            transactionEntityOptional.get().setTransactionDate(transactionEntityOptional.get().getTransactionDate());
            transactionEntityOptional.get().setValue(transactionEntityOptional.get().getValue());
            transactionEntityOptional.get().setInvoiceId(transactionEntityOptional.get().getInvoiceId());
            transactionEntityOptional.get().setCurrencyId(transactionEntityOptional.get().getCurrencyId());

            return this.transactionRepository.save(transactionEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TransactionEntity> getAllCurrentUserCreditTransactions() {
        return this.transactionRepository.findAllByUserIdAndInvoiceIdIsNull(Math.toIntExact(userService.getCurrentUserId()));
    }

    public List<TransactionEntity> getAllCurrentUserInvoiceTransactions(){
        return this.transactionRepository.findAllByUserIdAndCreditIdIsNull(Math.toIntExact(userService.getCurrentUserId()));
    }
}
