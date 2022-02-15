package com.orion.cafeorion.service;

import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.entity.User;

import java.util.List;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
public interface OrderService {

    Order findOrderById(int id);

    List<Order> findAllByStatus(Order.Status status);

    List<Order> findAllOrders();

    Order saveOrder(Order order);

    Order createOrder(int dishId, Order order);

    void deleteOrderById(int id);

    Order update(int targetId, Order source);
}
