package com.orion.cafeorion.service;

import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Subcategory;

import java.util.List;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
public interface DishService {

    List<Dish> findDishesBySubcategoryId(int subcategoryId);

    Dish findDishById(int dishId);

    void saveDish(Dish dish);

    Dish createDish(int subcategoryId, Dish dish);

    void deleteDishById(int dishId);

    Dish update(int targetId, Subcategory subcategory, Dish source);
}
