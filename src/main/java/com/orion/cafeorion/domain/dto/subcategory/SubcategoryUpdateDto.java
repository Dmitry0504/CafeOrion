package com.orion.cafeorion.domain.dto.subcategory;

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
