package com.polsl.factoringcompany.paymenttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentTypeEntity, Long> {

    Optional<PaymentTypeEntity> findPaymentTypeEntityByPaymentTypeName(String name);

}
