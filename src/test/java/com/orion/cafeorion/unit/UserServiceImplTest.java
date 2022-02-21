package com.orion.cafeorion.unit;

import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.repository.UserRepository;
import com.orion.cafeorion.service.implementation.UserServiceImpl;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Dmitriy
 * @since 20.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testUserNotFoundByUsername() {
        String username = "username";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());
        try {
            userRepository.findUserByUsername(username);
        } catch (RuntimeException e) {
            assertEquals(e.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        when(userRepository.findUserByUsername("username")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        assertEquals("username", userService.createUser(user).getUsername());
    }

    @Test
    public void testDeleteUserByUsernameTimes() {
        String username = "username";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));
        userService.deleteUserByUsername(username);
        verify(userRepository, times(1)).delete(user);
    }

    /*
        @Test
    public void testFindDishesBySubcategoryIdIsReturnedValueList() {
        List<Dish> list = new ArrayList<>();
        when(dishRepository.findDishesBySubcategoryId(1)).thenReturn(list);
        assertEquals(list, dishService.findDishesBySubcategoryId(1));
    }

    @Test
    public void testDishNotFoundById() {
        int id = 1;
        when(dishRepository.findById(id)).thenReturn(Optional.empty());
        try {
            dishService.findDishById(id);
        } catch (RuntimeException e) {
            assertEquals(e.getClass(), NotFoundException.class);
        }
    }

    @Test
    public void testCreateDishReturnedValue() {
        Subcategory subcategory = new Subcategory();
        subcategory.setTitle("Test subcategory");
        Dish dish = new Dish();
        dish.setTitle("Test dish");
        when(subcategoryService.findSubcategoryById(1)).thenReturn(subcategory);
        assertEquals(dish, dishService.createDish(1, dish));
    }

    @Test
    public void testCreateDishAddedDishToDishList() {
        Subcategory subcategory = new Subcategory();
        subcategory.setTitle("Test subcategory");
        Dish dish = new Dish();
        dish.setTitle("Test dish");
        when(subcategoryService.findSubcategoryById(1)).thenReturn(subcategory);
        dishService.createDish(1, dish);
        assertEquals(1, subcategory.getDishList().size());
    }

    @Test
    public void testDeleteDishByIdTimes() {
        int id = 1;
        dishService.deleteDishById(id);
        verify(dishRepository, times(1)).deleteById(id);
    }
     */
}
