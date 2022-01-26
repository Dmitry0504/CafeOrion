package com.orion.cafeorion.domain.dto.category;

import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import com.orion.cafeorion.domain.entity.Subcategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * @author Dmitriy
 * @since 24.01.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDto {
    int id;
    String title;
    List<SubcategoryDto> subcategoryList;
}
