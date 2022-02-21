package com.orion.cafeorion.unit;

import com.orion.cafeorion.repository.OrderRepository;
import com.orion.cafeorion.service.implementation.OrderServiceImpl;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void testOrderNotFoundById() {
        int id = 1;
        when(orderRepository.findById(id)).thenReturn(Optional.empty());
        try {
            orderRepository.findById(id);
        } catch (RuntimeException e) {
            assertEquals(e.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void testDeleteOrderByIdTimes() {
        int id = 1;
        orderService.deleteOrderById(id);
        verify(orderRepository, times(1)).deleteById(id);
    }

}
