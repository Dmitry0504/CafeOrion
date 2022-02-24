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
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @Mock
    private PasswordEncoder passwordEncoder;

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

}
