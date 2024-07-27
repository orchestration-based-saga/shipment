package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.Shipment;

public interface ShipmentRepositoryApi {

    Integer createShipmentToWarehouse(Shipment shipment);
}
