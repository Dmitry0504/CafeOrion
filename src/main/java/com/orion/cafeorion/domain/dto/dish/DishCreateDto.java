package com.orion.cafeorion.domain.dto.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "Dish createDto", description = "Dish dto for creating")
public class DishCreateDto {
    @Schema(description = "Dish title", example = "Mozzarella")
    String title;
    @Schema(description = "Dish price in cents", example = "120")
    Integer price;
}
