package com.orion.cafeorion.domain.dto.subcategory;

import com.orion.cafeorion.domain.dto.dish.DishDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "Subcategory fullDto", description = "Subcategory full info")
public class SubcategoryFullDto {
    @Schema(description = "Subcategory id", example = "1")
    int id;
    @Schema(description = "Subcategory title", example = "Pizza")
    String title;
    @Schema(description = "List dishDto")
    List<DishDto> dishDtoList;
}
