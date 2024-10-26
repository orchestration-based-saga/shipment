package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.ItemServicingRequest;
import com.saga.shipment.domain.model.Shipment;

public interface ShipmentProducerApi {

    void sendShipment(Shipment shipment);

    void packageIsDelivered(String packageId, boolean isDelivered, ItemServicingRequest request);
}
