package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.category.CategoryCreateDto;
import com.orion.cafeorion.domain.dto.category.CategoryDto;
import com.orion.cafeorion.domain.dto.category.CategoryUpdateDto;
import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.mapper.CategoryMapper;
import com.orion.cafeorion.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 20.01.2022
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping()
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAllCategories()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable int id) {
        return categoryMapper.toDto(categoryService.findCategoryById(id));
    }

    @PostMapping("")
    public CategoryDto addNewCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        Category category = categoryMapper.fromCreateDto(categoryCreateDto);
        categoryService.saveCategory(category);
        return categoryMapper.toDto(category);
    }

    @PatchMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable int id
            , @RequestBody CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryMapper.fromUpdateDto(categoryUpdateDto);

        Category categoryToUpdate = categoryService.findCategoryById(id);
        categoryToUpdate.setTitle(category.getTitle());

        categoryService.saveCategory(categoryToUpdate);

        return categoryMapper.toDto(categoryToUpdate);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return "Category with id " + id + " was deleted!";
    }

}
