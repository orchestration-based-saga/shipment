package com.saga.shipment.domain.in;

import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;

public interface ShipmentServiceApi {

    void returnItemToWarehouse(Shipment shipment);

    void updateStatus(String packageId, ShipmentDomainStatus status);
}
