package com.saga.shipment.application.mapper;

import com.saga.shipment.application.application.api.response.DeliveredShipment;
import com.saga.shipment.application.messaging.api.CreateShipment;
import com.saga.shipment.application.messaging.api.enums.ShipmentState;
import com.saga.shipment.domain.model.Claim;
import com.saga.shipment.domain.model.DeliveredPackage;
import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.model.enums.ClaimStatusDomain;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ShipmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "claim", source = "createShipmentRequest", qualifiedByName = "linkClaim")
    @Mapping(target = "packageId", ignore = true)
    @Mapping(target = "status", ignore = true)
    Shipment fromMessage(CreateShipment createShipmentRequest);

    @Named("linkClaim")
    default Claim linkClaim(CreateShipment shipment) {
        return new Claim(shipment.claimId(), null, ClaimStatusDomain.valueOf(shipment.status().name()));
    }

    ShipmentDomainStatus fromMessageStatus(ShipmentState state);

    DeliveredShipment toResponse(DeliveredPackage pack);

    List<DeliveredShipment> toResponse(List<DeliveredPackage> pack);
}
