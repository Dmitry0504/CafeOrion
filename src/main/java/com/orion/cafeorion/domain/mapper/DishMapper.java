package com.orion.cafeorion.domain.mapper;

import com.orion.cafeorion.domain.dto.dish.DishCreateDto;
import com.orion.cafeorion.domain.dto.dish.DishDto;
import com.orion.cafeorion.domain.dto.dish.DishUpdateDto;
import com.orion.cafeorion.domain.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Mapper(uses = {SubcategoryMapper.class})
public interface DishMapper {

    DishDto toDto(Dish dish);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategory", ignore = true)
    Dish fromCreateDto(DishCreateDto dishCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategory", ignore = true)
    Dish fromUpdateDto(DishUpdateDto dishUpdateDto);
}
