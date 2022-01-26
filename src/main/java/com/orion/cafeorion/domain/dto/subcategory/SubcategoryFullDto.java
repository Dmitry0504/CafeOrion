package com.orion.cafeorion.domain.dto.subcategory;

import com.orion.cafeorion.domain.dto.dish.DishDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SubcategoryFullDto {
    int id;
    String title;
    List<DishDto> dishDtoList;
}
