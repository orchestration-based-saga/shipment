package com.saga.shipment.infra.repository;

import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.out.ShipmentRepositoryApi;
import com.saga.shipment.infra.mapper.ShipmentEntityMapper;
import com.saga.shipment.infra.model.ClaimEntity;
import com.saga.shipment.infra.model.ShipmentEntity;
import com.saga.shipment.infra.model.enums.ClaimStatus;
import com.saga.shipment.infra.model.enums.ShipmentStatus;
import com.saga.shipment.infra.repository.jpa.ClaimEntityRepository;
import com.saga.shipment.infra.repository.jpa.ShipmentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShipmentRepository implements ShipmentRepositoryApi {

    private final ShipmentEntityMapper mapper;
    private final ClaimEntityRepository claimEntityRepository;
    private final ShipmentEntityRepository shipmentEntityRepository;

    @Override
    public Integer createShipmentToWarehouse(Shipment shipment) {
        ShipmentEntity shipmentEntity = mapper.toEntity(shipment);
        if (shipmentEntity.getClaim() == null || !shipmentEntity.getClaim().getStatus().equals(ClaimStatus.RETURNING_TO_WAREHOUSE)) {
            throw new RuntimeException("Returning to warehouse flow triggered, but no associated claim");
        }
        ClaimEntity claim = claimEntityRepository.save(shipmentEntity.getClaim());
        shipmentEntity.setClaim(claim);
        shipmentEntity.setStatus(ShipmentStatus.CREATED);
        return shipmentEntityRepository.save(shipmentEntity).getId();
    }
}
