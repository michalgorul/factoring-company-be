package com.polsl.factoringcompany.credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

    List<CreditEntity> findAllByUserId(int userId);

    @Query(value = "SELECT max(c.id) FROM credit c " +
            "WHERE FUNCTION('MONTH', c.creationDate) = ?1 AND " +
            "FUNCTION('YEAR', c.creationDate) = ?2")
    Long getCreditNumber(int month, int year);

    Optional<CreditEntity> findByCreditNumber(String creditNumber);

    List<CreditEntity> findAllByStatusEquals(String status);
}
