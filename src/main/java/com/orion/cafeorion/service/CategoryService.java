package com.orion.cafeorion.service;

import com.orion.cafeorion.domain.entity.Category;

import java.util.List;

/**
 * @author Dmitriy
 * @since 20.01.2022
 */
public interface CategoryService {

    List<Category> findAllCategories();

    Category findCategoryById(int id);

    void saveCategory(Category category);

    Category update(int id, Category category);

    void deleteCategoryById(int id);

}
