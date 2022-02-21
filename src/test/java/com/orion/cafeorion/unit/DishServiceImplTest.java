package com.orion.cafeorion.unit;

import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.repository.DishRepository;
import com.orion.cafeorion.service.SubcategoryService;
import com.orion.cafeorion.service.implementation.DishServiceImpl;
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
public class DishServiceImplTest {

    @InjectMocks
    private DishServiceImpl dishService;
    @Mock
    private DishRepository dishRepository;
    @Mock
    private SubcategoryService subcategoryService;

    @Test
    public void testFindDishesBySubcategoryIdIsReturnedValueList() {
        List<Dish> list = new ArrayList<>();
        when(dishRepository.findDishesBySubcategoryId(1)).thenReturn(list);
        assertEquals(list, dishService.findDishesBySubcategoryId(1));
    }

    @Test
    public void testDishNotFoundById() {
        int id = 1;
        when(dishRepository.findById(id)).thenReturn(Optional.empty());
        try {
            dishService.findDishById(id);
        } catch (RuntimeException e) {
            assertEquals(e.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void testCreateDishReturnedValue() {
        Subcategory subcategory = new Subcategory();
        subcategory.setTitle("Test subcategory");
        Dish dish = new Dish();
        dish.setTitle("Test dish");
        when(subcategoryService.findSubcategoryById(1)).thenReturn(subcategory);
        assertEquals(dish, dishService.createDish(1, dish));
    }

    @Test
    public void testCreateDishAddedDishToDishList() {
        Subcategory subcategory = new Subcategory();
        subcategory.setTitle("Test subcategory");
        Dish dish = new Dish();
        dish.setTitle("Test dish");
        when(subcategoryService.findSubcategoryById(1)).thenReturn(subcategory);
        dishService.createDish(1, dish);
        assertEquals(1, subcategory.getDishList().size());
    }

    @Test
    public void testDeleteDishByIdTimes() {
        int id = 1;
        dishService.deleteDishById(id);
        verify(dishRepository, times(1)).deleteById(id);
    }
}
