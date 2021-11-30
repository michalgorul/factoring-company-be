package com.polsl.factoringcompany.security.auth;

import java.util.Optional;

/**
 * The interface Application user dao.
 * @author Michal Goral
 * @version 1.0
 */
public interface ApplicationUserDao {

    /**
     * Selects application user by username.
     *
     * @param username the username
     * @return the ApplicationUser optional
     */
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
