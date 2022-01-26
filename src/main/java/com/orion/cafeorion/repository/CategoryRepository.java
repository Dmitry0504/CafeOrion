package com.orion.cafeorion.repository;

import com.orion.cafeorion.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dmitriy
 * @since 20.01.2022
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
