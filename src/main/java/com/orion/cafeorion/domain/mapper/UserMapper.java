package com.orion.cafeorion.domain.mapper;

import com.orion.cafeorion.domain.dto.user.UserCreateDto;
import com.orion.cafeorion.domain.dto.user.UserDto;
import com.orion.cafeorion.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Mapper
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderList", ignore = true)
    User fromCreateDto(UserCreateDto userCreateDto);
}
