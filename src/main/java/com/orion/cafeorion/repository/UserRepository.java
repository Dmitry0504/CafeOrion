package com.orion.cafeorion.repository;

import com.orion.cafeorion.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Dmitriy
 * @since 03.02.2022
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}
