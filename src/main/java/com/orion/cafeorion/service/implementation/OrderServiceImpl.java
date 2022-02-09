package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.repository.OrderRepository;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.service.OrderService;
import com.orion.cafeorion.service.UserService;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DishService dishService;
    private final UserService userService;

    @Override
    public Order findOrderById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order", id));
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        User user = userService.loadUserByUsername(username);
        return orderRepository.findAllByUser(user);
    }

    @Override
    public List<Order> findAllByStatus(Order.Status status) {
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order createOrder(int dishId, Order order) {
        Dish dish = dishService.findDishById(dishId);
        User user = userService.loadUserByUsername("Hook");
        order.setDish(dish);
        order.setUser(user);
        order.setStatus(Order.Status.CREATED);
        order.setOrderTime(new Timestamp(new Date().getTime()));
        return saveOrder(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order update(int targetId, Order source) {
        Order targetOrder = findOrderById(targetId);
        targetOrder.setStatus(source.getStatus());
        return saveOrder(targetOrder);
    }
}
