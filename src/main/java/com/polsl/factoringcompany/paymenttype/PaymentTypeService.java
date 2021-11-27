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

@Service
@AllArgsConstructor
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;

    public List<PaymentTypeEntity> getPaymentTypes() {
        return paymentTypeRepository.findAll();
    }

    public PaymentTypeEntity getPaymentType(Long id) throws IdNotFoundInDatabaseException {
        return this.paymentTypeRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Payment type: ", id));
    }

    public PaymentTypeEntity addPaymentType(String paymentTypeName) {

        nameValidation(paymentTypeName);

        try {
            return this.paymentTypeRepository.save(new PaymentTypeEntity(StringUtils.capitalize(paymentTypeName)));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePaymentType(Long id) throws IdNotFoundInDatabaseException {
        try {
            this.paymentTypeRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Payment type", id);
        }
    }

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

    private void nameValidation(String paymentTypeName) {
        if (StringValidator.stringWithSpacesImproper(paymentTypeName, 25))
            throw new ValueImproperException(paymentTypeName);

        if (ifNameTaken(paymentTypeName)) {
            throw new NotUniqueException("Payment type", "name", paymentTypeName);
        }
    }

    public boolean ifNameTaken(String paymentTypeName) {
        Optional<PaymentTypeEntity> paymentTypeEntity = paymentTypeRepository.findPaymentTypeEntityByPaymentTypeName(
                StringUtils.capitalize(paymentTypeName));
        return paymentTypeEntity.isPresent();
    }

    public PaymentTypeEntity getPaymentTypeEntityByName(String name) {
        return this.paymentTypeRepository.findPaymentTypeEntityByPaymentTypeName(name)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Payment type", 0L));
    }
}
