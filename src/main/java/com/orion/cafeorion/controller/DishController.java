package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.dish.DishCreateDto;
import com.orion.cafeorion.domain.dto.dish.DishDto;
import com.orion.cafeorion.domain.dto.dish.DishUpdateDto;
import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.domain.mapper.DishMapper;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "categories/{id}/subcategories/{subcategory-id}/dishes")
public class DishController {

    private final DishMapper dishMapper;
    private final DishService dishService;
    private final SubcategoryService subcategoryService;

    @GetMapping()
    public List<DishDto> getDishesFromSubcategory(@PathVariable("subcategory-id") int subcategory_id) {
        return dishService.findDishesBySubcategory_Id(subcategory_id)
                .stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{dish-id}")
    public DishDto getDishById(@PathVariable("dish-id") int dish_id) {
        return dishMapper.toDto(dishService.findDishById(dish_id));
    }

    @PostMapping()
    public DishDto addNewDish(@PathVariable("subcategory-id") int subcategory_id
        , @RequestBody DishCreateDto dishCreateDto) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(subcategory_id);
        Dish dish = dishMapper.fromCreateDto(dishCreateDto);
        subcategory.addNewDishToList(dish);
//        subcategoryService.saveSubcategory(subcategory);
        dishService.saveDish(dish);
        return dishMapper.toDto(dish);
    }

    @PatchMapping("/{dish-id}")
    public DishDto updateDish(@PathVariable("dish-id") int dish_id
            , @RequestBody DishUpdateDto dishUpdateDto) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(dishUpdateDto.getSubcategoryId());
        Dish updateSource = dishMapper.fromUpdateDto(dishUpdateDto);
        Dish result = dishService.update(dish_id, subcategory, updateSource);
        return dishMapper.toDto(result);
    }

    @DeleteMapping("/{dish-id}")
    public void deleteDishById(@PathVariable("dish-id") int dish_id){
        dishService.deleteDishById(dish_id);
    }


}
