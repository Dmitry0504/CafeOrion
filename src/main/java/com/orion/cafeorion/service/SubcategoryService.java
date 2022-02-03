package com.orion.cafeorion.service;


import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.entity.Subcategory;

import java.util.List;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */
public interface SubcategoryService {

    List<Subcategory> findAllSubcategories();

    Subcategory findSubcategoryById(int id);

    void saveSubcategory(Subcategory subcategory);

    Subcategory create(int categoryId, Subcategory subcategory);

    void deleteSubcategoryById(int id);

    List<Subcategory> findSubcategoryByCategoryId(int categoryId);

    Subcategory update(int targetId, Category category, Subcategory source);

}
