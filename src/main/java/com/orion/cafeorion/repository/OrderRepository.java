package com.orion.cafeorion.repository;

import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user);

    List<Order> findAllByStatus(Order.Status status);
}
