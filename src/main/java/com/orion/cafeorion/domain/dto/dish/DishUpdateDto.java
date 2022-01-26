package com.orion.cafeorion.domain.dto.dish;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DishUpdateDto {
    String title;
    Integer price;
    int subcategoryId;
}
