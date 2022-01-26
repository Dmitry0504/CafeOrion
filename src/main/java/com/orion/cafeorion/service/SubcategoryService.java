package com.orion.cafeorion.service;


import com.orion.cafeorion.domain.dto.subcategory.SubcategoryUpdateDto;
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

    void deleteSubcategoryById(int id);

    List<Subcategory> findSubcategoryByCategory_Id(int category_id);

    Subcategory update(int targetId, Category category, Subcategory subcategory);

}