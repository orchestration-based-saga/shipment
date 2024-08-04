package com.saga.shipment.domain.model;

import java.util.Set;
import java.util.UUID;

public record Order(
        Integer id,
        com.saga.shipment.domain.model.enums.OrderStatus status,
        String orderId,
        Set<Suborder> suborders,
        UUID customerId
) {
}
