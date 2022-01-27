package com.orion.cafeorion.domain.dto.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

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
    @Schema(description = "Dish title", example = "Mozzarella")
    String title;
    @Schema(description = "Dish price in cents", example = "120")
    Integer price;
    @Schema(description = "The subcategory id to which the dish belongs", example = "5")
    int subcategoryId;
}
