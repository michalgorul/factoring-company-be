package com.polsl.factoringcompany.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface customer repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * Finds all customer entities specified by user id in list.
     *
     * @param id the id
     * @return the list of customers
     */
    List<CustomerEntity> findAllByUserId(Integer id);

    /**
     * Finds customer entity specified by phone number.
     *
     * @param phone the phone number
     * @return the optional customer entity
     */
    Optional<CustomerEntity> findByPhone(String phone);
}
