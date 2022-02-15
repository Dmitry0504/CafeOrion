package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.repository.DishRepository;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.service.SubcategoryService;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final SubcategoryService subcategoryService;

    @Override
    public List<Dish> findDishesBySubcategoryId(int subcategoryId) {
        return dishRepository.findDishesBySubcategoryId(subcategoryId);
    }

    @Override
    public Dish findDishById(int dishId) {
        return dishRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish", dishId));
    }

    @Override
    public void saveDish(Dish dish) {
        dishRepository.save(dish);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    @Transactional
    public Dish createDish(int subcategoryId, Dish dish) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(subcategoryId);
        subcategory.addNewDishToList(dish);
        saveDish(dish);
        return dish;
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    @Transactional
    public void deleteDishById(int dishId) {
        dishRepository.deleteById(dishId);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    @Transactional
    public Dish update(int targetId, int subcategoryId, Dish source) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(subcategoryId);
        Dish target = findDishById(targetId);
        target.setTitle(source.getTitle());
        target.setPrice(source.getPrice());
        target.setSubcategory(subcategory);
        saveDish(target);
        return target;
    }
}
