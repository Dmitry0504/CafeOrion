package com.orion.cafeorion.controller;

import com.orion.cafeorion.domain.dto.order.OrderDto;
import com.orion.cafeorion.domain.dto.user.UserCreateDto;
import com.orion.cafeorion.domain.dto.user.UserDto;
import com.orion.cafeorion.domain.dto.user.UserUpdateDto;
import com.orion.cafeorion.domain.entity.User;
import com.orion.cafeorion.domain.mapper.OrderMapper;
import com.orion.cafeorion.domain.mapper.UserMapper;
import com.orion.cafeorion.service.UserService;
import com.orion.cafeorion.util.exсeption.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@Tag(name = "User controller", description = "designed to work with users")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "User not found")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    /**
     * @return Page<UserDto> on JSON format
     */
    @Operation(description = "Find all categories")
    @ApiResponse(responseCode = "200", description = "Categories were found")
    @ApiResponse(responseCode = "500", description = "Categories not found")
    @GetMapping()
    public Page<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(userDtoList);
    }

    /**
     * Return Category on JSON format
     *
     * @param username is a username
     * @return user on JSON format
     */
    @Operation(description = "Find user by username")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "500", description = "User not found")
    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userMapper.toDto(userService.loadUserByUsername(username));
    }

    /**
     * Add new user
     *
     * @param userCreateDto is a user on JSON format
     * @return user on JSON format
     */
    @Operation(description = "Create user")
    @ApiResponse(responseCode = "200", description = "User was created")
    @PostMapping("")
    public UserDto createNewUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = userMapper.fromCreateDto(userCreateDto);
        userService.createUser(user);
        return userMapper.toDto(user);
    }

    /**
     * Change user by username
     *
     * @param username user to update
     * @param userUpdateDto is a user on JSON format
     * @return userDto on JSON format
     */
    @Operation(description = "Update user")
    @ApiResponse(responseCode = "200", description = "User was updated")
    @PatchMapping("/{username}")
    public UserDto updateUser(@PathVariable String username, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        User user = userMapper.fromUpdateDto(userUpdateDto);
        return userMapper.toDto(userService.updateUser(username, user));
    }

    /**
     * Delete user by username
     *
     * @param username that need to delete
     */
    @Operation(description = "Delete user by username")
    @ApiResponse(responseCode = "200", description = "User was deleted")
    @DeleteMapping("/{username}")
    public ResponseEntity<Response> deleteUserByUsername(@PathVariable String username){
        userService.deleteUserByUsername(username);
        Response response = new Response("User with username " + username + " was deleted!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @return Page<OrderDto> on JSON format
     */
    @Operation(description = "Find all orders by user")
    @ApiResponse(responseCode = "200", description = "Orders were found")
    @ApiResponse(responseCode = "500", description = "Orders not found")
    @GetMapping("/{username}/orders")
    public Page<OrderDto> getAllOrdersByUsername(@PathVariable String username) {
        List<OrderDto> orderDtoList = userService.getOrdersByUser(username)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(orderDtoList);
    }

}
