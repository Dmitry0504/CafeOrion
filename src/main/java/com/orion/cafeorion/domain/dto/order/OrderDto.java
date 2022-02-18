package com.orion.cafeorion.domain.dto.order;

import com.orion.cafeorion.domain.dto.dish.DishDto;
import com.orion.cafeorion.domain.dto.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.sql.Timestamp;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "OrderDto", description = "Used for presentation")
public class OrderDto {
    @Schema(description = "Order id", example = "1")
    int id;
    DishDto dishDto;
    UserDto userDto;
    @Schema(description = "Order time", example = "2022-02-17T15:12:28.992Z")
    Timestamp orderTime;
    @Schema(description = "Order status", example = "CREATED")
    String status;
}
