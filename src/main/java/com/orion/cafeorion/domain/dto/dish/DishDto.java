package com.orion.cafeorion.domain.dto.dish;

import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
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
public class DishDto {
    int id;
    String title;
    Integer price;
    SubcategoryDto subcategoryDto;
}
