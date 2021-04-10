package com.travel2way.security.role;

public enum ApplicationUserPermission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    VISITOR_READ("visitor:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission()
    {
        return permission;
    }
}
