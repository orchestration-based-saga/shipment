package com.saga.shipment.domain.service;

import com.saga.shipment.domain.in.ShipmentServiceApi;
import com.saga.shipment.domain.model.Claim;
import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.model.enums.ClaimStatusDomain;
import com.saga.shipment.domain.out.ClaimProducerApi;
import com.saga.shipment.domain.out.ShipmentRepositoryApi;
import com.saga.shipment.infra.model.enums.ShipmentStatus;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ShipmentDomainService implements ShipmentServiceApi {
    private final ShipmentRepositoryApi shipmentRepositoryApi;
    private final ClaimProducerApi claimProducerApi;

    @Override
    public void returnItemToWarehouse(Shipment shipment) {
        Integer shipmentId = shipmentRepositoryApi.createShipmentToWarehouse(shipment);
        Optional<Shipment> maybeShipment = shipmentRepositoryApi.findById(shipmentId);
        if (maybeShipment.isEmpty()) {
            throw new RuntimeException("Couldn't find shipment with id: " + shipmentId);
        }
        shipment = shipment.generatePackageId();
        shipment.updateStatus(ShipmentStatus.IN_DELIVERY);
        shipmentRepositoryApi.save(shipment);
        Claim updatedClaim = shipment.claim().updateStatus(ClaimStatusDomain.IN_DELIVERY);
        claimProducerApi.sendShipmentId(shipmentId, updatedClaim);
    }

}
