package com.orion.cafeorion.domain.mapper;

import com.orion.cafeorion.domain.dto.category.CategoryCreateDto;
import com.orion.cafeorion.domain.dto.category.CategoryDto;
import com.orion.cafeorion.domain.dto.category.CategoryUpdateDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryCreateDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryUpdateDto;
import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.service.CategoryService;
import org.mapstruct.*;

/**
 * @author Dmitriy
 * @since 25.01.2022
 */
@Mapper(uses = {CategoryMapper.class})
public interface SubcategoryMapper {

    SubcategoryDto toDto(Subcategory source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Subcategory fromCreateDto(SubcategoryCreateDto source);


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    Subcategory fromUpdateDto(SubcategoryUpdateDto source);
//
//    void updateModel(SubcategoryDto subcategoryDto, @MappingTarget Subcategory subcategory);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Subcategory merge(@MappingTarget Subcategory target, Subcategory source);
}
