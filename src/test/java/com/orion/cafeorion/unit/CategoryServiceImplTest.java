package com.orion.cafeorion.unit;

import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.repository.CategoryRepository;
import com.orion.cafeorion.service.implementation.CategoryServiceImpl;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dmitriy
 * @since 17.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testFindAllCategories() {
        List<Category> list = new ArrayList<>();
        Mockito.when(categoryRepository.findAll()).thenReturn(list);
        Assertions.assertEquals(list, categoryServiceImpl.findAllCategories());
    }

    @Test
    public void testCategoryNotFoundById() {
        int id = 1;
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        try {
            categoryServiceImpl.findCategoryById(id);
        } catch (RuntimeException e) {
            Assertions.assertEquals(e.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void testFoundCategoryById() {
        int id = 1;
        Category testCategory = new Category();
        testCategory.setTitle("Test title");
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(testCategory));
        Category category = categoryRepository.findById(id).get();
        Assertions.assertEquals(category.getTitle(), testCategory.getTitle());
    }

    @Test
    public void testSaveCategory() {
        Category testCategory = new Category();
        testCategory.setTitle("Test title");
        categoryServiceImpl.saveCategory(testCategory);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(testCategory);
    }

    @Test
    public void testUpdateCategory() {
        int id = 1;
        Category testTargetCategory = new Category();
        testTargetCategory.setTitle("Test title");
        Category testSourceCategory = new Category();
        testSourceCategory.setTitle("New title");
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(testTargetCategory));
        Category targetCategory = categoryRepository.findById(id).get();
        targetCategory.setTitle(testSourceCategory.getTitle());
        Assertions.assertEquals(targetCategory.getTitle(), testSourceCategory.getTitle());
    }

    @Test
    public void testDeleteCategory() {
        int id = 1;
        categoryServiceImpl.deleteCategoryById(id);
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(id);
    }


}
