package com.saga.shipment.domain.model;

import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import lombok.Builder;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

@Builder
public record Shipment(
        Integer id,
        Claim claim,
        String orderId,
        Integer merchantInventoryId,
        Integer itemId,
        String packageId,
        ShipmentDomainStatus status,
        UUID senderId,
        UUID recipientId
) {

    public Shipment generatePackageId() {
        String packageId = orderId().concat(RandomStringUtils.randomAlphanumeric(5));
        return new Shipment(id, claim, orderId, merchantInventoryId, itemId, packageId, status, senderId, recipientId);
    }

    public Shipment updateStatus(ShipmentDomainStatus status) {
        return new Shipment(id, claim, orderId, merchantInventoryId, itemId, packageId, status, senderId, recipientId);
    }
}
