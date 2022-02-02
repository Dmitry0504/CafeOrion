package com.orion.cafeorion.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Dmitriy
 * @since 03.02.2022
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "enabled")
    int enabled;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    Role role;

    
    enum Role {
        ROLE_USER,
        ROLE_WAITER,
        ROLE_COOK,
        ROLE_ADMIN
    }
}
