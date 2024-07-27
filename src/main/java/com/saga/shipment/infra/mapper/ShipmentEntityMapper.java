package com.saga.shipment.infra.mapper;

import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.infra.model.ClaimEntity;
import com.saga.shipment.infra.model.ShipmentEntity;
import com.saga.shipment.infra.model.enums.ClaimStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface ShipmentEntityMapper {

    @Mapping(target = "claim", source = "shipment", qualifiedByName = "linkClaim")
    @Mapping(target = "recipientAddress", ignore = true)
    @Mapping(target = "senderAddress", ignore = true)
    @Mapping(target = "status", ignore = true)
    ShipmentEntity toEntity(Shipment shipment);

    @Named("linkClaim")
    default ClaimEntity linkClaim(Shipment shipment) {
        return ClaimEntity.builder()
                .id(shipment.claim().id())
                .status(ClaimStatus.valueOf(shipment.claim().status().name()))
                .build();
    }
}
