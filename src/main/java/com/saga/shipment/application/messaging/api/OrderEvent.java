package com.saga.shipment.application.messaging.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saga.shipment.application.messaging.api.enums.OrderEventStatus;

import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderEvent(
        Integer id,
        OrderEventStatus status,
        String orderId,
        Set<SuborderEvent> suborders,
        UUID customerId
) {
}
