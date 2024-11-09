package com.saga.shipment.domain.out;

import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.infra.model.enums.ShipmentStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepositoryApi {

    Integer createShipmentToWarehouse(Shipment shipment);

    Optional<Shipment> findById(Integer id);

    Shipment save(Shipment shipment);

    Optional<Shipment> findByPackageId(String packageId);

    List<Shipment> findByPackageIds(List<String> packageIds);

    UUID getUserIdOfMerchant(Integer merchantId);

    void updateStatus(String packageId, ShipmentStatus status);
}
