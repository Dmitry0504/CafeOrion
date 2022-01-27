package com.orion.cafeorion.domain.dto.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Schema(name = "Dish updateDto", description = "Dish dto for change")
public class DishUpdateDto {
    @NotBlank(message = "Title cannot be empty!")
    @Size(min = 2, max = 45, message = "Title must be between 2 and 45 characters")
    @Schema(description = "Dish title", example = "Mozzarella")
    String title;
    @PositiveOrZero(message = "Price must be positive or zero")
    @Schema(description = "Dish price in cents", example = "120")
    Integer price;
    @PositiveOrZero(message = "Subcategory id must be positive or zero")
    @Schema(description = "The subcategory id to which the dish belongs", example = "5")
    int subcategoryId;
}
