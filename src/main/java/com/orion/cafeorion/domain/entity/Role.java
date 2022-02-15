package com.orion.cafeorion.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_WAITER,
    ROLE_COOK,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
