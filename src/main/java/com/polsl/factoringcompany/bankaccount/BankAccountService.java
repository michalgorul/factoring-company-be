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

@Service
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserService userService;
    private final CustomerService customerService;

    public List<BankAccountEntity> getBankAccounts() {
        return this.bankAccountRepository.findAll();
    }

    public BankAccountEntity getBankAccount(Long id) {
        return this.bankAccountRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Bank account", id));
    }

    public void deleteBankAccount(Long id) {
        try {
            this.bankAccountRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Bank Account", id);
        }
    }

    private void updateValidate(Long id, BankAccountRequestDto bankAccountEntity) {
        if (ifBankAccountNumberTakenUpdating(id, bankAccountEntity.getBankAccountNumber()))
            throw new NotUniqueException("Bank Account", "number", bankAccountEntity.getBankAccountNumber());

        nameValidator(bankAccountEntity);
    }

    private void addValidate(BankAccountRequestDto bankAccountRequestDto) {
        if (ifBankAccountNumberTakenAdding(bankAccountRequestDto.getBankAccountNumber()))
            throw new NotUniqueException("Bank Account", "number", bankAccountRequestDto.getBankAccountNumber());

        nameValidator(bankAccountRequestDto);
    }

    private boolean ifBankAccountNumberTakenAdding(String bankAccountNumber) {
        Optional<BankAccountEntity> bankAccountEntityOptional = bankAccountRepository
                .findByBankAccountNumber(bankAccountNumber);
        return bankAccountEntityOptional.isPresent();
    }

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

    public BankAccountEntity getCurrentUserBankAccount() {
        UserEntity currentUser = userService.getCurrentUser();
        return this.bankAccountRepository.findByCompanyId(currentUser.getCompanyId())
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Bank account", 0L));
    }

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

    public BankAccountEntity getCustomersBankAccount(CustomerEntity customerEntity) {
        return this.bankAccountRepository.findByCompanyId(customerEntity.getCompanyId())
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Company", Long.valueOf(customerEntity.getCompanyId())));

    }

    public BankAccountEntity getCompanyBankAccount(Long companyId) {
        return this.bankAccountRepository.findByCompanyId(Math.toIntExact(companyId)).orElse(null);
    }

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
