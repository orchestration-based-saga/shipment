package com.saga.shipment.application.api.event;

import com.saga.shipment.application.api.enums.ClaimState;

import java.util.UUID;

public record CreateShipmentMessage(
        String orderId,
        Integer itemId,
        Integer merchantInventoryId,
        UUID customerId,
        UUID recipientId,
        Integer claimId,
        ClaimState status
) {
}
