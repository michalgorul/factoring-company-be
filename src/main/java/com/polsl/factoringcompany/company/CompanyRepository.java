package com.polsl.factoringcompany.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface company repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    /**
     * Finds company entity specified by nip.
     *
     * @param nip the nip number
     * @return the CompanyEntity optional
     */
    Optional<CompanyEntity> findCompanyEntityByNip(String nip);

    /**
     * Finds company entity specified by regon.
     *
     * @param regon the regon number
     * @return the CompanyEntity optional
     */
    Optional<CompanyEntity> findCompanyEntityByRegon(String regon);
}
