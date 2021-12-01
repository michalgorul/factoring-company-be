package com.polsl.factoringcompany.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * The interface Confirmation token repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, Long> {

    /**
     * Find by token entity by token string.
     *
     * @param token the token
     * @return the token entity optional
     */
    Optional<ConfirmationTokenEntity> findByToken(String token);

    /**
     * Update confirmed at.
     *
     * @param token       the token
     * @param confirmedAt the date of token confirmation
     * @return the int
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationTokenEntity c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          Timestamp confirmedAt);
}
