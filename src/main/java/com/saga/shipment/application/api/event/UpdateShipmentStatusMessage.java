package com.saga.shipment.application.api.event;

import com.saga.shipment.application.api.enums.ShipmentState;

public record UpdateShipmentStatusMessage(
        String packageId,
        ShipmentState status
) {
}

