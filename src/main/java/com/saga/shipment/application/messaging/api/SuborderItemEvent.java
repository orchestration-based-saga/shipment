package com.saga.shipment.application.messaging.api;

public record SuborderItemEvent(
        Integer id,
        Integer merchantInventoryId
) {
}
