package com.saga.shipment.application.api.event;

import java.util.UUID;

public record DeliveredPackageNotificationMessage(
        Long id,
        String processId,
        String businessKey,
        Long parentProcessId,
        UUID workflow,
        String packageId
) {
}

