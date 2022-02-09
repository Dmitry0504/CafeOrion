package com.orion.cafeorion.domain.dto.order;

import com.orion.cafeorion.domain.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

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
    Order.Status status;
}
