package com.orion.cafeorion.domain.mapper;

import com.orion.cafeorion.domain.dto.order.OrderCreateDto;
import com.orion.cafeorion.domain.dto.order.OrderDto;
import com.orion.cafeorion.domain.dto.order.OrderUpdateDto;
import com.orion.cafeorion.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Mapper(uses = {UserMapper.class, DishMapper.class})
public interface OrderMapper {

    @Mapping(target = "userDto", source = "user")
    @Mapping(target = "dishDto", source = "dish")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTime", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "dish", ignore = true)
    Order fromCreateDto(OrderCreateDto orderCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTime", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "dish", ignore = true)
    Order fromUpdateDto(OrderUpdateDto orderUpdateDto);
}
