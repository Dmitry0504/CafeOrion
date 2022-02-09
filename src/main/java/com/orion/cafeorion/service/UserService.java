package com.orion.cafeorion.service;

import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.entity.User;

import java.util.List;

/**
 * @author Dmitriy
 * @since 03.02.2022
 */
public interface UserService {

    List<User> findAllUsers();

    User loadUserByUsername(String username);

    User createUser(User user);

    User updateUser(String targetUsername, User source);

    void deleteUserByUsername(String username);

    List<Order> getOrdersByUser(String username);

}
