package com.saga.shipment.application.mapper;

import com.saga.shipment.application.api.event.CreateShipmentMessage;
import com.saga.shipment.application.api.event.OrderMessage;
import com.saga.shipment.application.api.event.SuborderItemMessage;
import com.saga.shipment.application.api.event.SuborderMessage;
import com.saga.shipment.application.api.response.DeliveredShipment;
import com.saga.shipment.application.api.enums.ShipmentState;
import com.saga.shipment.domain.model.Suborder;
import com.saga.shipment.domain.model.Claim;
import com.saga.shipment.domain.model.DeliveredPackage;
import com.saga.shipment.domain.model.Order;
import com.saga.shipment.domain.model.Shipment;
import com.saga.shipment.domain.model.enums.ClaimStatusDomain;
import com.saga.shipment.domain.model.enums.PackageStatus;
import com.saga.shipment.domain.model.enums.ShipmentDomainStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface ShipmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "claim", source = "createShipmentMessage", qualifiedByName = "linkClaim")
    @Mapping(target = "packageId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "senderId", source = "customerId")
    Shipment fromMessage(CreateShipmentMessage createShipmentMessage);

    @Named("linkClaim")
    default Claim linkClaim(CreateShipmentMessage shipment) {
        return new Claim(shipment.claimId(), null, ClaimStatusDomain.valueOf(shipment.status().name()));
    }

    ShipmentDomainStatus fromMessageStatus(ShipmentState state);

    @Mapping(target = "delivered", source = "pack.status", qualifiedByName = "isDelivered")
    DeliveredShipment toResponse(DeliveredPackage pack);

    @Named("isDelivered")
    default boolean isDelivered(PackageStatus status) {
        return PackageStatus.DELIVERED.equals(status);
    }

    Order fromMessage(OrderMessage orderMessage);

    Suborder fromMessage(SuborderMessage suborderMessage);

    SuborderItemMessage fromMessage(SuborderItemMessage suborderItem);
}
