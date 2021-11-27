package com.polsl.factoringcompany.security;

import lombok.Getter;

@Getter
public enum ApplicationUserPermission {
    INVOICE_READ("invoice:read"),
    INVOICE_WRITE("invoice:write"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write");

    private final String permission;

    ApplicationUserPermission(String permission){
        this.permission = permission;
    }
}
