package com.polsl.factoringcompany.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAllByUserId(Integer id);

    Optional<CustomerEntity> findByPhone(String phone);
}
