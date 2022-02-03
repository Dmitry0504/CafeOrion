package com.orion.cafeorion.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "UserCreateDto", description = "UserCreateDto used for create new user")
public class UserCreateDto {
    String username;
    String password;
    String passwordConfirm;
    int enabled;
    String role;
}
