package com.orion.cafeorion.domain.dto.subcategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
    @NotBlank(message = "Title cannot be empty!")
    @Size(min = 2, max = 45, message = "Title must be between 2 and 45 characters")
    @Schema(description = "Subcategory title", example = "Pizza")
    String title;
    @PositiveOrZero(message = "Category id must be positive or zero")
    @Schema(description = "Category id", example = "1")
    int categoryId;
}
