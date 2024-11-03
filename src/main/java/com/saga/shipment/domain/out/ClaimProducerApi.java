package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.Claim;
import com.saga.shipment.domain.model.ItemServicingRequest;

public interface ClaimProducerApi {

    void updateClaimWithShipment(String packageId, Integer shipmentId, Claim claim, ItemServicingRequest request);
}
