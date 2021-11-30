package com.polsl.factoringcompany.security;

import lombok.Getter;

/**
 * The enum Application of user permissions.
 * @author Michal Goral
 * @version 1.0
 */
@Getter
public enum ApplicationUserPermission {
    /**
     * Invoice read application user permission.
     */
    INVOICE_READ("invoice:read"),
    /**
     * Invoice write application user permission.
     */
    INVOICE_WRITE("invoice:write"),
    /**
     * Customer read application user permission.
     */
    CUSTOMER_READ("customer:read"),
    /**
     * Customer write application user permission.
     */
    CUSTOMER_WRITE("customer:write");

    /**
     * user permission
     */
    private final String permission;

    /**
     * Constructor of ApplicationUserPermission enum
     * @param permission the permission
     */
    ApplicationUserPermission(String permission){
        this.permission = permission;
    }
}
