package com.saga.shipment.domain.service;

import com.saga.shipment.domain.in.ShipmentServiceApi;
import com.saga.shipment.domain.model.Claim;
import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.model.enums.ClaimStatusDomain;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import com.saga.shipment.domain.out.ClaimProducerApi;
import com.saga.shipment.domain.out.ShipmentProducerApi;
import com.saga.shipment.domain.out.ShipmentRepositoryApi;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ShipmentDomainService implements ShipmentServiceApi {
    private final ShipmentRepositoryApi shipmentRepositoryApi;
    private final ClaimProducerApi claimProducerApi;
    private final ShipmentProducerApi shipmentProducerApi;

    @Override
    public void returnItemToWarehouse(Shipment shipment) {
        Integer shipmentId = shipmentRepositoryApi.createShipmentToWarehouse(shipment);
        Optional<Shipment> maybeShipment = shipmentRepositoryApi.findById(shipmentId);
        if (maybeShipment.isEmpty()) {
            throw new RuntimeException("Couldn't find shipment with id: " + shipmentId);
        }
        Shipment updatedShipment = maybeShipment.get();
        updatedShipment = updatedShipment.generatePackageId();
        updatedShipment.updateStatus(ShipmentDomainStatus.IN_DELIVERY);
        shipmentRepositoryApi.save(updatedShipment);
        Claim updatedClaim = updatedShipment.claim().updateStatus(ClaimStatusDomain.IN_DELIVERY);
        claimProducerApi.sendShipmentId(shipmentId, updatedClaim);
        shipmentProducerApi.sendShipment(updatedShipment);
    }

    @Override
    public void updateStatus(String packageId, ShipmentDomainStatus status) {
        Optional<Shipment> maybeShipment = shipmentRepositoryApi.findByPackageId(packageId);
        if (maybeShipment.isEmpty()) {
            throw new RuntimeException("Couldn't find shipment with package id: " + packageId);
        }
        Shipment shipment = maybeShipment.get();
        shipment = shipment.updateStatus(status);
        shipmentRepositoryApi.save(shipment);
        shipmentProducerApi.sendShipment(shipment);
    }
}
