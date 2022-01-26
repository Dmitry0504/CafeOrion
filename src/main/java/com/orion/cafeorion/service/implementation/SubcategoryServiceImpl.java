package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.repository.SubcategoryRepository;
import com.orion.cafeorion.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public List<Subcategory> findAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    public Subcategory findSubcategoryById(int id) {
        return subcategoryRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveSubcategory(Subcategory subcategory) {
        subcategoryRepository.save(subcategory);
    }

    @Override
    public void deleteSubcategoryById(int id) {
        subcategoryRepository.deleteById(id);
    }

    @Override
    public List<Subcategory> findSubcategoryByCategory_Id(int category_id) {
        return subcategoryRepository.findSubcategoryByCategory_Id(category_id);
    }

    @Override
    public Subcategory update(int targetId, Category category, Subcategory subcategory) {
        Subcategory subcategoryToUpdate = this.findSubcategoryById(targetId);
        subcategoryToUpdate.setTitle(subcategory.getTitle());
        subcategoryToUpdate.setCategory(category);
        this.saveSubcategory(subcategoryToUpdate);
        return subcategoryToUpdate;
    }
}
