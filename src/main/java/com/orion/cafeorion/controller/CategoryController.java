package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.category.CategoryCreateDto;
import com.orion.cafeorion.domain.dto.category.CategoryDto;
import com.orion.cafeorion.domain.dto.category.CategoryUpdateDto;
import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.mapper.CategoryMapper;
import com.orion.cafeorion.service.CategoryService;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import com.orion.cafeorion.util.exсeption.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 20.01.2022
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
@Tag(name = "Category controller", description = "designed to work with categories")
@ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
@ApiResponse(responseCode = "400", description = "Validation failed",
        content = @Content(schema = @Schema(implementation = MethodArgumentNotValidException.class)))
@ApiResponse(responseCode = "404", description = "Category not found",
        content = @Content(schema = @Schema(implementation = NotFoundException.class)))
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    /**
     * @return Page<CategoryDto> on JSON format
     */
    @Operation(description = "Find all categories")
    @ApiResponse(responseCode = "200", description = "Categories were found",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))))
    @GetMapping()
    public Page<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(categoryDtoList);
    }

    /**
     * Return Category on JSON format
     *
     * @param id is a category id
     * @return category on JSON format
     */
    @Operation(description = "Find category by id")
    @ApiResponse(responseCode = "200", description = "Category found")
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable int id) {
        return categoryMapper.toDto(categoryService.findCategoryById(id));
    }

    /**
     * Create a new Category
     *
     * @param categoryCreateDto is a category on JSON format
     * @return category on JSON format
     */
    @Operation(description = "Create category")
    @ApiResponse(responseCode = "200", description = "Category was created")
    @PostMapping("")
    public CategoryDto createNewCategory(@Valid @RequestBody CategoryCreateDto categoryCreateDto) {
        Category category = categoryMapper.fromCreateDto(categoryCreateDto);
        categoryService.saveCategory(category);
        return categoryMapper.toDto(category);
    }

    /**
     * Change category by id
     *
     * @param categoryUpdateDto is a category on JSON format
     * @return category on JSON format
     */
    @Operation(description = "Update category")
    @ApiResponse(responseCode = "200", description = "Category was updated")
    @PatchMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable int id,
                                      @Valid @RequestBody CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryMapper.fromUpdateDto(categoryUpdateDto);
        return categoryMapper.toDto(categoryService.update(id, category));
    }

    /**
     * Delete category by id
     *
     * @param id is a category id that need to delete
     */
    @Operation(description = "Delete category by id")
    @ApiResponse(responseCode = "200", description = "Category was deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategoryById(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        Response response = new Response("Category with id " + id + " was deleted!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
