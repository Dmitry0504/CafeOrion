package com.orion.cafeorion.domain.dto.subcategory;

import com.orion.cafeorion.domain.dto.category.CategoryCreateDto;
import com.orion.cafeorion.domain.dto.category.CategoryIdDto;
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
public class SubcategoryUpdateDto {
    String title;
    int categoryId;
}
