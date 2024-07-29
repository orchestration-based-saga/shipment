package com.saga.shipment.infra.repository.jpa;

import com.saga.shipment.infra.model.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentEntityRepository extends JpaRepository<ShipmentEntity, Integer> {

    Optional<ShipmentEntity> findByPackageId(String packageId);

    List<ShipmentEntity> findByPackageIdIn(List<String> packageId);

}
