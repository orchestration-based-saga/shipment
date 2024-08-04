package com.saga.shipment.application.api.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saga.shipment.application.api.enums.OrderEventStatus;

import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderMessage(
        Integer id,
        OrderEventStatus status,
        String orderId,
        Set<SuborderMessage> suborders,
        UUID customerId
) {
}
