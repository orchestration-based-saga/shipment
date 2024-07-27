package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.Claim;

public interface ClaimProducerApi {

    void sendShipmentId(Integer shipmentId, Claim claim);
}
