package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.dish.DishCreateDto;
import com.orion.cafeorion.domain.dto.dish.DishDto;
import com.orion.cafeorion.domain.dto.dish.DishUpdateDto;
import com.orion.cafeorion.domain.entity.Dish;
import com.orion.cafeorion.domain.mapper.DishMapper;
import com.orion.cafeorion.service.DishService;
import com.orion.cafeorion.util.exсeption.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/subcategories/{subcategory-id}/dishes")
@Tag(name = "Dish controller", description = "designed to work with dishes")
@ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
@ApiResponse(responseCode = "400", description = "Validation failed", content = @Content)
@ApiResponse(responseCode = "404", description = "Dish not found", content = @Content)
public class DishController {

    private final DishMapper dishMapper;
    private final DishService dishService;

    /**
     * Return Page<DishDto> from this subcategory on JSON format
     *
     * @param subcategoryId is id subcategory for search dishes
     * @return Page<DishDto> from this subcategory on JSON format
     */
    @Operation(description = "Find all dishes from subcategory")
    @ApiResponse(responseCode = "200", description = "Dishes were found")
    @GetMapping()
    public Page<DishDto> getDishesFromSubcategory(@PathVariable("subcategory-id") int subcategoryId) {
        List<DishDto> dishDtoList = dishService.findDishesBySubcategoryId(subcategoryId)
                .stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dishDtoList);
    }

    /**
     * Return dish on JSON format
     *
     * @param dishId is a dish id
     * @return dishDto on JSON format
     */
    @Operation(description = "Find dish by id")
    @ApiResponse(responseCode = "200", description = "Dish was found")
    @GetMapping("/{dish-id}")
    public DishDto getDishById(@PathVariable("dish-id") int dishId) {
        return dishMapper.toDto(dishService.findDishById(dishId));
    }

    /**
     * Create a new dish
     *
     * @param subcategoryId is id subcategory which dish belongs
     * @param dishCreateDto is a dish on JSON format
     * @return dishDto on JSON format
     */
    @Operation(description = "Create dish")
    @ApiResponse(responseCode = "200", description = "Dish was created")
    @PostMapping()
    public DishDto createNewDish(@PathVariable("subcategory-id") int subcategoryId
        , @Valid @RequestBody DishCreateDto dishCreateDto) {
        Dish dish = dishMapper.fromCreateDto(dishCreateDto);
        return dishMapper.toDto(dishService.createDish(subcategoryId, dish));
    }

    /**
     * Change dish by id
     *
     * @param dishId is dish id to update
     * @param dishUpdateDto is a dish on JSON format
     * @return dishDto on JSON format
     */
    @Operation(description = "Update dish")
    @ApiResponse(responseCode = "200", description = "Dish was updated")
    @PatchMapping("/{dish-id}")
    public DishDto updateDish(@PathVariable("dish-id") int dishId,
                              @Valid @RequestBody DishUpdateDto dishUpdateDto) {
        int subcategoryId = dishUpdateDto.getSubcategoryId();
        Dish updateSource = dishMapper.fromUpdateDto(dishUpdateDto);
        Dish result = dishService.update(dishId, subcategoryId, updateSource);
        return dishMapper.toDto(result);
    }

    /**
     * Delete dish by id
     *
     * @param dishId is a dish id that need to delete
     */
    @Operation(description = "Delete dish by id")
    @ApiResponse(responseCode = "200", description = "Dish was deleted")
    @DeleteMapping("/{dish-id}")
    public ResponseEntity<Response> deleteDishById(@PathVariable("dish-id") int dishId){
        dishService.deleteDishById(dishId);
        Response response = new Response("Dish with id " + dishId + " was deleted!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
