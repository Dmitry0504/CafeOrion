package com.orion.cafeorion.domain.dto.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Title cannot be empty!")
    @Size(min = 2, max = 45, message = "Title must be between 2 and 45 characters")
    @Schema(description = "Dish title", example = "Mozzarella")
    String title;
    @PositiveOrZero(message = "Price must be positive or zero")
    @Schema(description = "Dish price in cents", example = "120")
    Integer price;
}
