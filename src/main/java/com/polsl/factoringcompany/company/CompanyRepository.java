package com.polsl.factoringcompany.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findCompanyEntityByNip(String nip);

    Optional<CompanyEntity> findCompanyEntityByRegon(String regon);
}
