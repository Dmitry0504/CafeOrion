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
@Schema(name = "User Dto", description = "User dto used for presentation")
public class UserDto {
    @Schema(description = "Username", example = "root")
    String username;
    @Schema(description = "Can take values 0 or 1. 0 if user is blocked", example = "1")
    int enabled;
    @Schema(description = "User role", example = "ROLE_USER")
    String role;
}
