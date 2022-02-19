package com.orion.cafeorion.unit;

import com.orion.cafeorion.repository.DishRepository;
import com.orion.cafeorion.service.SubcategoryService;
import com.orion.cafeorion.service.implementation.DishServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Dmitriy
 * @since 20.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class DishServiceImplTest {

    @InjectMocks
    private DishServiceImpl dishService;
    @Mock
    private DishRepository dishRepository;
    @Mock
    private SubcategoryService subcategoryService;


}
