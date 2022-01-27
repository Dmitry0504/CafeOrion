package com.orion.cafeorion.domain.dto.subcategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Dmitriy
 * @since 24.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "Subcategory CreateDto", description = "Use for create subcategory")
public class SubcategoryCreateDto {
    @Schema(description = "Subcategory title", example = "Pizza")
    String title;
}
