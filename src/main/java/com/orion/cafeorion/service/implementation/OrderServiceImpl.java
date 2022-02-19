package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.repository.OrderRepository;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.service.OrderService;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DishService dishService;

    @Secured({ "ROLE_ADMIN", "ROLE_WAITER" })
    @Override
    public Order findOrderById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order", id));
    }

    @Secured({ "ROLE_ADMIN", "ROLE_WAITER", "ROLE_COOK" })
    @Override
    public List<Order> findAllByStatus(Order.Status status) {
        return orderRepository.findAllByStatus(status);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_WAITER", "ROLE_COOK" })
    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @Override
    @Transactional
    public Order createOrder(int dishId, Order order) {
        Dish dish = dishService.findDishById(dishId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        order.setDish(dish);
        order.setUser(user);
        order.setStatus(Order.Status.CREATED);
        order.setOrderTime(new Timestamp(new Date().getTime()));
        return saveOrder(order);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    @Transactional
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_WAITER", "ROLE_COOK" })
    @Override
    @Transactional
    public Order update(int targetId, Order source) {
        Order targetOrder = findOrderById(targetId);
        targetOrder.setStatus(source.getStatus());
        return saveOrder(targetOrder);
    }
}
