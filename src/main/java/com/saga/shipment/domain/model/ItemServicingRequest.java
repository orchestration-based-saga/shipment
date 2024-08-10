package com.saga.shipment.domain.model;

import java.util.UUID;

public record ItemServicingRequest(
        Long id,
        String processId,
        String businessKey,
        Long parentProcessId,
        UUID workflow
) {
}

