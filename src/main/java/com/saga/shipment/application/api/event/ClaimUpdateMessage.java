package com.saga.shipment.application.api.event;

import com.saga.shipment.application.api.enums.ClaimState;

public record ClaimUpdateMessage(
        Integer claimId,
        Integer shipmentId,
        ClaimState status
) {
}
