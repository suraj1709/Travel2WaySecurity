package com.travel2way.security.role;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum  ApplicationUserRole {
    ADMIN(Sets.newHashSet(ApplicationUserPermission.ADMIN_READ,ApplicationUserPermission.ADMIN_WRITE)),
    VISITOR(Sets.newHashSet(ApplicationUserPermission.VISITOR_READ));

    private  final Set<ApplicationUserPermission> applicationUserPermissions;


    ApplicationUserRole(Set<ApplicationUserPermission> applicationUserPermissions)
    {
        this.applicationUserPermissions = applicationUserPermissions;
    }

    public Set<ApplicationUserPermission> getApplicationUserPermissions()
    {
        return applicationUserPermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities()
    {
        Set<SimpleGrantedAuthority> authority=getApplicationUserPermissions()
                .stream()
                .map(e->new SimpleGrantedAuthority(e.getPermission())).collect(Collectors.toSet());
        authority.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authority;
    }
}
