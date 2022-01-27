package com.orion.cafeorion.domain.dto.subcategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Dmitriy
 * @since 25.01.2022
 */
@Value
@Builder
@Getter
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "Subcategory updateDto", description = "Use for update subcategory")
public class SubcategoryUpdateDto {
    @Schema(description = "Subcategory title", example = "Pizza")
    String title;
    @Schema(description = "Category id", example = "1")
    int categoryId;
}
