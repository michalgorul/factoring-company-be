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
 * The type Currency service. Used to connect controller with Data access object
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class CurrencyService {

    /**
     * the currency repository bean
     */
    private final CurrencyRepository currencyRepository;

    /**
     * Gets all currencies from database.
     *
     * @return the currencies
     */
    public List<CurrencyEntity> getCurrencies() {
        return currencyRepository.findAll();
    }

    /**
     * Gets currency specified by id.
     *
     * @param id the id
     * @return the currency
     * @throws IdNotFoundInDatabaseException the id not found in database exception
     */
    public CurrencyEntity getCurrency(Long id) throws IdNotFoundInDatabaseException {
        return this.currencyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Currency", id));
    }

    /**
     * Creates currency entity and saves it to database.
     *
     * @param name the name
     * @param code the code
     * @return the currency entity
     */
    public CurrencyEntity addCurrency(String name, String code) {
        validating(name, code);
        try {
            return currencyRepository.save(new CurrencyEntity(StringUtils.capitalize(name), code.toUpperCase()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes currency specified by id from database.
     *
     * @param id the id
     * @throws IdNotFoundInDatabaseException the id not found in database exception
     */
    public void deleteCurrency(Long id) throws IdNotFoundInDatabaseException {
        try {
            this.currencyRepository.deleteById(id);
        } catch (RuntimeException ignored) {
            throw new IdNotFoundInDatabaseException("Currency", id);
        }
    }

    /**
     * Updates currency entity and saves it to database.
     *
     * @param id                 the id
     * @param currencyRequestDto the currency request dto
     * @return the currency entity
     */
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

    /**
     * Validates name and code while creating new currency entity
     * @param name the name
     * @param code the code
     */
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

    /**
     * Validates name and code while updating existing currency entity
     * @param id the id
     * @param name the name
     * @param code the code
     */
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

    /**
     * Checks if name is already taken.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean ifNameTaken(String name) {
        Optional<CurrencyEntity> foundByName = currencyRepository.findCurrencyEntityByName(
                StringUtils.capitalize(name));
        return foundByName.isPresent();
    }

    /**
     * Checks if code is already taken.
     *
     * @param code the code
     * @return the boolean
     */
    public boolean ifCodeTaken(String code) {
        Optional<CurrencyEntity> foundByName = currencyRepository.findCurrencyEntityByCode(code.toUpperCase());
        return foundByName.isPresent();
    }

    /**
     * Checks if name is already taken while updating.
     *
     * @param id   the id
     * @param name the name
     * @return the boolean
     */
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

    /**
     * Checks if code is already taken while updating.
     *
     * @param id   the id
     * @return the boolean
     */
    public boolean ifCodeTakenUpdating(Long id, String code) {
        Optional<CurrencyEntity> currencyEntityByCode = currencyRepository.findCurrencyEntityByCode(code.toUpperCase());
        Optional<CurrencyEntity> currencyEntityById = currencyRepository.findById(id);

        if (currencyEntityById.isEmpty())
            throw new IdNotFoundInDatabaseException("Currency", id);
        if (currencyEntityByCode.isEmpty())
            return false;

        return !currencyEntityByCode.get().getId().equals(currencyEntityById.get().getId());
    }

    /**
     * Gets currency entity specified by currency name.
     * If not found throws IdNotFoundInDatabaseException
     *
     * @param name the name
     * @return the currency
     */
    public CurrencyEntity getCurrencyByCurrencyName(String name) {
        return this.currencyRepository.findCurrencyEntityByName(name)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Currency", 0L));
    }

}
