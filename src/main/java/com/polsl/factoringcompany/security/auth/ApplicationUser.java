package com.polsl.factoringcompany.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * The class Application user.
 * <a href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetails.html">See more</a>
 * @author Michal Goral
 * @version 1.0
 */
public class ApplicationUser implements UserDetails {

    /**
     * the set of granted authorities
     */
    private final Set<? extends GrantedAuthority> grantedAuthorities;

    /**
     * the username
     */
    private final String username;

    /**
     * the password
     */
    private final String password;

    /**
     * the information if account is not expired
     */
    private final boolean isAccountNonExpired;

    /**
     * the information if account is not locked
     */
    private final boolean isAccountNonLocked;

    /**
     * the information if credentials is not expired
     */
    private final boolean isCredentialsNonExpired;

    /**
     * the information if account is enabled
     */
    private final boolean isEnabled;

    /**
     * Instantiates a new Application user.
     *
     * @param grantedAuthorities      the granted authorities
     * @param username                the username
     * @param password                the password
     * @param isAccountNonExpired     the information if account is non expired
     * @param isAccountNonLocked      the information if account is non locked
     * @param isCredentialsNonExpired the information if credentials are non expired
     * @param isEnabled               the information if account is enabled
     */
    public ApplicationUser(Set<? extends GrantedAuthority> grantedAuthorities, String username,
                           String password, boolean isAccountNonExpired,
                           boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.grantedAuthorities = grantedAuthorities;
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }


    /**
     * Gets authorities
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    /**
     * Gets password
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets username
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Gets information if account is non expired
     * @return the information if account is non expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * Gets the information if account is non locked
     * @return the information if account is non locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * Gets the information if credentials are non expired
     * @return the information if credentials are non expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * Gets the information if account is enabled
     * @return the information if account is enabled
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
