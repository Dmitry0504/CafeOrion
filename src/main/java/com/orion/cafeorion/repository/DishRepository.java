package com.orion.cafeorion.repository;

import com.orion.cafeorion.domain.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findDishesBySubcategoryId(int subcategoryId);
}
