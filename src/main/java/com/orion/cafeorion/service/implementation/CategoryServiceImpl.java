package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.repository.CategoryRepository;
import com.orion.cafeorion.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dmitriy
 * @since 20.01.2022
 */

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }
}
