package com.polsl.factoringcompany.paymenttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface PaymentType repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentTypeEntity, Long> {

    /**
     * Find payment type entity by specified name optional.
     *
     * @param name the name
     * @return the payment type optional
     */
    Optional<PaymentTypeEntity> findPaymentTypeEntityByPaymentTypeName(String name);

}
