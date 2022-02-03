package com.orion.cafeorion.domain.dto.dish;

import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "Dish Dto", description = "Dish used dto for presentation")
public class DishDto {
    @Schema(description = "Dish id", example = "1")
    int id;
    @Schema(description = "Dish title", example = "Mozzarella")
    String title;
    @Schema(description = "Dish price in cents", example = "120")
    Integer price;
    @Schema(description = "The subcategory to which the dish belongs")
    SubcategoryDto subcategoryDto;
}
