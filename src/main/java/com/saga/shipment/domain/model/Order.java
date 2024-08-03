package com.saga.shipment.domain.model;

import com.saga.shipment.application.messaging.api.enums.OrderEventStatus;

import java.util.Set;
import java.util.UUID;

public record Order(
        Integer id,
        OrderEventStatus status,
        String orderId,
        Set<Suborder> suborders,
        UUID customerId
) {
}
