package com.saga.shipment.domain.in;

import com.saga.shipment.domain.model.Order;
import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;

import java.util.List;

public interface ShipmentServiceApi {

    void returnItemToWarehouse(Shipment shipment);

    void updateStatus(List<String> packageIds, ShipmentDomainStatus status);

    void reassignCourier(List<String> packageIds);

    void processOrder(Order order);
}
