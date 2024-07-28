package com.saga.shipment.infra.mapper;

import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import com.saga.shipment.infra.model.ClaimEntity;
import com.saga.shipment.infra.model.ShipmentEntity;
import com.saga.shipment.infra.model.enums.ClaimStatus;
import com.saga.shipment.infra.model.enums.ShipmentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface ShipmentEntityMapper {

    @Mapping(target = "claim", source = "shipment", qualifiedByName = "linkClaim")
    @Mapping(target = "recipientAddress", ignore = true)
    @Mapping(target = "senderAddress", ignore = true)
    ShipmentEntity toEntity(Shipment shipment);

    ShipmentStatus toEntity(ShipmentDomainStatus status);

    @Named("linkClaim")
    default ClaimEntity linkClaim(Shipment shipment) {
        return ClaimEntity.builder()
                .id(shipment.claim().id())
                .status(ClaimStatus.valueOf(shipment.claim().status().name()))
                .build();
    }

    Shipment toDomain(ShipmentEntity entity);
}