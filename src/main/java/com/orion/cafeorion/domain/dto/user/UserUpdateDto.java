package com.orion.cafeorion.domain.dto.user;

import com.orion.cafeorion.domain.entity.Role;
import com.orion.cafeorion.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;

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
    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 2, max = 15, message = "Username must be between 2 and 15 characters")
    @Schema(description = "Username", example = "Tom")
    String username;
    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 4, max = 15, message = "Password must be between 4 and 15 characters")
    @Schema(description = "User password", example = "Qwerty1234")
    String password;
    @Min(value = 0)
    @Max(value = 1)
    @Schema(description = "Can take values 0 or 1. 0 if user is blocked", example = "1")
    int enabled;
    @Schema(description = "User role", example = "ROLE_USER")
    Role role;
}
