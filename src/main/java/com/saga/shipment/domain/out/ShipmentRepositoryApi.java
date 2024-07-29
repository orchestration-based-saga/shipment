package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.Shipment;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepositoryApi {

    Integer createShipmentToWarehouse(Shipment shipment);

    Optional<Shipment> findById(Integer id);

    void save(Shipment shipment);

    Optional<Shipment> findByPackageId(String packageId);

    List<Shipment> findByPackageIds(List<String> packageIds);
}
