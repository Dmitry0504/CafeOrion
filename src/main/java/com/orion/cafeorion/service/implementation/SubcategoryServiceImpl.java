package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.repository.SubcategoryRepository;
import com.orion.cafeorion.service.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */

@Service
@AllArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    @Override
    @Transactional
    public List<Subcategory> findAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    @Transactional
    public Subcategory findSubcategoryById(int id) {
        return subcategoryRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void saveSubcategory(Subcategory subcategory) {
        subcategoryRepository.save(subcategory);
    }

    @Override
    @Transactional
    public void deleteSubcategoryById(int id) {
        subcategoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Subcategory> findSubcategoryByCategory_Id(int category_id) {
        return subcategoryRepository.findSubcategoryByCategory_Id(category_id);
    }

    @Override
    @Transactional
    public Subcategory update(int targetId, Category category, Subcategory source) {
        Subcategory target = findSubcategoryById(targetId);
        target.setTitle(source.getTitle());
        target.setCategory(category);
        this.saveSubcategory(target);
        return target;
    }
}
