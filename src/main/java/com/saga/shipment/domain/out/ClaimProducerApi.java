package com.saga.shipment.domain.out;

public interface ClaimProducerApi {

    void sendShipmentId(Integer shipmentId, Integer claimId);
}
