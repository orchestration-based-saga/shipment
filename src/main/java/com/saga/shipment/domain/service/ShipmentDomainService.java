package com.saga.shipment.domain.service;

import com.saga.shipment.domain.in.ShipmentServiceApi;
import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.out.ClaimProducerApi;
import com.saga.shipment.domain.out.ShipmentRepositoryApi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipmentDomainService implements ShipmentServiceApi {
    private final ShipmentRepositoryApi shipmentRepositoryApi;
    private final ClaimProducerApi claimProducerApi;

    @Override
    public void returnItemToWarehouse(Shipment shipment) {
        Integer shipmentId = shipmentRepositoryApi.createShipmentToWarehouse(shipment);
        claimProducerApi.sendShipmentId(shipmentId, shipment.claim().id());
    }
}
