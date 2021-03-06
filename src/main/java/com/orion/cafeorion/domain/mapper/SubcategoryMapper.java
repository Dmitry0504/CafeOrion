package com.orion.cafeorion.domain.mapper;

import com.orion.cafeorion.domain.dto.subcategory.SubcategoryCreateDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryFullDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryUpdateDto;
import com.orion.cafeorion.domain.entity.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Dmitriy
 * @since 25.01.2022
 */
@Mapper(uses = {CategoryMapper.class, DishMapper.class})
public interface SubcategoryMapper {

    SubcategoryDto toDto(Subcategory source);

    @Mapping(source = "dishList", target = "dishDtoList")
    SubcategoryFullDto toFullDto(Subcategory source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "dishList", ignore = true)
    Subcategory fromCreateDto(SubcategoryCreateDto source);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dishList", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    Subcategory fromUpdateDto(SubcategoryUpdateDto source);

}
