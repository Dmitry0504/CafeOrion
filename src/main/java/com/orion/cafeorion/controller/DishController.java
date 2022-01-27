package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.dish.DishCreateDto;
import com.orion.cafeorion.domain.dto.dish.DishDto;
import com.orion.cafeorion.domain.dto.dish.DishUpdateDto;
import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.domain.mapper.DishMapper;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.service.SubcategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/categories/{id}/subcategories/{subcategory-id}/dishes")
@Tag(name = "Dish controller", description = "designed to work with dishes")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Dish not found")
public class DishController {

    private final DishMapper dishMapper;
    private final DishService dishService;
    private final SubcategoryService subcategoryService;

    /**
     * Return List<DishDto> from this subcategory on JSON format
     *
     * @param subcategory_id is id subcategory for search dishes
     * @return List<DishDto> from this subcategory on JSON format
     */
    @Operation(description = "Find all dishes from subcategory")
    @ApiResponse(responseCode = "200", description = "Dishes were found")
    @ApiResponse(responseCode = "500", description = "Dishes not found")
    @GetMapping()
    public List<DishDto> getDishesFromSubcategory(@PathVariable("subcategory-id") int subcategory_id) {
        return dishService.findDishesBySubcategory_Id(subcategory_id)
                .stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Return dish on JSON format
     *
     * @param dish_id is a dish id
     * @return dishDto on JSON format
     */
    @Operation(description = "Find dish by id")
    @ApiResponse(responseCode = "200", description = "Dish was found")
    @ApiResponse(responseCode = "500", description = "Dish not found")
    @GetMapping("/{dish-id}")
    public DishDto getDishById(@PathVariable("dish-id") int dish_id) {
        return dishMapper.toDto(dishService.findDishById(dish_id));
    }

    /**
     * Create a new subcategory
     *
     * @param subcategory_id is id subcategory which dish belongs
     * @param dishCreateDto is a dish on JSON format
     * @return dishDto on JSON format
     */
    @Operation(description = "Create dish")
    @ApiResponse(responseCode = "200", description = "Dish was created")
    @PostMapping()
    public DishDto createNewDish(@PathVariable("subcategory-id") int subcategory_id
        , @Valid @RequestBody DishCreateDto dishCreateDto) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(subcategory_id);
        Dish dish = dishMapper.fromCreateDto(dishCreateDto);
        subcategory.addNewDishToList(dish);
        dishService.saveDish(dish);
        return dishMapper.toDto(dish);
    }

    /**
     * Change dish by id
     *
     * @param dish_id is dish id to update
     * @param dishUpdateDto is a dish on JSON format
     * @return subCategoryDto on JSON format
     */
    @Operation(description = "Update dish")
    @ApiResponse(responseCode = "200", description = "Subcategory was updated")
    @PatchMapping("/{dish-id}")
    public DishDto updateDish(@PathVariable("dish-id") int dish_id
            , @Valid @RequestBody DishUpdateDto dishUpdateDto) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(dishUpdateDto.getSubcategoryId());
        Dish updateSource = dishMapper.fromUpdateDto(dishUpdateDto);
        Dish result = dishService.update(dish_id, subcategory, updateSource);
        return dishMapper.toDto(result);
    }

    /**
     * Delete dish by id
     *
     * @param dish_id is a dish id that need to delete
     */
    @Operation(description = "Delete subcategory by id")
    @ApiResponse(responseCode = "200", description = "Subcategory was deleted")
    @DeleteMapping("/{dish-id}")
    public void deleteDishById(@PathVariable("dish-id") int dish_id){
        dishService.deleteDishById(dish_id);
    }


}
