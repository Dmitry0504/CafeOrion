package com.orion.cafeorion.domain.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Dmitriy
 * @since 04.02.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Schema(name = "OrderCreateDto", description = "Used for create new order")
public class OrderCreateDto {
    int dishId;
}
