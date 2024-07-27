package com.saga.shipment.domain.model;

public record Shipment(
        Integer id,
        Claim claim,
        String orderId,
        Integer merchantInventoryId,
        Integer itemId
) {
}
