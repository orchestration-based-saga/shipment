package com.saga.shipment.application.api.event;

public record ItemServicingProcessResponse(
        String processId,
        String businessKey,
        ClaimUpdateMessage claim
) {
}

