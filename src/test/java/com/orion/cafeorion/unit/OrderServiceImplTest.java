package com.orion.cafeorion.unit;

import com.orion.cafeorion.repository.OrderRepository;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.service.implementation.OrderServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Dmitriy
 * @since 20.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private DishService dishService;
}
