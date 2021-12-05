package com.polsl.factoringcompany.bankaccount;

import com.polsl.factoringcompany.customer.CustomerEntity;
import com.polsl.factoringcompany.customer.CustomerService;
import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.exceptions.NotUniqueException;
import com.polsl.factoringcompany.exceptions.ValueImproperException;
import com.polsl.factoringcompany.stringvalidation.StringValidator;
import com.polsl.factoringcompany.user.UserEntity;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * The type bank account service. Used to connect controller with Data access object
 *
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class BankAccountService {

    /**
     * the bank account repository bean
     */
    private final BankAccountRepository bankAccountRepository;

    /**
     * the user service bean
     */
    private final UserService userService;

    /**
     * the customer service bean
     */
    private final CustomerService customerService;

    /**
     * Gets all bank accounts from database.
     *
     * @return the bank accounts
     */
    public List<BankAccountEntity> getBankAccounts() {
        return this.bankAccountRepository.findAll();
    }

    /**
     * Gets bank account specified by id from database.
     *
     * @param id the id
     * @return the bank account
     */
    public BankAccountEntity getBankAccount(Long id) {
        return this.bankAccountRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Bank account", id));
    }

    /**
     * Deletes bank account specified by id from database.
     *
     * @param id the id
     */
    public void deleteBankAccount(Long id) {
        try {
            this.bankAccountRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Bank Account", id);
        }
    }

    /**
     * Validates if Bank account and all the rest names are in proper form while updating
     * @param id the bank account id
     * @param bankAccountEntity the bank account entity
     */
    private void updateValidate(Long id, BankAccountRequestDto bankAccountEntity) {
        if (ifBankAccountNumberTakenUpdating(id, bankAccountEntity.getBankAccountNumber()))
            throw new NotUniqueException("Bank Account", "number", bankAccountEntity.getBankAccountNumber());

        nameValidator(bankAccountEntity);
    }

    /**
     * Validates if Bank account and all the rest names are in proper form
     * while creating new bank account entity
     * @param bankAccountRequestDto the bank account request dto
     */
    private void addValidate(BankAccountRequestDto bankAccountRequestDto) {
        if (ifBankAccountNumberTakenAdding(bankAccountRequestDto.getBankAccountNumber()))
            throw new NotUniqueException("Bank Account", "number", bankAccountRequestDto.getBankAccountNumber());

        nameValidator(bankAccountRequestDto);
    }

    /**
     * Checks if Bank account number is already in use while creating new entity.
     * @param bankAccountNumber the bank account number
     * @return true if it already in use
     */
    private boolean ifBankAccountNumberTakenAdding(String bankAccountNumber) {
        Optional<BankAccountEntity> bankAccountEntityOptional = bankAccountRepository
                .findByBankAccountNumber(bankAccountNumber);
        return bankAccountEntityOptional.isPresent();
    }

    /**
     * Checks if Bank account number is already in use while updating existing entity.
     * @param id the bank account entity id
     * @param bankAccountNumber the bank account number
     * @return true if it already in use
     */
    private boolean ifBankAccountNumberTakenUpdating(Long id, String bankAccountNumber) {
        Optional<BankAccountEntity> bankAccountEntityByBankAccountNumber = bankAccountRepository
                .findByBankAccountNumber(bankAccountNumber);
        Optional<BankAccountEntity> bankAccountEntitybyId = bankAccountRepository.findById(id);

        if (bankAccountEntitybyId.isEmpty())
            throw new IdNotFoundInDatabaseException("Bank Account", id);
        if (bankAccountEntityByBankAccountNumber.isEmpty())
            return false;

        return !bankAccountEntityByBankAccountNumber.get().getId().equals(bankAccountEntitybyId.get().getId());
    }

    /**
     * Validates if names from bank account entity are in proper form
     * while creating new entity or updating existing
     * @param bankAccountEntity the bank account entity
     */
    private void nameValidator(BankAccountRequestDto bankAccountEntity) {
        if (StringValidator.stringWithSpacesImproper(bankAccountEntity.getBankName(), 50)) {
            throw new ValueImproperException(bankAccountEntity.getBankName());
        } else if (StringValidator.stringWithDigitsImproper(bankAccountEntity.getBankSwift(), 8)) {
            throw new ValueImproperException(bankAccountEntity.getBankSwift());
        } else if (StringValidator.stringWithDigitsImproper(bankAccountEntity.getBankAccountNumber(), 28)) {
            throw new ValueImproperException(bankAccountEntity.getBankAccountNumber());
        } else if (!StringValidator.isBankAccountNumberValid(bankAccountEntity.getBankAccountNumber())) {
            throw new ValueImproperException((bankAccountEntity.getBankAccountNumber()));
        }

    }

    /**
     * Gets currently logged in JWT token user's bank account.
     *
     * @return the current user's bank account
     */
    public BankAccountEntity getCurrentUserBankAccount() {
        UserEntity currentUser = userService.getCurrentUser();
        return this.bankAccountRepository.findByCompanyId(currentUser.getCompanyId())
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Bank account", 0L));
    }

    /**
     * Update currently logged user's bank account entity.
     *
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    public BankAccountEntity updateCurrentUserBankAccount(BankAccountRequestDto bankAccountRequestDto) {

        UserEntity currentUser = userService.getCurrentUser();

        Optional<BankAccountEntity> bankAccountEntityOptional =
                bankAccountRepository.findByCompanyId(currentUser.getCompanyId());

        if (bankAccountEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Bank Account", 0L);

        updateValidate(bankAccountEntityOptional.get().getId(), bankAccountRequestDto);

        try {
            bankAccountEntityOptional.get().setBankSwift(bankAccountRequestDto.getBankSwift().toUpperCase());
            bankAccountEntityOptional.get().setBankAccountNumber(bankAccountRequestDto.getBankAccountNumber());
            bankAccountEntityOptional.get().setBankName(StringUtils.capitalize(bankAccountRequestDto.getBankName()));
            bankAccountEntityOptional.get().setCompanyId(bankAccountEntityOptional.get().getCompanyId());

            return this.bankAccountRepository.save(bankAccountEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Creates currently logged user's bank account entity.
     *
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    public BankAccountEntity createCurrentUserBankAccount(BankAccountRequestDto bankAccountRequestDto) {
        UserEntity currentUser = userService.getCurrentUser();

        addValidate(bankAccountRequestDto);

        try {
            return this.bankAccountRepository.save(new BankAccountEntity(
                    bankAccountRequestDto.getBankSwift().toUpperCase(),
                    bankAccountRequestDto.getBankAccountNumber(),
                    StringUtils.capitalize(bankAccountRequestDto.getBankName()),
                    currentUser.getCompanyId()));

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets customer's bank account. If nothing found throws IdNotFoundInDatabaseException
     *
     * @param customerEntity the customer entity
     * @return the customers bank account
     */
    public BankAccountEntity getCustomersBankAccount(CustomerEntity customerEntity) {
        return this.bankAccountRepository.findByCompanyId(customerEntity.getCompanyId())
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Bank account not found"));

    }

    /**
     * Gets company's bank account. If nothing found returns null
     *
     * @param companyId the company id
     * @return the company bank account
     */
    public BankAccountEntity getCompanyBankAccount(Long companyId) {
        return this.bankAccountRepository.findByCompanyId(Math.toIntExact(companyId)).orElse(null);
    }

    /**
     * Creates customer's bank account entity.
     *
     * @param customerId            the customer id
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    public BankAccountEntity createCustomersBankAccount(Long customerId, BankAccountRequestDto bankAccountRequestDto) {
        CustomerEntity customer = customerService.getCustomer(customerId);
        addValidate(bankAccountRequestDto);

        try {
            return this.bankAccountRepository.save(new BankAccountEntity(
                    bankAccountRequestDto.getBankSwift().toUpperCase(),
                    bankAccountRequestDto.getBankAccountNumber(),
                    StringUtils.capitalize(bankAccountRequestDto.getBankName()),
                    customer.getCompanyId()));

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates bank account entity and saves it to database.
     *
     * @param id                    the id
     * @param bankAccountRequestDto the bank account request dto
     * @return the bank account entity
     */
    public BankAccountEntity updateBankAccount(Long id, BankAccountRequestDto bankAccountRequestDto) {
        Optional<BankAccountEntity> bankAccountEntityOptional = this.bankAccountRepository.findById(id);

        if (bankAccountEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Bank Account", 0L);

        updateValidate(bankAccountEntityOptional.get().getId(), bankAccountRequestDto);

        try {
            bankAccountEntityOptional.get().setBankSwift(bankAccountRequestDto.getBankSwift().toUpperCase());
            bankAccountEntityOptional.get().setBankAccountNumber(bankAccountRequestDto.getBankAccountNumber());
            bankAccountEntityOptional.get().setBankName(StringUtils.capitalize(bankAccountRequestDto.getBankName()));
            bankAccountEntityOptional.get().setCompanyId(bankAccountEntityOptional.get().getCompanyId());

            return this.bankAccountRepository.save(bankAccountEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
