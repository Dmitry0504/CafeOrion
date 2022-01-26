package com.orion.cafeorion.repository;

import com.orion.cafeorion.domain.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    List<Subcategory> findSubcategoryByCategory_Id(int categoryId);
}
