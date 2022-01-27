package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.subcategory.SubcategoryCreateDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryFullDto;
import com.orion.cafeorion.domain.dto.subcategory.SubcategoryUpdateDto;
import com.orion.cafeorion.domain.entity.Category;
import com.orion.cafeorion.domain.entity.Subcategory;
import com.orion.cafeorion.domain.mapper.SubcategoryMapper;
import com.orion.cafeorion.service.CategoryService;
import com.orion.cafeorion.service.SubcategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/categories/{id}/subcategories")
@Tag(name = "Subcategory controller", description = "designed to work with subcategories")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Subcategory not found")
public class SubcategoryController {

    private final SubcategoryMapper subcategoryMapper;
    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    /**
     * Return List<SubcategoryDto> on JSON format
     *
     * @param id is category id for search subcategories
     * @return List<SubcategoryDto> on JSON format
     */
    @GetMapping("")
    @Operation(description = "Find all subcategories")
    @ApiResponse(responseCode = "200", description = "Subcategories were found")
    @ApiResponse(responseCode = "500", description = "Subcategories not found")
    public List<SubcategoryDto> getSubcategoriesFromCategory(@PathVariable int id) {
        return subcategoryService.findSubcategoryByCategory_Id(id)
                .stream()
                .map(subcategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Return Subcategory on JSON format
     *
     * @param subcategory_id is a subcategory id
     * @return subcategoryFullDto on JSON format
     */
    @Operation(description = "Find subcategory by id")
    @ApiResponse(responseCode = "200", description = "Subcategory was found")
    @ApiResponse(responseCode = "500", description = "Subcategory not found")
    @GetMapping("/{subcategory-id}")
    public SubcategoryFullDto getSubcategoryById(@PathVariable("subcategory-id") int subcategory_id) {
        return subcategoryMapper.toFullDto(subcategoryService.findSubcategoryById(subcategory_id));
    }

    /**
     * Create a new subcategory
     *
     * @param id is category id which subcategory belongs
     * @param subcategoryCreateDto is a subcategory on JSON format
     * @return subcategoryDto on JSON format
     */
    @Operation(description = "Create subcategory")
    @ApiResponse(responseCode = "200", description = "Subcategory was created")
    @PostMapping()
    public SubcategoryDto crateNewSubcategory(@PathVariable int id
            , @Valid @RequestBody SubcategoryCreateDto subcategoryCreateDto) {
        Category category = categoryService.findCategoryById(id);
        Subcategory subcategory = subcategoryMapper.fromCreateDto(subcategoryCreateDto);
        category.addNewSubcategoryToList(subcategory);
        categoryService.saveCategory(category);
        return subcategoryMapper.toDto(subcategory);
    }

    /**
     * Change subcategory by id
     *
     * @param subcategory_id is subcategory id to update
     * @param subcategoryUpdateDto is a subcategory on JSON format
     * @return subCategoryDto on JSON format
     */
    @Operation(description = "Update subcategory")
    @ApiResponse(responseCode = "200", description = "Subcategory was updated")
    @PatchMapping("/{subcategory-id}")
    public SubcategoryDto updateSubcategory(@PathVariable("subcategory-id") int subcategory_id
            , @Valid @RequestBody SubcategoryUpdateDto subcategoryUpdateDto) {

        Subcategory subcategoryUpdate = subcategoryMapper.fromUpdateDto(subcategoryUpdateDto);
        int categoryId = subcategoryUpdateDto.getCategoryId();
        Category category = categoryService.findCategoryById(categoryId);

        return Optional.of(subcategoryMapper.toDto(subcategoryService.update(subcategory_id, category, subcategoryUpdate))).orElseThrow();
    }

    /**
     * Delete subcategory by id
     *
     * @param subcategory_id is a subcategory id that need to delete
     */
    @Operation(description = "Delete subcategory by id")
    @ApiResponse(responseCode = "200", description = "Subcategory was deleted")
    @DeleteMapping("/{subcategory-id}")
    public void deleteSubcategoryById(@PathVariable("subcategory-id") int subcategory_id) {
        subcategoryService.deleteSubcategoryById(subcategory_id);
    }

}
