package com.orion.cafeorion.unit;

import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.repository.SubcategoryRepository;
import com.orion.cafeorion.service.CategoryService;
import com.orion.cafeorion.service.implementation.SubcategoryServiceImpl;
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
 * @since 20.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class SubcategoryServiceImplTest {

    @InjectMocks
    private SubcategoryServiceImpl subcategoryService;
    @Mock
    private SubcategoryRepository subcategoryRepository;
    @Mock
    private CategoryService categoryService;

    @Test
    public void testFindAllSubcategories() {
        List<Subcategory> list = new ArrayList<>();
        when(subcategoryRepository.findAll()).thenReturn(list);
        assertEquals(list, subcategoryService.findAllSubcategories());
    }

    @Test
    public void testSubcategoryNotFoundById() {
        int id = 1;
        when(subcategoryRepository.findById(id)).thenReturn(Optional.empty());
        try {
            subcategoryService.findSubcategoryById(1);
        } catch (RuntimeException e) {
            assertEquals(e.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void testFoundSubcategoryById() {
        int id = 1;
        Subcategory testSubcategory = new Subcategory();
        testSubcategory.setTitle("Test title");
        when(subcategoryRepository.findById(id)).thenReturn(Optional.of(testSubcategory));
        Subcategory subcategory = subcategoryRepository.findById(id).get();
        assertEquals(subcategory.getTitle(), testSubcategory.getTitle());
    }

    @Test
    public void testCreateSubcategory() {
        int id = 1;
        Subcategory subcategory = new Subcategory();
        subcategory.setTitle("Test title");
        when(categoryService.findCategoryById(id)).thenReturn(new Category());
        assertEquals(subcategory.getTitle(),
                subcategoryService.create(id, subcategory).getTitle());
    }

    @Test
    public void testDeleteSubcategory() {
        int id = 1;
        subcategoryService.deleteSubcategoryById(id);
        verify(subcategoryRepository, times(1)).deleteById(id);
    }

}
