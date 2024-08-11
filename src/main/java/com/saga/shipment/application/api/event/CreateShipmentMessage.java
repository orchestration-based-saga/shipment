package com.saga.shipment.application.api.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saga.shipment.application.api.enums.ClaimState;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateShipmentMessage(
        String orderId,
        Integer itemId,
        Integer merchantInventoryId,
        UUID customerId,
        UUID recipientId,
        Integer id,
        ClaimState status
) {
}
