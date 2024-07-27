package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.Shipment;

public interface ShipmentProducerApi {

    void sendShipment(Shipment shipment);
}
