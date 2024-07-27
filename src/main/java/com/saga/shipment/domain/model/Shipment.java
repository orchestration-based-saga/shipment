package com.saga.shipment.domain.model;

import com.saga.shipment.infra.model.enums.ShipmentStatus;
import lombok.Builder;
import org.apache.commons.lang3.RandomStringUtils;

@Builder
public record Shipment(
        Integer id,
        Claim claim,
        String orderId,
        Integer merchantInventoryId,
        Integer itemId,
        String packageId
) {

    public Shipment generatePackageId() {
        String packageId = orderId().concat(RandomStringUtils.randomAlphanumeric(5));
        return new Shipment(id, claim, orderId, merchantInventoryId, itemId, packageId);
    }

    public Shipment updateStatus(ShipmentStatus status) {
        return new Shipment(id, claim, orderId, merchantInventoryId, itemId, packageId);
    }
}
