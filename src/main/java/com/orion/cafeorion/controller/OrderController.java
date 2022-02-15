package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.order.OrderCreateDto;
import com.orion.cafeorion.domain.dto.order.OrderDto;
import com.orion.cafeorion.domain.dto.order.OrderUpdateDto;
import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.mapper.OrderMapper;
import com.orion.cafeorion.service.OrderService;
import com.orion.cafeorion.util.exсeption.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
@Tag(name = "Order controller", description = "designed to work with orders")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Order not found")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    /**
     * Return order on JSON format
     *
     * @param orderId is a dish id
     * @return orderDto on JSON format
     */
    @Operation(description = "Find order by id")
    @ApiResponse(responseCode = "200", description = "Order was found")
    @ApiResponse(responseCode = "500", description = "Order not found")
    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable int orderId) {
        return orderMapper.toDto(orderService.findOrderById(orderId));
    }

    /**
     * @return Page<OrderDto> on JSON format
     */
    @Operation(description = "Find all orders")
    @ApiResponse(responseCode = "200", description = "Orders were found")
    @ApiResponse(responseCode = "500", description = "Orders not found")
    @GetMapping("")
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
    public OrderDto createOrder(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        Order order = orderMapper.fromCreateDto(orderCreateDto);
        return orderMapper.toDto(orderService.createOrder(orderCreateDto.getDishId(), order));
    }

    /**
     * Change order by id
     *
     * @param orderId is order id to update
     * @param orderUpdateDto is a order on JSON format
     * @return dishDto on JSON format
     */
    @Operation(description = "Update order")
    @ApiResponse(responseCode = "200", description = "Order was updated")
    @PatchMapping("/{orderId}")
    public OrderDto updateOrder(@PathVariable int orderId,
                                @Valid @RequestBody OrderUpdateDto orderUpdateDto) {
        Order source = orderMapper.fromUpdateDto(orderUpdateDto);
        Order result = orderService.update(orderId, source);
        return orderMapper.toDto(result);
    }

    /**
     * Delete order by id
     *
     * @param orderId is a order id that need to delete
     */
    @Operation(description = "Delete order by id")
    @ApiResponse(responseCode = "200", description = "Order was deleted")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Response> deleteOrderById(@PathVariable int orderId){
        orderService.deleteOrderById(orderId);
        Response response = new Response("Order with id " + orderId + " was deleted!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
