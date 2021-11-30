package com.polsl.factoringcompany.transaction;

import com.polsl.factoringcompany.currency.CurrencyService;
import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Transaction service. Used to connect controller with Data access object
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class TransactionService {

    /**
     * the bean of transaction repository
     */
    private final TransactionRepository transactionRepository;

    /**
     * the bean of user service
     */
    private final UserService userService;

    /**
     * the bean of currency service
     */
    private final CurrencyService currencyService;

    /**
     * Gets all transactions from database.
     *
     * @return the transactions
     */
    public List<TransactionEntity> getTransactions() {
        return this.transactionRepository.findAll();
    }

    /**
     * Gets all desired credit transactions.
     *
     * @param creditId the credit id
     * @return the credit transactions
     */
    public List<TransactionEntity> getCreditTransactions(Integer creditId) {
        return this.transactionRepository.findAllByCreditId(creditId);
    }


    /**
     * Gets desired transaction.
     *
     * @param id the id of transaction
     * @return the transaction
     */
    public TransactionEntity getTransaction(Long id) {
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Transaction", id));
    }


    /**
     * Deletes transaction.
     *
     * @param id the desired transaction id
     */
    public void deleteTransaction(Long id) {
        try {
            this.transactionRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Transaction", id);
        }
    }


    /**
     * Creates new transaction entity for currently logged in JWT token user.
     *
     * @param transactionRequestDto the transaction request dto
     * @return the transaction entity
     */
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

    /**
     * Creates new transaction entity for specified user.
     *
     * @param transactionRequestDto the transaction request dto
     * @param userId                the user id
     */
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

    /**
     * Updates transaction registered in database.
     *
     * @param id the id of desired transaction
     * @return the transaction entity
     */
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

    /**
     * Get all currently logged in JWT token user credit transactions list.
     *
     * @return the list of transactions
     */
    public List<TransactionEntity> getAllCurrentUserCreditTransactions() {
        return this.transactionRepository.findAllByUserIdAndInvoiceIdIsNull(Math.toIntExact(userService.getCurrentUserId()));
    }

    /**
     * Get all currently logged in JWT token user invoice transactions list.
     *
     * @return the list of transactions
     */
    public List<TransactionEntity> getAllCurrentUserInvoiceTransactions(){
        return this.transactionRepository.findAllByUserIdAndCreditIdIsNull(Math.toIntExact(userService.getCurrentUserId()));
    }
}
