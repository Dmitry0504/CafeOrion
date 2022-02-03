package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.repository.UserRepository;
import com.orion.cafeorion.service.UserService;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dmitriy
 * @since 03.02.2022
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username '" + username + "' not found!"));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String targetUsername, User source) {
        return null;
    }

    @Override
    public void deleteUserByUserName(String username) {
        userRepository.delete(loadUserByUsername(username));
    }
}
