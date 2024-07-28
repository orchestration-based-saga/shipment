package com.saga.shipment.application.messaging.api;

import com.saga.shipment.application.messaging.api.enums.ShipmentState;

public record UpdateShipmentStatus(
        String packageId,
        ShipmentState status
) {
}

