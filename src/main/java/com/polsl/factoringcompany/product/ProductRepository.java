package com.polsl.factoringcompany.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Product repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    /**
     * Finds product entity by name.
     *
     * @param name the name
     * @return the product optional
     */
    Optional<ProductEntity> findProductEntityByName(String name);
}
