package com.polsl.factoringcompany.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The interface User repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds user by email.
     *
     * @param email the email
     * @return the user optional
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Finds user by username.
     *
     * @param username the username
     * @return the user optional
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * Finds user by username.
     *
     * @param id the id of user to be looked for
     * @return the user optional
     */
    @NotNull
    Optional<UserEntity> findById(@NotNull Long id);


    /**
     * Enables app user.
     *
     * @param id the id of user to be enabled
     */
    @Transactional
    @Modifying
    @Query("UPDATE UserEntity a " +
            "SET a.enabled = TRUE WHERE a.id = ?1")
    void enableAppUser(Long id);
}
