package com.orion.cafeorion.domain.mapper;

import com.orion.cafeorion.domain.dto.user.UserCreateDto;
import com.orion.cafeorion.domain.dto.user.UserDto;
import com.orion.cafeorion.domain.dto.user.UserUpdateDto;
import com.orion.cafeorion.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Mapper(imports = {List.class})
public interface UserMapper {
//expression = "java(List.of(userCreateDto.getRole()))
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderList", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User fromCreateDto(UserCreateDto userCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderList", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User fromUpdateDto(UserUpdateDto userUpdateDto);

}
