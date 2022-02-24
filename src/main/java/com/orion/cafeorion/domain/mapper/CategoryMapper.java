package com.orion.cafeorion.domain.mapper;

import com.orion.cafeorion.domain.dto.category.CategoryCreateDto;
import com.orion.cafeorion.domain.dto.category.CategoryDto;
import com.orion.cafeorion.domain.dto.category.CategoryUpdateDto;
import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.service.CategoryService;
import org.mapstruct.*;

/**
 * @author Dmitriy
 * @since 24.01.2022
 */
@Mapper(uses = {CategoryService.class, SubcategoryMapper.class})
public interface CategoryMapper {

    CategoryDto toDto(Category source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "subcategoryList", ignore = true)
    Category map(Integer id);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategoryList", ignore = true)
    Category fromCreateDto(CategoryCreateDto source);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategoryList", ignore = true)
    Category fromUpdateDto(CategoryUpdateDto source);

}