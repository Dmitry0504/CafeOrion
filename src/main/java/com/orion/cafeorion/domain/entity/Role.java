package com.orion.cafeorion.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    WAITER,
    COOK,
    ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
