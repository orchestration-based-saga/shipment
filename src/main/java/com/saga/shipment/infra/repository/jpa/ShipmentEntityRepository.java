package com.saga.shipment.infra.repository.jpa;

import com.saga.shipment.infra.model.ShipmentEntity;
import com.saga.shipment.infra.model.enums.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShipmentEntityRepository extends JpaRepository<ShipmentEntity, Integer> {

    Optional<ShipmentEntity> findByPackageId(String packageId);

    List<ShipmentEntity> findByPackageIdIn(List<String> packageId);

    @Query(""" 
                UPDATE Shipment s
                SET s.status=?2
                WHERE s.packageId = ?1
            """
    )
    void updateStatusByPackage(String packageId, ShipmentStatus status);

}
