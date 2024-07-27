package com.saga.shipment.domain.in;

import com.saga.shipment.domain.model.Shipment;

public interface ShipmentServiceApi {

    void returnItemToWarehouse(Shipment shipment);
}
