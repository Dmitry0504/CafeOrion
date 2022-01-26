package com.orion.cafeorion.domain.dto.subcategory;

import com.orion.cafeorion.domain.dto.category.CategoryCreateDto;
import com.orion.cafeorion.domain.dto.category.CategoryIdDto;
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
public class SubcategoryCreateDto {
    String title;
}
