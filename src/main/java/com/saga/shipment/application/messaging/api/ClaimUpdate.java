package com.saga.shipment.application.messaging.api;

import com.saga.shipment.application.messaging.api.enums.ClaimState;

public record ClaimUpdate(
        Integer claimId,
        Integer shipmentId,
        ClaimState status
) {
}
