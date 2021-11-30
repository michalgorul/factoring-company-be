package com.polsl.factoringcompany.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.polsl.factoringcompany.security.ApplicationUserPermission.*;

/**
 * The enum application user role.
 * @author Michal Goral
 * @version 1.0
 */
public enum ApplicationUserRole {
    /**
     * Admin application user role.
     */
    ADMIN(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, INVOICE_READ, INVOICE_WRITE)),
    /**
     * Admin trainee application user role.
     */
    ADMIN_TRAINEE(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, INVOICE_READ, INVOICE_WRITE));

    /**
     * the set of user permissions
     */
    private final Set<ApplicationUserPermission> permissions;

    /**
     * Constructor of ApplicationUserRole enum
     * @param permissions the user permissions
     */
    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Gets permissions.
     *
     * @return the permissions
     */
    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    /**
     * Gets granted authorities.
     *
     * @return the granted authorities
     */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
