package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.repository.OrderRepository;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.service.OrderService;
import com.orion.cafeorion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Order> findAllByUser(User user) {
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
        
        return saveOrder(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order update(int targetId, Order source) {
        return null;
    }
}
