package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.repository.UserRepository;
import com.orion.cafeorion.service.UserService;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import com.orion.cafeorion.util.exсeption.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (userExist(user.getUsername())) {
            throw new UserAlreadyExistException(user.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String targetUsername, User source) {
        if (userExist(source.getUsername())) {
            throw new UserAlreadyExistException(source.getUsername());
        }
        User target = loadUserByUsername(targetUsername);
        target.setUsername(source.getUsername());
        target.setPassword(source.getPassword());
        target.setEnabled(source.getEnabled());
        target.setRole(source.getRole());
        return userRepository.save(target);
    }

    @Override
    public void deleteUserByUserName(String username) {
        userRepository.delete(loadUserByUsername(username));
    }

    private boolean userExist(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        return optionalUser.isPresent();
    }
}
