package com.saga.shipment.infra.repository.jpa;

import com.saga.shipment.infra.model.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentEntityRepository extends JpaRepository<ShipmentEntity, Integer> {

}
