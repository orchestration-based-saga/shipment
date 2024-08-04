package com.saga.shipment.application.api.event;

public record SuborderItemMessage(
        Integer id,
        Integer merchantInventoryId
) {
}
