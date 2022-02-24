package com.orion.cafeorion.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @author Dmitriy
 * @since 03.02.2022
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "enabled")
    int enabled;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    Role role;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orderList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled == 1;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
