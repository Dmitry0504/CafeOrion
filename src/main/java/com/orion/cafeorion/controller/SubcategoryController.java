package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.subcategory.SubcategoryCreateDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryUpdateDto;
import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.domain.mapper.SubcategoryMapper;
import com.orion.cafeorion.service.CategoryService;
import com.orion.cafeorion.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "categories/{id}/subcategories")
public class SubcategoryController {

    private final SubcategoryMapper subcategoryMapper;
    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    @GetMapping("")
    public List<SubcategoryDto> getSubcategoriesFromCategory(@PathVariable int id) {
        return subcategoryService.findSubcategoryByCategory_Id(id)
                .stream()
                .map(subcategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{subcategory-id}")
    public SubcategoryDto getSubcategoryById(@PathVariable("subcategory-id") int subcategory_id) {
        return subcategoryMapper.toDto(subcategoryService.findSubcategoryById(subcategory_id));
    }

    @PostMapping()
    public SubcategoryDto addNewSubcategory(@PathVariable int id
            , @RequestBody SubcategoryCreateDto subcategoryCreateDto) {
        Category category = categoryService.findCategoryById(id);
        Subcategory subcategory = subcategoryMapper.fromCreateDto(subcategoryCreateDto);
        category.addNewSubcategoryToList(subcategory);
        categoryService.saveCategory(category);
        return subcategoryMapper.toDto(subcategory);
    }

    @PatchMapping("/{subcategory-id}")
    public SubcategoryDto updateSubcategory(@PathVariable("subcategory-id") int subcategory_id
            , @RequestBody SubcategoryUpdateDto subcategoryUpdateDto) {

        Subcategory subcategoryUpdate = subcategoryMapper.fromUpdateDto(subcategoryUpdateDto);
        int categoryId = subcategoryUpdateDto.getCategoryId();
        Category category = categoryService.findCategoryById(categoryId);

        return Optional.of(subcategoryMapper.toDto(subcategoryService.update(subcategory_id, category, subcategoryUpdate))).orElseThrow();
    }

    @DeleteMapping("/{subcategory-id}")
    public String deleteSubcategoryById(@PathVariable("subcategory-id") int subcategory_id) {
        subcategoryService.deleteSubcategoryById(subcategory_id);
        return "Subcategory with id " + subcategory_id + " was deleted!";
    }

}
