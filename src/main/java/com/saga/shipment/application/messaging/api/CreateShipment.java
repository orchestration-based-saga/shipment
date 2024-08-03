package com.saga.shipment.application.messaging.api;

import com.saga.shipment.application.messaging.api.enums.ClaimState;

import java.util.UUID;

public record CreateShipment(
        String orderId,
        Integer itemId,
        Integer merchantInventoryId,
        UUID customerId,
        Integer claimId,
        ClaimState status
) {
}
