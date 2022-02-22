package com.orion.cafeorion.domain.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

/**
 * @author Dmitriy
 * @since 09.02.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "OrderUpdateDto", description = "Used for update order")
public class OrderUpdateDto {
    @NotBlank(message = "Status cannot be empty!")
    @Schema(description = "Order status", example = "CREATED")
    String status;
}
