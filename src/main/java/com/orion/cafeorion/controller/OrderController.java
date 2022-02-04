package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.order.OrderCreateDto;
import com.orion.cafeorion.domain.dto.order.OrderDto;
import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.mapper.OrderMapper;
import com.orion.cafeorion.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/orders")
@Tag(name = "Order controller", description = "designed to work with orders")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Order not found")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    /**
     * @return Page<OrderDto> on JSON format
     */
    @Operation(description = "Find all orders")
    @ApiResponse(responseCode = "200", description = "Order were found")
    @ApiResponse(responseCode = "500", description = "Order not found")
    @GetMapping()
    public Page<OrderDto> getAllOrders() {
        List<OrderDto> orderDtoList = orderService.findAllOrders()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(orderDtoList);
    }

    /**
     * Create order
     *
     * @param orderCreateDto is an order on JSON format
     * @return order on JSON format
     */
    @Operation(description = "Create order")
    @ApiResponse(responseCode = "200", description = "Order was created")
    @PostMapping("")
    public OrderDto createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        Order order = orderMapper.fromCreateDto(orderCreateDto);
        return orderMapper.toDto(orderService.createOrder(orderCreateDto.getDishId(), order));
    }


}
