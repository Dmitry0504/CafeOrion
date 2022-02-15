package com.orion.cafeorion.service.implementation;

import com.orion.cafeorion.domain.entity.Order;
import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.repository.UserRepository;
import com.orion.cafeorion.service.UserService;
import com.orion.cafeorion.util.exсeption.NotFoundException;
import com.orion.cafeorion.util.exсeption.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Dmitriy
 * @since 03.02.2022
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Secured({ "ROLE_ADMIN", "ROLE_WAITER" })
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username '" + username + "' not found!"));
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    @Transactional
    public User createUser(User user) {
        if (userExist(user.getUsername())) {
            throw new UserAlreadyExistException(user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    @Transactional
    public User updateUser(String targetUsername, User source) {
        if (userExist(source.getUsername())) {
            throw new UserAlreadyExistException(source.getUsername());
        }
        User target = loadUserByUsername(targetUsername);
        target.setUsername(source.getUsername());
        target.setPassword(passwordEncoder.encode(source.getPassword()));
        target.setEnabled(source.getEnabled());
        target.setRole(source.getRole());
        return userRepository.save(target);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        userRepository.delete(loadUserByUsername(username));
    }

    @Override
    public List<Order> getOrdersByUser(String username) {
        User user = loadUserByUsername(username);
        return user.getOrderList();
    }

    private boolean userExist(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        return optionalUser.isPresent();
    }
}
