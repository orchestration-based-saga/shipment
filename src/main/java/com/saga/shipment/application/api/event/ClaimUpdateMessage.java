package com.saga.shipment.application.api.event;

import com.saga.shipment.application.api.enums.ClaimState;

public record ClaimUpdateMessage(
        Integer id,
        String orderId,
        Integer shipmentId,
        ClaimState status,
        String packageId,
        Integer merchantInventoryId,
        Integer itemId
) {
}
