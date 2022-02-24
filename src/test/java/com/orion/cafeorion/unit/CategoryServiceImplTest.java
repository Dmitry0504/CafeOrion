package com.orion.cafeorion.unit;

import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.repository.CategoryRepository;
import com.orion.cafeorion.service.implementation.CategoryServiceImpl;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        when(categoryRepository.findAll()).thenReturn(list);
        assertEquals(list, categoryServiceImpl.findAllCategories());
    }

    @Test
    public void testCategoryNotFoundById() {
        int id = 1;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        try {
            categoryServiceImpl.findCategoryById(id);
        } catch (RuntimeException e) {
            assertEquals(e.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void testFoundCategoryById() {
        int id = 1;
        Category testCategory = new Category();
        testCategory.setTitle("Test title");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(testCategory));
        Category category = categoryRepository.findById(id).get();
        assertEquals(category.getTitle(), testCategory.getTitle());
    }

    @Test
    public void testSaveCategory() {
        Category testCategory = new Category();
        testCategory.setTitle("Test title");
        categoryServiceImpl.saveCategory(testCategory);
        verify(categoryRepository, times(1)).save(testCategory);
    }

    @Test
    public void testUpdateCategory() {
        int id = 1;
        Category testTargetCategory = new Category();
        testTargetCategory.setTitle("Test title");
        Category testSourceCategory = new Category();
        testSourceCategory.setTitle("New title");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(testTargetCategory));
        Category targetCategory = categoryRepository.findById(id).get();
        targetCategory.setTitle(testSourceCategory.getTitle());
        assertEquals(targetCategory.getTitle(), testSourceCategory.getTitle());
    }

    @Test
    public void testDeleteCategory() {
        int id = 1;
        categoryServiceImpl.deleteCategoryById(id);
        verify(categoryRepository, times(1)).deleteById(id);
    }


}
