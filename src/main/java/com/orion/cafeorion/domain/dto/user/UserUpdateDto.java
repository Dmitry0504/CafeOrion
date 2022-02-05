package com.orion.cafeorion.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Dmitriy
 * @since 05.02.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "UserUpdateDto", description = "This dto used for update users")
public class UserUpdateDto {
    String username;
    String password;
    int enabled;
    String role;
}
