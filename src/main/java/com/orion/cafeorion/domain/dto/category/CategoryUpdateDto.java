package com.orion.cafeorion.domain.dto.category;

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
public class CategoryUpdateDto {
    String title;
}
