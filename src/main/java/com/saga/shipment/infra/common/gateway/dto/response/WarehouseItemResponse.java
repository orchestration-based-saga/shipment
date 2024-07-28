package com.saga.shipment.infra.common.gateway.dto.response;

public record WarehouseItemResponse(
        Integer merchantInventoryId,
        WarehouseItemStatus status,
        String packageId
) {
}

