package com.polsl.factoringcompany.currency;

import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.exceptions.NotUniqueException;
import com.polsl.factoringcompany.exceptions.ValueImproperException;
import com.polsl.factoringcompany.stringvalidation.StringValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Michal Goral
 */
@Service
@AllArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public List<CurrencyEntity> getCurrencies() {
        return currencyRepository.findAll();
    }

    public CurrencyEntity getCurrency(Long id) throws IdNotFoundInDatabaseException {
        return this.currencyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Currency", id));
    }

    public CurrencyEntity addCurrency(String name, String code) {
        validating(name, code);
        try {
            return currencyRepository.save(new CurrencyEntity(StringUtils.capitalize(name), code.toUpperCase()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCurrency(Long id) throws IdNotFoundInDatabaseException {
        try {
            this.currencyRepository.deleteById(id);
        } catch (RuntimeException ignored) {
            throw new IdNotFoundInDatabaseException("Currency", id);
        }
    }

    @Transactional
    public CurrencyEntity updateCurrency(Long id, CurrencyRequestDto currencyRequestDto) {

        Optional<CurrencyEntity> currencyEntity = currencyRepository.findById(id);

        if (currencyEntity.isEmpty())
            throw new IdNotFoundInDatabaseException("Currency", id);

        try {
            updateValidating(id, currencyRequestDto.getName(), currencyRequestDto.getCode());
            currencyEntity.get().setName(StringUtils.capitalize(currencyRequestDto.getName()));
            currencyEntity.get().setCode(currencyRequestDto.getCode().toUpperCase());
            return this.currencyRepository.save(currencyEntity.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void validating(String name, String code) {

        if (StringValidator.stringWithSpacesImproper(name, 15))
            throw new ValueImproperException(name);

        if (StringValidator.stringWithoutSpacesImproper(code, 5))
            throw new ValueImproperException(code, "code");

        if (ifNameTaken(name))
            throw new NotUniqueException("Currency", "name", name);

        if (ifCodeTaken(code))
            throw new NotUniqueException("Currency", "code", code);
    }

    private void updateValidating(Long id, String name, String code) {

        if (StringValidator.stringWithSpacesImproper(name, 15))
            throw new ValueImproperException(name);

        if (StringValidator.stringWithoutSpacesImproper(code, 5))
            throw new ValueImproperException(code, "code");

        if (ifNameTakenUpdating(id, name))
            throw new NotUniqueException("Currency", "name", name);

        if (ifCodeTakenUpdating(id, code))
            throw new NotUniqueException("Currency", "code", code);
    }

    public boolean ifNameTaken(String name) {
        Optional<CurrencyEntity> foundByName = currencyRepository.findCurrencyEntityByName(
                StringUtils.capitalize(name));
        return foundByName.isPresent();
    }

    public boolean ifCodeTaken(String code) {
        Optional<CurrencyEntity> foundByName = currencyRepository.findCurrencyEntityByCode(code.toUpperCase());
        return foundByName.isPresent();
    }

    public boolean ifNameTakenUpdating(Long id, String name) {
        Optional<CurrencyEntity> currencyEntityByName = currencyRepository.findCurrencyEntityByName(
                StringUtils.capitalize(name));
        Optional<CurrencyEntity> currencyEntityById = currencyRepository.findById(id);

        if (currencyEntityById.isEmpty())
            throw new IdNotFoundInDatabaseException("Currency", id);
        if (currencyEntityByName.isEmpty())
            return false;

        return !currencyEntityByName.get().getId().equals(currencyEntityById.get().getId());
    }

    public boolean ifCodeTakenUpdating(Long id, String code) {
        Optional<CurrencyEntity> currencyEntityByCode = currencyRepository.findCurrencyEntityByCode(code.toUpperCase());
        Optional<CurrencyEntity> currencyEntityById = currencyRepository.findById(id);

        if (currencyEntityById.isEmpty())
            throw new IdNotFoundInDatabaseException("Currency", id);
        if (currencyEntityByCode.isEmpty())
            return false;

        return !currencyEntityByCode.get().getId().equals(currencyEntityById.get().getId());
    }

    public CurrencyEntity getCurrencyByCurrencyName(String name) {
        return this.currencyRepository.findCurrencyEntityByName(name)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Currency", 0L));
    }

}
