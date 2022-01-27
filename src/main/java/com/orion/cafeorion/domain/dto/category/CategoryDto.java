package com.orion.cafeorion.domain.dto.category;

import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import com.orion.cafeorion.domain.entity.Subcategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * @author Dmitriy
 * @since 24.01.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "Category Dto", description = "Full category info")
public class CategoryDto {
    @Schema(description = "Category id", example = "1")
    int id;
    @Schema(description = "Category title", example = "Food")
    String title;
    @Schema(description = "Subcategory list from category")
    List<SubcategoryDto> subcategoryList;
}
