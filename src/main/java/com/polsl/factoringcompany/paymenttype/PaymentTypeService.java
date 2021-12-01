package com.polsl.factoringcompany.paymenttype;

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
 * The type Payment type service. Used to connect controller with Data access object
 *
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class PaymentTypeService {

    /**
     * the payment type repository bean
     */
    private final PaymentTypeRepository paymentTypeRepository;

    /**
     * Gets all payment types from database.
     *
     * @return the payment types
     */
    public List<PaymentTypeEntity> getPaymentTypes() {
        return paymentTypeRepository.findAll();
    }

    /**
     * Gets payment type specified by id. If payment type is not found it throws IdNotFoundInDatabaseException
     *
     * @param id the id
     * @return the payment type
     * @throws IdNotFoundInDatabaseException the id not found in database exception
     */
    public PaymentTypeEntity getPaymentType(Long id) throws IdNotFoundInDatabaseException {
        return this.paymentTypeRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Payment type: ", id));
    }

    /**
     * Creates payment type  entity and saves it to database.
     *
     * @param paymentTypeName the payment type name
     * @return the payment type entity
     */
    public PaymentTypeEntity addPaymentType(String paymentTypeName) {

        nameValidation(paymentTypeName);

        try {
            return this.paymentTypeRepository.save(new PaymentTypeEntity(StringUtils.capitalize(paymentTypeName)));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes product specified by id.
     *
     * @param id the id
     * @throws IdNotFoundInDatabaseException the id not found in database exception
     */
    public void deletePaymentType(Long id) throws IdNotFoundInDatabaseException {
        try {
            this.paymentTypeRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Payment type", id);
        }
    }

    /**
     * Updates payment type entity and saves it to database.
     *
     * @param id              the id
     * @param paymentTypeName the payment type name
     * @return the payment type entity
     */
    @Transactional
    public PaymentTypeEntity updatePaymentType(Long id, String paymentTypeName) {

        Optional<PaymentTypeEntity> paymentTypeEntity = paymentTypeRepository.findById(id);

        if (paymentTypeEntity.isEmpty())
            throw new IdNotFoundInDatabaseException("Payment type", id);

        nameValidation(paymentTypeName);

        try {
            paymentTypeEntity.get().setPaymentTypeName(StringUtils.capitalize(paymentTypeName));
            return this.paymentTypeRepository.save(paymentTypeEntity.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Validates if payment type has proper name
     * @param paymentTypeName the name
     */
    private void nameValidation(String paymentTypeName) {
        if (StringValidator.stringWithSpacesImproper(paymentTypeName, 25))
            throw new ValueImproperException(paymentTypeName);

        if (ifNameTaken(paymentTypeName)) {
            throw new NotUniqueException("Payment type", "name", paymentTypeName);
        }
    }

    /**
     * Checks if name taken.
     *
     * @param paymentTypeName the name
     * @return true if name is already in use
     */
    public boolean ifNameTaken(String paymentTypeName) {
        Optional<PaymentTypeEntity> paymentTypeEntity = paymentTypeRepository.findPaymentTypeEntityByPaymentTypeName(
                StringUtils.capitalize(paymentTypeName));
        return paymentTypeEntity.isPresent();
    }

    /**
     * Gets payment type entity specified by name. If it is not found it throws IdNotFoundInDatabaseException
     *
     * @param name the name
     * @return the product entity
     */
    public PaymentTypeEntity getPaymentTypeEntityByName(String name) {
        return this.paymentTypeRepository.findPaymentTypeEntityByPaymentTypeName(name)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Payment type", 0L));
    }
}
